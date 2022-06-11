package ru.ekitselyuk.lib.patterns

data class Tesla(var number: String? = null,
                 var type: String? = null): Car {

    override fun ride(name: String?) {
        TODO("Not yet implemented")
    }

    override fun stop(name: String?) {
        TODO("Not yet implemented")
    }
}

class TeslaView {
    fun printStudentDetails(model: String?, number: String?) {
        println("------\nTesla: ")
        println("Name: $model")
        println("Number: $number")
    }
}

class TeslaController(val model: Tesla, val view: TeslaView) {
    var teslaName: String?
        get() = model.type
        set(value) {
            model.type = value
            updateView()
        }

    var teslaRollNo: String?
        get() = model.number
        set(value) {
            model.number = value
            updateView()
        }

    fun updateView() {
        view.printStudentDetails(model.type, model.number)
    }
}

fun main(args: Array<String>) {

    val model = retriveStudentFromDatabase()

    val view = TeslaView()
    val controller = TeslaController(model, view)

    //update model data
    controller.teslaName = "X"

    controller.teslaName = "S"
}

private fun retriveStudentFromDatabase(): Tesla {
    val student = Tesla()
    student.type = "S"
    student.number = "1123-123"
    return student
}