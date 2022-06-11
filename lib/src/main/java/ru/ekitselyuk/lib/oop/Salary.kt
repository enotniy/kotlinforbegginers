package ru.ekitselyuk.lib.oop




class SalaryGiver {

    private var tax = 0.13

    fun paySalary(worker: Worker) {
        println("${worker.name}: ${getSalaryWithTax(worker)}")
    }

    private fun getSalaryWithTax(worker: Worker): Double {
        return worker.salary - (worker.salary * tax)
    }
}

class Worker(val name: String, val salary: Double)

fun main() {

    val workers = listOf(
        Worker("Вася", 100.0),
        Worker("Петя", 200.0)
    )

    val giver = SalaryGiver()

    workers.forEach { worker ->
        //if (worker.name == "Вася") giver.tax = 0.0 else giver.tax = 0.13
        giver.paySalary(worker)
    }

}