import java.lang.Math.abs
import java.util.*

fun main() {

    fun part1() {
        Scanner(System.`in`).use { scanner ->
            val numbers = scanner.nextLine()
                .split(",")
                .map { it.toInt() }
            val median = numbers.sortedDescending()[numbers.size / 2 ]
            var cost = 0
            numbers.map {
                cost += kotlin.math.abs(it - median)
            }
            println(cost)
        }
    }

    fun part2() {
        Scanner(System.`in`).use { scanner ->
            val numbers = scanner.nextLine()
                .split(",")
                .map { it.toInt() }
            var lowestCost = Int.MAX_VALUE

            val maxIteration = numbers.size / 2

            for(j in numbers.toSet()) {
                for(i in 1..maxIteration) {
                    var costPlus = 0
                    var costMinus = 0
                    numbers.map {
                        val d = 1
                        val a = 1
                        val nPlus = kotlin.math.abs(it - (j + 1))
                        val nMinus = kotlin.math.abs(it - (j - 1))
                        costPlus += (2 * a + (nPlus - 1) * d) * nPlus / 2
                        costMinus += (2 * a + (nMinus - 1) * d) * nMinus / 2
                    }

                    if(costPlus <= lowestCost) { lowestCost = costPlus }
                    if(costMinus <= lowestCost) { lowestCost = costMinus }
                }
            }

            println(lowestCost)
        }
    }

    part2()
}

