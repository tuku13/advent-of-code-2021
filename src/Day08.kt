import java.util.*

fun main() {
    fun part1() {
        var sum = 0
        Scanner(System.`in`).use { scanner ->
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                if (line.isNullOrBlank() || line == "\n") {
                    break
                }
                val partialSum = line.split("|")[1]
                    .split(" ")
                    .count {
                        it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7
                    }
                sum += partialSum
            }
        }

        println("Sum: $sum")

    }
    fun part2() {}

    part1()
}