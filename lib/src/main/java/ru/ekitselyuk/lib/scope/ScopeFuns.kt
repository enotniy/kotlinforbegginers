package ru.ekitselyuk.lib.scope

import ru.ekitselyuk.lib.delegate.CarWithTrailer
import ru.ekitselyuk.lib.patterns.BMW
import ru.ekitselyuk.lib.patterns.Car


fun main() {

    var carLet: Car? = BMW()
//
//    carLet?.ride("test")
//    carLet?.stop("test")
//
//    if (carLet != null) {
//        carLet.ride("test")
//        carLet.stop("test")
//    }


    val bool = carLet?.let { carNotNull ->
        carNotNull.ride("test")
        carNotNull.stop("test")
        return@let true
    }

//    val carApply = BMW()
//    carApply.name = "BMW X5"

    val carApply = BMW().apply {
        name = "BMW X5"
       // return@apply this
    }
//    val carAlso = BMW()
//    carAlso.name = "BMW X5"

    val carAlso = BMW().also {
        it.name = "BMW X5"
        //return@also it
    }

    val carRun = BMW().run {
        name = "BMW X5"
        CarWithTrailer(this)
    }

    val carRun2 = run {
        val car = BMW()
        car.name = "BMW X5"
        CarWithTrailer(car)
    }

    val carWith = with(BMW()) {
        name = "BMW X5"
    }
}