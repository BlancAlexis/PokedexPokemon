# PokedexPokemon
<p align="center">
  <img width="460" height="300" src="https://github.com/user-attachments/assets/89b197f2-401c-4213-8aaf-fc83de784dbc">
</p>

Ce projet Android natif est une application de Pokédex conçue pour démontrer mes compétences en développement Android, en mettant en œuvre mes compétences acquises à travers d'autres projets.

## Description

PokedexPokemon permet de consulter la liste des pokémons présent aujourd'hui en les récupérants de manières paginée, une page spécial permet de connaître toutes les stastiques a connaitre sur lui ainsi que même entrendre son cri.
Également il est aussi possible de consulter l'entiereté des cartes sur lequels le pokémon y figure, de manière paginée également.

Par la suite j'ai prévu d'implémenter un système de "deck", où l'utilisateur pourra se constituer puis sauvegarder des cartes qu'il possède dans un ou plusieurs decks

## Fonctionnalités

* SplashScreen animé avec condition
* Lazy loading data depuis une api
* Listener d'état connexion internet
* Mise en base de donnée locale
* Lecture GIF
* 
## Architecture

*   Clean Architecture
*   MVVM
  
## Technologies utilisées

*   Kotlin
*   Compose (LazyColumn, LazyGrid, Composant custom...)
*   Koin (injection dépendance ViewModel, autre ...)
*   Retrofit (sealed class Result, Coroutine, DTO)
*   Room (sealed class Result, Flow, Entity) 
*   Paging 3 (lazy loading)
*   Coil (lazy loading gif)
  
<p align="center">
<img src="https://github.com/user-attachments/assets/904572cd-3793-48c6-be06-96e5e33855cc" width="10%" height="10%" />
<img src="https://github.com/user-attachments/assets/fe88a561-c53d-4f4e-9c0a-2e6bca195c7b" width="10%" height="10%" />
<img src="https://github.com/user-attachments/assets/67e4af9e-dea2-49aa-b80a-fb27c73cb277" width="10%" height="10%" />
<img src="https://github.com/user-attachments/assets/9aef9bfd-34a2-49dd-8cbf-ae03c93ce2cb" width="10%" height="10%" />
<img src="https://github.com/user-attachments/assets/e8fe75a0-55cf-4cca-8542-1f138ab7d0bb" width="10%" height="10%" /> 
<img src="https://github.com/user-attachments/assets/103ea153-3878-46c6-9914-2e5e69be619b" width="10%" height="10%" />
<img src="https://github.com/user-attachments/assets/ec1d48c7-8fdd-43fa-8510-9cf4170951e9" width="10%" height="10%" />
</p>
