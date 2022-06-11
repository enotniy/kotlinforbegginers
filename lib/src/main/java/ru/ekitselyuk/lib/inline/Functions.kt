package ru.ekitselyuk.lib.inline

fun String.firstOrNullByCondition(condition: (Char) -> Boolean): Char? {
    for (element in this) {
        if (condition(element))  { return element }
    }
    return null
}

inline fun String.firstOrNullByConditionInline(condition: (Char) -> Boolean): Char? {
    for (element in this) {
        if (condition(element))  { return element }
    }
    return null
}

fun testFun(block: () -> Int) = block()

inline fun inlineTestFun (block: () -> Unit) = block()

inline fun crossInlineTestFun(crossinline block: () -> Int) = block()

fun main(): Unit {

    val result = "listOf<String>().".firstOrNull()?.let { first ->
        println(first)
        "OK"
    } ?: run {
        println("null")
        "NO OK"
    }

    with(listOf<String>()) {
        first()
    }



    val res = inlineTestFun {
        for(i in 0..10) {
            print("test")
            return
        }
        return@inlineTestFun
    }

    crossInlineTestFun {
        for(i in 0..10) {
            print("test")
            return@crossInlineTestFun 1
        }
        return@crossInlineTestFun 0
    }

    testFun {
        for(i in 0..10) {
            print("test")
            return@testFun 1
        }
        return@testFun 0
    }
}