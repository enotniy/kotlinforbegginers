package ru.ekitselyuk.lib.delegate

import ru.ekitselyuk.lib.patterns.BMW
import ru.ekitselyuk.lib.patterns.Car

class Trailer: Car {
    override fun ride(name: String?) {
    }

    override fun stop(name: String?) {
    }

    override fun getFluel(): Float {
        TODO("Not yet implemented")
    }
}


class CarWithTrailer(private val baseCar: BMW): Car by baseCar {
    private val trailer = Trailer()

    override fun ride(name: String?) {
        baseCar.ride(name)
        trailer.ride(name)
    }

    override fun stop(name: String?) {
        baseCar.stop(name)
        trailer.stop(name)
    }
}

fun main() {
    val bmw = BMW()
    val carWithTrailer = CarWithTrailer(bmw)

    carWithTrailer.ride("test")
    carWithTrailer.stop("test")
}