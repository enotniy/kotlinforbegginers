package ru.ekitselyuk.lib.patterns


fun main() {
    println("Start")
}

interface Car {
    fun ride(name: String? = null)
    fun stop(name: String? = null)

    fun getFluel(): Float
}

class CarDriver(private val car: Car) : Car {
    override fun ride(name: String?) {
        car.ride(name)
    }

    override fun stop(name: String?) {
        car.stop(name)
    }

    override fun getFluel(): Float {
        TODO("Not yet implemented")
    }
}