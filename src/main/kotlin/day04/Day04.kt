package day04

import readInput
import println

fun main() {
    fun validCoord(x: Int, y: Int, m: Int, n: Int): Boolean {
        return x >= 0 && x < m && y >= 0 && y < n
    }

    fun findWord(index: Int, word: String, grid: Array<CharArray>, x: Int, y: Int, dirX: Int, dirY: Int): Boolean {
        if (index == word.length) return true

        if (validCoord(x, y, grid.size, grid[0].size) &&
            word[index] == grid[x][y]
        )
            return findWord(
                index + 1, word, grid,
                x + dirX, y + dirY, dirX, dirY
            )

        return false
    }

    fun searchWord(
        grid: Array<CharArray>,
        word: String,
        directions: Pair<IntArray, IntArray>,
        unique: Boolean = false
    ): ArrayList<IntArray> {
        val m: Int = grid.size
        val n: Int = grid[0].size

        val ans: ArrayList<IntArray> = ArrayList()

        val x: IntArray = directions.first
        val y: IntArray = directions.second

        for (i in 0 until m) {
            for (j in 0 until n) {
                // Search in all directions
                for (k in 0 until x.size) {
                    if (findWord(0, word, grid, i, j, x[k], y[k])) {
                        ans.add(intArrayOf(i, j))
                        if (unique) break
                    }
                }
            }
        }
        return ans
    }

    fun initializeGrid(input: List<String>): Array<CharArray> {
        val m = input.size
        val n = input[0].length // all lines are of same length
        val grid = Array(m) { CharArray(n) }

        // initialize grid with input
        for (i in 0 until m) {
            for (j in 0 until n) {
                grid[i][j] = input[i][j]
            }
        }

        return grid
    }

    fun getAllLetterLocations(grid: Array<CharArray>, letter: Char): ArrayList<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val ans : ArrayList<IntArray> = ArrayList()

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == letter) {
                    ans.add(intArrayOf(i, j))
                }
            }
        }
        return ans
    }

    fun crossCount (grid : Array<CharArray>, aLocations: ArrayList<IntArray>, mLocations: ArrayList<IntArray>, sLocations: ArrayList<IntArray>, directions: Pair<IntArray, IntArray>): Int {
        var count = 0
        // TODO in each a location (x and y coordinates), check directions if there is a M and S in the same direction
        // if there is, increment count


        return count
    }


    fun part1(input: List<String>): Int {
        val grid = initializeGrid(input)
        val word = "XMAS"

        val x: IntArray = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val y: IntArray = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
        val directions = Pair(x, y)
        val ans = searchWord(grid, word, directions)

        // Solution for part 1
        return ans.size
    }

    fun part2(input: List<String>): Int {
        val grid = initializeGrid(input)
        val x: IntArray = intArrayOf(-1, -1, 1, 1)
        val y: IntArray = intArrayOf(-1, 1, -1, 1)
        val directions = Pair(x, y)

        val aLocations = getAllLetterLocations(grid, 'A')
        val mLocations = getAllLetterLocations(grid, 'M')
        val sLocations = getAllLetterLocations(grid, 'S')


        // Solution for part 2
        return ans.size
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day04", "day04_test")
    val testAnswerPartOne = 18
    val testAnswerPartTwo = 9
    check(part1(testInput) == testAnswerPartOne) { "answer to test one is wrong" }
    check(part2(testInput) == testAnswerPartTwo) { "answer to test two is wrong" }

    val input = readInput("day04", "day04")
    part1(input).println()
    part2(input).println()
}
