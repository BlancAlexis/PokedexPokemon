package com.example.pokedexpokemon.domainLayer.usecase

import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import org.koin.core.component.KoinComponent

class GetPokemon(
    private val basePokemonRepository: BasePokemonRepository
) : KoinComponent {
    suspend fun invoke(index : String): Ressource<BasePokemonDTO> {
        return try{
            Ressource.Success(basePokemonRepository.getPokemon(index))
        }
        catch (e : Exception){
            Ressource.Error(e)
        }
    }

}