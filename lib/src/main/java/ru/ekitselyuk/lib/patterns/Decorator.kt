package ru.ekitselyuk.lib.patterns

import java.sql.Wrapper

fun main() {
    println("Start")
}

class CarDecorator(private val car: Car) : Car {

    var onWayTime: Long = 0L
    var start: Long = 0L

    override fun ride(name: String?) {
        car.ride(name)
        start = System.nanoTime()
    }

    override fun stop(name: String?) {
        car.stop(name)
        onWayTime = System.nanoTime() - start
    }
}