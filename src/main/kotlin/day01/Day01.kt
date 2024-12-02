package day01

import println
import readInput

fun main() {
    fun part1(leftColumn: List<Int>, rightColumn: List<Int>): Int {
        // Solution for part 1
        return kotlin.math.abs(leftColumn.sum() - rightColumn.sum())
    }

    fun part2(leftColumn: List<Int>, rightColumn: List<Int>): Int {
        val rightColumnOccurrences: Map<Int, Int> = rightColumn.groupingBy{it}.eachCount()
        val score = leftColumn.mapNotNull{ rightColumnOccurrences[it]?.times(it) }
        // Solution for part 2
        return score.sum()
    }

    fun sortColumns(inputToSort: List<String>) :  Pair<List<Int>, List<Int>>{
        val left = inputToSort.map { line -> line.split(" ").first().toInt() }.sortedDescending().reversed()
        val right = inputToSort.map { line -> line.split(" ").last().toInt() }.sortedDescending().reversed()
        return Pair(left, right)
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day01", "day01_test")
    val (testLeftColumn, testRightColumn) = sortColumns(testInput)
    val testAnswer1 = 11
    val testAnswer2 = 31
    check(part1(testLeftColumn, testRightColumn) == testAnswer1) { "answer to test1 is wrong" }
    check(part2(testLeftColumn, testRightColumn) == testAnswer2) { "answer to test2 is wrong" }

    val input = readInput("day01", "day01")

    val (leftColumn, rightColumn) = sortColumns(input)

    part1(leftColumn, rightColumn).println()
    part2(leftColumn, rightColumn).println()
}
