package ru.ekitselyuk.lib.patterns

interface ElementForVisit {
    fun accept(v: Visitor)
}

class Visitor {
    fun visit(element: ElementForVisit) {
        println("Check " + element.javaClass.simpleName)
    }
}

class Engine : ElementForVisit {
    override fun accept(v: Visitor) {
        v.visit(this)
    }
}

class Wheels : ElementForVisit {
    override fun accept(v: Visitor) {
        v.visit(this)
    }
}

class Driver :ElementForVisit {
    override fun accept(v: Visitor) {
        v.visit(this)
    }
}

fun main() {
    val visitor = Visitor()

    val list = listOf<ElementForVisit>(Engine(), Driver(), Wheels())

    list.forEach { element -> visitor.visit(element) }




}