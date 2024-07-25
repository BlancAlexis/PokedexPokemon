package com.example.pokedexpokemon.domainLayer.repository

import BasePokemon
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.map
import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.domainLayer.card_mapper.PokemonMapper.toDomain
import org.koin.core.component.KoinComponent

interface BasePokemonRepository {
    suspend fun getListBasePokemon(): List<BasePokemon>
    suspend fun getPokemon(index: String): BasePokemon
}

class BasePokemonRepositoryImpl(
    private val basePokemonDataSource: BasePokemonDataSource
) : BasePokemonRepository, KoinComponent {
    override suspend fun getListBasePokemon(nextPageNumber: Int, loadSize: Int): Flow<PagingData<BasePokemon>> {
         return Pager(PagingConfig(pageSize = 20)) {
            BookPagingSource(basePokemonDataSource)
        }.flow.map {
            it.map {
                it.toDomain()
            }
         } // TODO Trouver autre chose?
    }

    override suspend fun getPokemon(index: String): BasePokemon {
        return basePokemonDataSource.getPokemon(index).toDomain()

    }
}

class BookPagingSource(
    private val dataSource: BasePokemonDataSource
) : PagingSource<Int, BasePokemonDTO>() {

    override suspend fun load(params: LoadParams<Int>):  LoadResult<Int, BasePokemonDTO> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = dataSource.getListBasePokemon(mapOf("limit" to "$nextPageNumber", "offset" to "$params.loadSize"))
            val result = response.results.mapNotNull {
                    dataSource.extractPokemonId(it.url)?.let { id ->
                        try {
                            dataSource.getPokemon(id)
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
            LoadResult.Page(
                data = result,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.results.isEmpty()) null else response.count + 1 // probablement pas le count
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BasePokemonDTO>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
    }