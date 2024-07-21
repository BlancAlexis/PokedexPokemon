package com.example.pokedexpokemon.domainLayer.usecase

import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import org.koin.core.component.KoinComponent

class GetPokemonList(
    private val basePokemonRepository: BasePokemonRepository
) : KoinComponent {
    suspend fun invoke(): Ressource<List<BasePokemonDTO>> {
        return try{
             Ressource.Success(basePokemonRepository.getListBasePokemon())
        }
        catch (e : Exception){
            Ressource.Error(e)
        }
    }

}

class GetPokemon(
    private val basePokemonRepository: BasePokemonRepository
) : KoinComponent {
    suspend fun invoke(): Ressource<BasePokemonDTO> {
        return try{
             Ressource.Success(basePokemonRepository.getPokemon())
        }
        catch (e : Exception){
            Ressource.Error(e)
        }
    }

}