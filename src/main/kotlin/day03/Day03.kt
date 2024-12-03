package day03

import readInput
import println

fun main() {

    fun part1(input: List<String>): Int {
        val regex = Regex(pattern = "[0-9]+")
        val intlist = input.map { regex.findAll(it).map { it2 -> it2.value.toInt() }.toList()}
        // Solution for part 1
        return intlist.sumOf { it.reduce { acc, i -> acc * i  } }
    }

    fun part2(input: List<String>): Int {
        // Solution for part 2
        return 0
    }

    fun useRegex(input: String): List<String> {
        val regex = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)")
        return  regex.findAll(input).map { it.value }.toList()
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day03", "day03_test").joinToString()
    val mulTestInput = useRegex(testInput)
    val testAnswerPartOne = 161
    val testAnswerPartTwo = 0
    check(part1(mulTestInput) == testAnswerPartOne) { "answer to test one is wrong" }
    check(part2(mulTestInput) == testAnswerPartTwo) { "answer to test two is wrong" }

    val input = readInput("day03", "day03").joinToString()
    val mulInput = useRegex(input)
    part1(mulInput).println()
    part2(mulInput).println()
}
