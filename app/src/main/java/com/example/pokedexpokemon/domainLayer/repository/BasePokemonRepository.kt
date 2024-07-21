package com.example.pokedexpokemon.domainLayer.repository

import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.model.BasePokemon
import org.koin.core.component.KoinComponent

interface BasePokemonRepository {
    suspend fun getListBasePokemon(): Ressource<List<BasePokemon>>
}
class BasePokemonRepositoryImpl(
    private val basePokemonDataSource: BasePokemonDataSource
) : BasePokemonRepository, KoinComponent {
    override suspend fun getListBasePokemon(): Ressource<List<BasePokemon>> {
        return basePokemonDataSource.getListBasePokemon()
    }
}
