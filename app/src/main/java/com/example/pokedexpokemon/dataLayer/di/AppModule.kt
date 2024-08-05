package com.example.pokedexpokemon.dataLayer.di

import androidx.room.Room
import com.example.pokedexpokemon.BuildConfig
import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSourceImpl
import com.example.pokedexpokemon.dataLayer.datasource.CardPokemonDataSource
import com.example.pokedexpokemon.dataLayer.datasource.CardPokemonDataSourceImpl
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.dto.PokedexResponse
import com.example.pokedexpokemon.dataLayer.dto.ResponseCardApi
import com.example.pokedexpokemon.dataLayer.paging.CardPagingSource
import com.example.pokedexpokemon.dataLayer.room.Database
import com.example.pokedexpokemon.dataLayer.utils.NetworkInterceptor
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepositoryImpl
import com.example.pokedexpokemon.domainLayer.repository.CardPokemonRepository
import com.example.pokedexpokemon.domainLayer.repository.CardPokemonRepositoryImpl
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonCardByNameUseCase
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailPokemonViewModel
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon.CardPokemonViewModel
import com.example.pokedexpokemon.presentationLayer.settings.SettingsViewModel
import com.example.pokedexpokemon.presentationLayer.teams.TeamViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

fun injectFeature() = loadFeature


private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            retrofit1Module,
            databaseModule,
            retrofitModule,
            settingsModule,
            teamModule,
        )
    )
}

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}


val teamModule = module {
    viewModelOf(::TeamViewModel)
}

val retrofitModule = module {
    single<PokedexService> {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(NetworkInterceptor()).build()

        Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            .create(PokedexService::class.java)
    }

    factory<BasePokemonRepository> { BasePokemonRepositoryImpl(get()) }
    factory<BasePokemonDataSource> { BasePokemonDataSourceImpl(get()) }
    factory<GetPokemonList> { GetPokemonList(get()) }
    factory<GetPokemon> { GetPokemon(get()) }
    viewModelOf(::ListDetailPokemonViewModel)

}

val retrofit1Module = module {
    single<PokemonCardService> {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(NetworkInterceptor()).build()

        Retrofit.Builder().baseUrl("https://api.pokemontcg.io/v2/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            .create(PokemonCardService::class.java)
    }

    factory<CardPokemonRepository> { CardPokemonRepositoryImpl(get()) }
    factory<CardPokemonDataSource> { CardPokemonDataSourceImpl(get()) }
    factory<GetPokemonCardByNameUseCase> { GetPokemonCardByNameUseCase(get()) }
    factory<CardPagingSource> { CardPagingSource(get()) }
    viewModelOf(::CardPokemonViewModel)

}


val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            Database::class.java,
            "Database_PokedexPokemon"
        ).build()
    }
    single {
        get<Database>().getPokemonDAO()
    }
}

interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): PokedexResponse

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): BasePokemonDTO
}

interface PokemonCardService {
    @GET("cards")
    suspend fun getPokemonCardByName(
        @Query("page") pageNumber: Int,
        @Query("pageSize") pageSize: Int = 20,
        @Query("q") name: String,
        @Header("X-Api-Key") apiKey: String = BuildConfig.CARD_POKEMON_API_KEY
    ): ResponseCardApi
}