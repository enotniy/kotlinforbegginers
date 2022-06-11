package ru.ekitselyuk.lib

class Cat

fun main() {
    println(solve(arrayOf(1, 2, 4, 5, 6, 7, 8, 9, 10, 9)))
}


fun solve(array: Array<Int>): Int {
    return array.sum() - IntRange(1, 10).sum()
}