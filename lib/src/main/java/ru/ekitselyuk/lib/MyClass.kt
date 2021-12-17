package ru.ekitselyuk.lib

import java.io.File
import java.nio.file.Paths

var lines: List<List<Long>> = listOf()
var results = mutableListOf<Long>()
var result = ""

fun main() {

    var position = 0 to 0
    File(Paths.get("advent_6.test.txt").toAbsolutePath().toString())
    //File(Paths.get("advent_1.test.txt").toAbsolutePath().toString())
        .readLines()
        .first()
        .forEach {
            position = when(it) {
                'L' -> position.copy(first = position.first - 1)
                'R' -> position.copy(first = position.first + 1)
                'D' -> position.copy(second = position.second - 1)
                'U' -> position.copy(second = position.second + 1)
                else -> throw Exception()
            }
        }

    var path = ""

    if (position.second < 0) {
        repeat(position.second * -1) { path += 'D' }
    }
    if (position.first < 0) {
        repeat(position.first * -1) { path += 'L' }
    }
    if (position.first > 0) {
        repeat(position.first) { path += 'R' }
    }
    if (position.second > 0) {
        repeat(position.second ) { path += 'U' }
    }

    println(position)
    println(path)
}