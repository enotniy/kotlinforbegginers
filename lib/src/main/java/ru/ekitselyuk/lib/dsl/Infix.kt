package ru.ekitselyuk.lib.dsl

import ru.ekitselyuk.lib.patterns.Car


sealed class Test {

}

data class Tesla(var number: String? = null,
                 var type: String? = null): Car {

    override fun ride(name: String?) {
        TODO("Not yet implemented")
    }

    override fun stop(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getFluel(): Float = TODO()
}


infix fun Car.go(string: String): Car {
    return this.apply { ride(string) }
}

infix fun Pair<Int, Int>.to(third: Int): Triple<Int, Int, Int> {
    return Triple(first, second, third)
}

fun main () {
    //val pair: Pair<Int, Int> = Pair(1, 2)
    val pair: Pair<Int, Int> = 1 to 2
    val triple: Triple<Int, Int, Int> = 1 to 2 to 3
    val (a,b) = 1 to 2


    listOf<Pair<Int, Int>>().forEach {  (a: Int, b: Int) ->

    }

    Tesla() go "home"
    Tesla().go("home")
    Tesla().ride("home")

    val (number, type) = Tesla("1", "S")
}