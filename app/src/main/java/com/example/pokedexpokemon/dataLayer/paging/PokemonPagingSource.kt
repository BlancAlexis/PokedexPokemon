package com.example.pokedexpokemon.dataLayer.paging

import BasePokemon
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.domainLayer.card_mapper.PokemonMapper.toDomain

class PokemonPagingSource (
        private val remoteDataSource: BasePokemonDataSource
    ) : PagingSource<Int, BasePokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BasePokemon> {
        println("base paging source")
        return try {
            val nextPageNumber = params.key?.times(20)
            val response = remoteDataSource.getListBasePokemon(
                offset = nextPageNumber ?: 1,
            )
            val listPokemon = response.results.mapNotNull {
                remoteDataSource.extractPokemonId(it.url)?.let { id ->
                    try {
                        remoteDataSource.getPokemon(id)
                    } catch (e: Exception) {
                        null
                    }
                }
            }
            LoadResult.Page(
                data = listPokemon.map { it.toDomain() },
                prevKey = if (nextPageNumber == 1 || nextPageNumber == null) null else nextPageNumber -1,
                nextKey = if (response.results.isEmpty()) null else params.key?.plus(1) // probablement pas le count
            )
        } catch (e: Exception) {
            println("exeception $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BasePokemon>): Int? {
        return state.anchorPosition
    }
}