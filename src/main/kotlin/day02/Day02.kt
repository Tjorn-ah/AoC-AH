package day02

import readInput
import println

fun main() {
    fun List<Int>.isSafeAscending(): Boolean {
        return this.zipWithNext().all { (it.first > it.second) && (kotlin.math.abs(it.first - it.second) in 1..3) }
    }

    fun List<Int>.isSafeDescending(): Boolean {
        return this.zipWithNext().all { (it.first < it.second) && (kotlin.math.abs(it.first - it.second) in 1..3) }
    }

    fun part1(input: List<List<Int>>): Pair<Int, List<List<Int>>> {
        var safeCount = 0
        val toRemove : MutableList<List<Int>> = mutableListOf()
        input.onEach {
            if (it.isSafeAscending() || it.isSafeDescending()) {
                safeCount++
                toRemove.add(it)
            }
        }

        // Solution for part 1
        return Pair(safeCount,  input.filterNot { it in toRemove })
    }

    fun part2(input: List<List<Int>>): Int {
        var (safeCount, inputFixed) = part1(input)

        inputFixed.forEach {
            for(i in it.indices){
                val listWithOneRemoved: List<Int> = it.toMutableList().apply { removeAt(i) }.toList()
                    if (listWithOneRemoved.isSafeAscending() || listWithOneRemoved.isSafeDescending()) {
                        safeCount++
                        break
                    }
            }
        }

        // Solution for part 2
        return safeCount
    }

    fun parseInput(input: List<String>): List<List<Int>> {
        val array = input.map { it.split(" ").map { it2 -> it2.toInt() } }
        return array
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day02", "day02_test")
    val parsedTestInput = parseInput(testInput)
    val testAnswerPartOne = 2
    val testAnswerPartTwo = 4
    check(part1(parsedTestInput).first == testAnswerPartOne) { "answer to test one is wrong" }
    check(part2(parsedTestInput) == testAnswerPartTwo) { "answer to test two is wrong" }

    val input = readInput("day02", "day02")
    val parsedInput = parseInput(input)
    part1(parsedInput).println()
    part2(parsedInput).println()
}
