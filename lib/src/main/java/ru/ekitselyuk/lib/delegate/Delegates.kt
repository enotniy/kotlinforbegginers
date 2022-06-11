package ru.ekitselyuk.lib.delegate

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

val lazy: String by lazy { "test" }
val readOnly: String by ReadOnlyProperty { thisRef, property -> "test" }

val readWrite: String by resourceDelegate()

fun resourceDelegate(): ReadWriteProperty<Any?, String> =
    object : ReadWriteProperty<Any?, String> {
        var curValue = "test"
        override fun getValue(thisRef: Any?, property: KProperty<*>): String = curValue
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            curValue = value
        }
    }

val readWriteObject: String by MyStringDelegate()

class MyStringDelegate {
    operator fun <T> getValue(thisRef: Any?, property: KProperty<*>): T = TODO()
    operator fun <T> setValue(thisRef: Any?, property: KProperty<*>, value: T): Unit = TODO()
}