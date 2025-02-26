package com.example.pokedexpokemon.dataLayer.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedexpokemon.dataLayer.datasource.CardPokemonDataSource
import com.example.pokedexpokemon.domainLayer.card_mapper.CardMapper.toDomain
import com.example.pokedexpokemon.domainLayer.model.PokemonCard
import retrofit2.HttpException

class CardPagingSource(
    private val remoteDataSource: CardPokemonDataSource,
) : PagingSource<Int, PokemonCard>() {

    lateinit var name: String

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonCard> {
        return try {
            val currentPage = params.key ?: 1
            val apiResponse = remoteDataSource.getPokemonByName(
                pageNumber = currentPage,
                name = name,
            )
            LoadResult.Page(
                data = apiResponse.data.map { it.toDomain() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (apiResponse.data.isEmpty()) null else currentPage + 1
            )
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonCard>): Int? {
        return state.anchorPosition
    }

}