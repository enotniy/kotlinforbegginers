package ru.ekitselyuk.lib

import java.io.File
import java.nio.file.Paths

var matrix: Array<Array<Int>> = arrayOf()

fun main() {

    val sizes = File(Paths.get("advent_10.test.txt").toAbsolutePath().toString())
        .readLines()
        .drop(1)
        .first()
        .split(" ")
        .map { it.toInt() }
        .also { m ->
            matrix = Array(m.size) { x -> Array(m.size) { y -> if (x == y) m[x] else 0 } }
        }

    for (end in sizes.indices) {
        for (start in end - 1 downTo 0) {
            matrix[start][end] = minOf(matrix[start + 1][end], matrix[start][end - 1])
        }
    }

    var max = 0
    sizes.indices.forEach { x ->
        sizes.indices.forEach { y ->
            max = maxOf(matrix[x][y] * (y - x + 1), max)
        }
    }
    println(max)
}