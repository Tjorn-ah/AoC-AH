package day05

import readInput
import println

fun main() {
    fun part1(pageOrderRules : List<String>, pages : List<String>): Int {
        val correctIndexes : MutableList<Int> = mutableListOf()

        for (line in pages.indices) {
            val pagesToCheck : Pair<Int, Int> = pageOrderRules[line].split("|").map { it.toInt() }.let { it[0] to it[1] }
            if (pages.indexOf(pagesToCheck.first.toString()) > pages.indexOf(pagesToCheck.second.toString())) {
                correctIndexes.add(line)
            }
        }
        // get the middle number of the correct indexes
        for (i in correctIndexes) {
            // get the middle
            val middle = (pages[i] / 2)
            // todo, parse the pages to get the middle int
        }
        return 10
    }

    fun part2(input: List<String>): Int {
        // Solution for part 2
        return input.size
    }

    fun splitParts(input: List<String>): Pair<List<String>, List<String>> {
        val splitIndex = input.indexOf("")
        return input.take(splitIndex) to input.drop(splitIndex + 1)
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day05", "day05_test")
    val testInputSplit = splitParts(testInput)
    val testAnswerPartOne = 143
    val testAnswerPartTwo = 0
    check(part1(testInputSplit.first, testInputSplit.second) == testAnswerPartOne) { "answer to test one is wrong" }
    check(part2(testInput) == testAnswerPartTwo) { "answer to test two is wrong" }

    val input = readInput("day05", "day05")
    //part1(input).println()
    part2(input).println()
}
