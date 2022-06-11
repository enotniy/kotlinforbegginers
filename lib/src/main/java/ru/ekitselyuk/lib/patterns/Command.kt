package ru.ekitselyuk.lib.patterns

interface CarCommand {
    fun execute()
}

class RideCarCommand(private val car: Car, private val name: String): CarCommand {
    override fun execute() {
        car.stop(name)
    }
}

class StopCarCommand(private val car: Car, private val name: String): CarCommand {
    override fun execute() {
        car.stop(name)
    }
}

fun main() {
    val car = BMW()
    val commands = listOf(
        RideCarCommand(car, "1"),
        StopCarCommand(car, "1"),
        RideCarCommand(car, "2"),
        StopCarCommand(car, "2"),
    )

    for(command in commands) {
        Thread.sleep(1000)
        command.execute()
    }
}