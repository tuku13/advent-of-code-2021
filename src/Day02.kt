import java.util.*

fun main() {
    fun part1() {
        var horizontalPosition = 0
        var depth = 0

        Scanner(System.`in`).use {
            while (it.hasNextLine()) {
                var line = it.nextLine()
                if(line.isNullOrEmpty() || line == "\n") {
                    break
                }

                var command = line.split(" ")[0]
                var parameter = line.split(" ")[1].toInt()

                when (command) {
                    "forward" -> horizontalPosition += parameter
                    "down" -> depth += parameter
                    "up" -> depth -= parameter
                }
            }
        }

        var result = horizontalPosition * depth
        println(result)
    }

    fun part2() {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        Scanner(System.`in`).use {
            while (it.hasNextLine()) {
                var line = it.nextLine()
                if(line.isNullOrEmpty() || line == "\n") {
                    break
                }

                var command = line.split(" ")[0]
                var parameter = line.split(" ")[1].toInt()

                when (command) {
                    "forward" -> {
                        horizontalPosition += parameter
                        depth += aim * parameter
                    }
                    "down" -> aim += parameter
                    "up" -> aim -= parameter
                }
            }
        }

        var result = horizontalPosition * depth
        println(result)
    }
}