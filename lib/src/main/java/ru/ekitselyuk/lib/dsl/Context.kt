package ru.ekitselyuk.lib.dsl


@DslMarker
annotation class MyCustomDslMarker

class DataContext {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Incorrect context")
    fun data(init: DataContext.() -> Unit) {}
}

@MyCustomDslMarker
class SchedulingContext { }

object Schedule {

    operator fun invoke(init: SchedulingContext.() -> Unit)  {
        SchedulingContext().init()
    }
}

class MyContext

fun  main() {

    val x : MyContext.() -> Unit = {}
    //x()

    val c = MyContext()
    c.x()
    x(c)

}