package com.example.pokedexpokemon.dataLayer.di

import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSourceImpl
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.dto.PokedexResponse
import com.example.pokedexpokemon.dataLayer.utils.NetworkInterceptor
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepositoryImpl
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

fun injectFeature() = loadFeature


private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            retrofitModule,
        )
    )
}

val retrofitModule = module {
    single<PokedexService> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PokedexService::class.java)
    }

    factory<BasePokemonRepository> { BasePokemonRepositoryImpl(get()) }
    factory<BasePokemonDataSource> { BasePokemonDataSourceImpl(get()) }
    factory<GetPokemonList> {GetPokemonList(get())}
    factory<GetPokemon> { GetPokemon(get()) }
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
