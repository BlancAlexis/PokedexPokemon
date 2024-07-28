package com.example.pokedexpokemon.dataLayer.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedexpokemon.dataLayer.di.PokemonCardService
import com.example.pokedexpokemon.dataLayer.dto.CardPrice
import com.example.pokedexpokemon.dataLayer.dto.PokemonCard
import com.example.pokedexpokemon.dataLayer.dto.PokemonCardDTO
import com.example.pokedexpokemon.dataLayer.dto.Prices
import com.example.pokedexpokemon.dataLayer.dto.ResponseCardApi
import retrofit2.HttpException

interface CardPokemonDataSource {
    suspend fun getPokemonByName(pageNumber : Int, name : String): ResponseCardApi
}

class CardPokemonDataSourceImpl(
    private val pokemonCardService: PokemonCardService
) : CardPokemonDataSource {

    override suspend fun getPokemonByName(pageNumber : Int, name : String): ResponseCardApi {
        return pokemonCardService.getPokemonCardByName(pageNumber, name = name )
    }
}


fun PokemonCardDTO.toDomain() = PokemonCard(
    artist = artist,
    name = name,
    hp = hp,
  //  set = setDTO.,
    types = types,
    images = imagesDTO.small,
    number = number,
    rarity = rarity?: "",
    subtypes = subtypes,
    cardPrice = CardPrice(
        "",
        Prices(23.0,34.0,45.0,45.0,23.0)
    ),
    supertype = supertype,
)

class CardPagingSource(
    private val remoteDataSource: CardPokemonDataSource,
) : PagingSource<Int, PokemonCard>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonCard> { // je conv pas la ?
        return try {
            println("load charizard")
            val currentPage = params.key ?: 1
            val apiResponse = remoteDataSource.getPokemonByName(
                pageNumber = currentPage,
                name = "charizard",
            )
            LoadResult.Page(
                data = apiResponse.data.map { it.toDomain() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (apiResponse.data.isEmpty()) null else apiResponse.page + 1
            )
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonCard>): Int? {
        return state.anchorPosition
    }

}