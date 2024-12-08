package day05

import readInput
import println

fun main() {
    fun convertRules(rules: List<String>) = rules.map {
        val (first, second) = it.split("|").map(String::toInt)
        first to second
    }

    fun part1(pageOrderRules: List<String>, pages: List<String>): Int {
        val convertedRules = convertRules(pageOrderRules)
        val correctIndexes = pages.withIndex().filter { (_, line) ->
            val lineNumbers = line.split(",").map(String::toInt)
            convertedRules.all { (first, second) ->
                val firstIndex = lineNumbers.indexOf(first)
                val secondIndex = lineNumbers.indexOf(second)
                firstIndex == -1 || secondIndex == -1 || firstIndex <= secondIndex
            }
        }.map { it.index }

        val pagesList = pages.map { it.split(",").map(String::toInt) }
        return correctIndexes.sumOf { pagesList[it][pagesList[it].size / 2] }
    }

    fun reorderUpdate(update: List<Int>, pageOrderRules: List<Pair<Int, Int>>): List<Int> {
        val updatePages = update.toMutableList()
        var reordered: Boolean
        do {
            reordered = false
            for ((before, after) in pageOrderRules) {
                val beforeIndex = updatePages.indexOf(before)
                val afterIndex = updatePages.indexOf(after)
                if (beforeIndex != -1 && afterIndex != -1 && beforeIndex > afterIndex) {
                    updatePages.removeAt(afterIndex)
                    updatePages.add(beforeIndex, after)
                    reordered = true
                }
            }
        } while (reordered)
        return updatePages
    }

    fun part2(pageOrderRules: List<String>, pages: List<String>): Int {
        val convertedRules = convertRules(pageOrderRules)
        val incorrectIndexes = pages.withIndex().filter { (_, line) ->
            val lineNumbers = line.split(",").map(String::toInt)
            convertedRules.any { (first, second) ->
                val firstIndex = lineNumbers.indexOf(first)
                val secondIndex = lineNumbers.indexOf(second)
                firstIndex != -1 && secondIndex != -1 && firstIndex > secondIndex
            }
        }.map { it.index }

        val pagesList = pages.map { it.split(",").map(String::toInt) }
        return incorrectIndexes.sumOf {
            val reordered = reorderUpdate(pagesList[it], convertedRules)
            reordered[reordered.size / 2]
        }
    }

    fun splitParts(input: List<String>): Pair<List<String>, List<String>> {
        val splitIndex = input.indexOf("")
        return input.take(splitIndex) to input.drop(splitIndex + 1)
    }

    val testInput = readInput("day05", "day05_test")
    val (testRules, testPages) = splitParts(testInput)
    check(part1(testRules, testPages) == 143) { "answer to test one is wrong" }
    check(part2(testRules, testPages) == 123) { "answer to test two is wrong" }

    val input = readInput("day05", "day05")
    val (rules, pages) = splitParts(input)
    part1(rules, pages).println()
    part2(rules, pages).println()
}