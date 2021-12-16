package ru.ekitselyuk.lib

import java.io.File
import java.nio.file.Paths

var lines: List<List<Long>> = listOf()
var results = mutableListOf<Long>()
var result = ""

fun main() {

    lines = File(Paths.get("advent_5.test.txt").toAbsolutePath().toString())
    //lines = File(Paths.get("advent_1.test").toAbsolutePath().toString())
        .readLines()
        .drop(1)
        .map {
            it.split(" ")
                .map { num -> num.toLong() }
        }
    next(0, "")

    println(results.sorted().take(1234567))
    println(results.sorted()[1234567 - 1])
}

fun next(line: Int, x: String) {
    if (line >= lines.size) {
        return
    }

    for (num in lines[line]) {
        val res = x + num
        if (line == lines.size - 1) {
            results.add(res.toLong())
        } else {
            next(line + 1, res)
        }
    }
}