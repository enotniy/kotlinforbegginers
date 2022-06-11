package ru.ekitselyuk.lib.patterns

interface AbstractFactory<T> {
    fun create(carType: String?): T
}

class Test(val str: String) {

    inner class InnerTest() {

        init {
            str
        }

    }
}




data class BMW: Car {

    var name = "BMW"
    override fun ride(name: String?) {
    }

    override fun stop(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getFluel(): Float {
        TODO("Not yet implemented")
    }
}

class Lada: Car {
    override fun ride(name: String?) {
    }

    override fun stop(name: String?) {
        TODO("Not yet implemented")
    }
}


fun main() {

    val list = ArrayList<Int>()
    val list2 = listOf<Int>()
}

class CarFactory : AbstractFactory<Car?> {

    override fun create(carType: String?): Car? {
        if ("BMW".equals(carType, ignoreCase = true)) {
            return BMW()
        } else if ("Lada".equals(carType, ignoreCase = true)) {
            return Lada()
        }
        return null
    }

}