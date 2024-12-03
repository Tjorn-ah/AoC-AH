package day03

import readInput
import println

fun main() {

    fun useRegexOnlyMull(input: String): List<String> {
        val regex = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)")
        return regex.findAll(input).map { it.value }.toList()
    }

    fun useRegexDoDont(input: String): List<String> {
        val regex = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)|don't|do")
        return regex.findAll(input).map { it.value }.toList()
    }

    fun part1(input: List<String>): Int {
        val regex = Regex(pattern = "[0-9]+")
        val intlist = input.map { regex.findAll(it).map { it2 -> it2.value.toInt() }.toList() }
        // Solution for part 1
        return intlist.sumOf { it.reduce { acc, i -> acc * i } }
    }

    fun part2(input: List<String>): Int {
        val splitRegex = Regex(pattern = "don't.*?do(?!n't)")
        val removedDont = splitRegex.replace(input.joinToString(), "")
        // Solution for part 2
        return part1(useRegexOnlyMull(removedDont))
    }


    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day03", "day03_test").joinToString()
    val mulTestInput = useRegexOnlyMull(testInput)
    val mulTestInputDoDont = useRegexDoDont(testInput)
    val testAnswerPartOne = 161
    val testAnswerPartTwo = 48
    check(part1(mulTestInput) == testAnswerPartOne) { "answer to test one is wrong" }
    check(part2(mulTestInputDoDont) == testAnswerPartTwo) { "answer to test two is wrong" }

    val input = readInput("day03", "day03").joinToString()
    val mulInput = useRegexOnlyMull(input)
    val mullInputDoDont = useRegexDoDont(input)
    part1(mulInput).println()
    part2(mullInputDoDont).println()
}
