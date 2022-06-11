package ru.ekitselyuk.lib

open class Game

open class Shooter : Game()
open class Strategy : Game()
open class Sport : Game()

class CounterStrike : Shooter()
class Fortnite : Shooter() {
    fun test() {}
}
class Pubg : Shooter()

class Fifa: Sport()

fun addToCollection(steam: Steam<in Shooter>)  {
}

fun main() {
    addToCollection(Steam<Game>(Game()))
    addToCollection(Steam<Shooter>(Shooter()))
   // addToCollection(Steam<Fortnite>(Fortnite()))
}

class XBox {
    fun <T : Shooter> install(program: T) {}
}

open class Steam<T>(var game: T?) {
}


fun erasing() {

    val counterStrikes: List<CounterStrike> = ArrayList()
    val fortnites: List<Fortnite> = ArrayList()
    val pubgs: List<Pubg> = ArrayList()

    println(counterStrikes.javaClass == fortnites.javaClass)
    println(pubgs.javaClass == counterStrikes.javaClass)
}