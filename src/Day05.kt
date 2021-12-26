import java.lang.Math.max
import java.lang.Math.min
import java.util.*

fun main() {
    class HydrothermalBoard(data: MutableList<String>, diagonal: Boolean = false) {
        private var diagram : Array<Array<Int>>

        fun printOverlaps() {
            var overlaps = 0
            diagram.map {
                it.map { n ->
                    if(n >= 2) overlaps++
                }
            }
            println("Overlaps: $overlaps")
        }

        override fun toString(): String {
            var result = ""

            diagram.map {
                it.map { n ->
                    result += if(n == 0) "." else n
                }
                result += "\n"
            }

            return result
        }

        init {
            val numbers = mutableListOf<Int>()
            data.map { row ->
                row.split(" -> ").map { coordinates ->
                    coordinates.split(",").map {
                        numbers.add(it.toInt())
                    }
                }
            }
            val max = numbers.maxOrNull() ?: numbers[0]
            diagram = Array(max + 1) {_ -> Array(max + 1) {_ -> 0} }

            data.map { row ->
                val coordinatePairString = row.split(" -> ")
                val x1 = coordinatePairString[0].split(",")[0].toInt()
                val y1 = coordinatePairString[0].split(",")[1].toInt()
                val x2 = coordinatePairString[1].split(",")[0].toInt()
                val y2 = coordinatePairString[1].split(",")[1].toInt()

                if(diagonal) {
                    if(x1 == x2) {
                        for(i in y1.coerceAtMost(y2)..y1.coerceAtLeast(y2)) {
                            diagram[i][x1] += 1
                        }
                        println(row)
                    }
                    if (y1 == y2) {
                        for (i in x1.coerceAtMost(x2)..x1.coerceAtLeast(x2)) {
                            diagram[y1][i] += 1
                        }
                        println(row)
                    }
                    if (x1 == y1 && x2 == y2 ||
                        (x1.coerceAtLeast(x2) - x1.coerceAtMost(x2) == y1.coerceAtLeast(y2) - y2.coerceAtMost(y1))
                    ) {
                        if (x2 > x1 && y2 > y1) { // down-right
                            val length = x1.coerceAtLeast(x2) - x1.coerceAtMost(x2)
                            for(i in 0..length) {
                                val x = x1.coerceAtMost(x2) + i
                                val y = y2.coerceAtMost(y1) + i
                                diagram[x][y] += 1
                            }
                            println(row)
                        }
                        if (x1 > x2 && y1 > y2) { // up-left
                            val length = x1.coerceAtLeast(x2) - x1.coerceAtMost(x2)
                            for(i in 0..length) {
                                val x = x1.coerceAtLeast(x2) - i
                                val y = y1.coerceAtLeast(y2) - i
                                diagram[x][y] += 1
                            }
                            println(row)
                        }
                    }
                    if (x1 == y2 && y1 == x2 ||
                        (x1.coerceAtLeast(x2) - x1.coerceAtMost(x2) == y1.coerceAtLeast(y2) - y2.coerceAtMost(y1))
                    ) {
                        if (x2 > x1 && y1 > y2) { // up-right
                            val length = x1.coerceAtLeast(x2) - x1.coerceAtMost(x2)
                            for(i in 0..length) {
                                val x = x1.coerceAtMost(x2) + i
                                val y = y1.coerceAtLeast(y2) - i
                                diagram[x][y] += 1
                            }
                            println(row)
                        }
                        if (x1 > x2 && y2 > y1) { // down-left
                            val length = x1.coerceAtLeast(x2) - x1.coerceAtMost(x2)
                            for(i in 0..length) {
                                val x = x1.coerceAtLeast(x2) - i
                                val y = y2.coerceAtMost(y1) + i
                                diagram[x][y] += 1
                            }
                            println(row)
                        }
                    }

                } else {
                    if(x1 == x2) {
                        for(i in y1.coerceAtMost(y2)..y1.coerceAtLeast(y2)) {
                            diagram[i][x1] += 1
                        }
                    }
                    else if (y1 == y2) {
                        for(i in x1.coerceAtMost(x2)..x1.coerceAtLeast(x2)) {
                            diagram[y1][i] += 1
                        }
                    }
                }


            }
        }
    }

    fun part1() {
        val rows = mutableListOf<String>()
        Scanner(System.`in`).use {
            while (it.hasNextLine()) {
                val line = it.nextLine()
                if(line.isNullOrBlank() || line.trim().contains("q")) {
                    break
                }
                rows.add(line)
            }
        }

        val board = HydrothermalBoard(rows, false)
        println(board)
        board.printOverlaps()
    }

    fun part2() {
        val rows = mutableListOf<String>()
        Scanner(System.`in`).use {
            while (it.hasNextLine()) {
                val line = it.nextLine()
                if(line.isNullOrBlank() || line.trim().contains("q")) {
                    break
                }
                rows.add(line)
            }
        }

        val board = HydrothermalBoard(rows, true)
        // println(board) // if the input is short
        board.printOverlaps()
    }

    part2()
}