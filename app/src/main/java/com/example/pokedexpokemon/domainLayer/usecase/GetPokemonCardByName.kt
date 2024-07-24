package com.example.pokedexpokemon.domainLayer.usecase

import com.example.pokedexpokemon.dataLayer.dto.PokemonCard
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.repository.CardPokemonRepository

class GetPokemonCardByNameUseCase(private val pokemonRepository: CardPokemonRepository) {

    suspend operator fun invoke(option: Map<String,String>): Ressource<List<PokemonCard>> {
        return try{
            Ressource.Success(pokemonRepository.getPokemonByName(option+("pageSize" to "$pageSize")))
        }
        catch (e : Exception){
            Ressource.Error(e)
        }
    }

    final val  pageSize : Int  = 20
}