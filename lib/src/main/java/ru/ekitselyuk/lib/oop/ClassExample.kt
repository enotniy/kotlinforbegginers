package ru.ekitselyuk.lib.oop

import java.lang.Error



fun main() {

    listOf<String>().plus("")
    listOf<String>().plus(listOf("", ""))

    listOf<CharSequence>(("sdfsdf").toString())

}

class Test(param1: String = "test", param2: String = "test2")
