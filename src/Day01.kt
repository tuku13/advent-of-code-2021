import java.util.*

fun main() {
    fun part1(){
        val scanner = Scanner(System.`in`)

        val listOfNumbers = mutableListOf<Int>()

        var line: String

        while (scanner.hasNextLine()) {
            line = scanner.nextLine()
            if(line == "\n" || line.isNullOrEmpty()) {
                break
            }

            listOfNumbers.add(line.toInt())
        }

        var increases = 0

        for(i in 1 until listOfNumbers.size) {
            if(listOfNumbers[i] > listOfNumbers[i - 1]) {
                increases++
            }
        }

        println("Increases $increases times")

        scanner.close()
    }

    fun part2() {
        val scanner = Scanner(System.`in`)

        val listOfNumbers = mutableListOf<Int>()
        val sums = mutableListOf<Int>()

        var line: String

        while (scanner.hasNextLine()) {
            line = scanner.nextLine()
            if(line == "\n" || line.isNullOrEmpty()) {
                break
            }

            listOfNumbers.add(line.toInt())
        }

        var increases = 0

        for(i in 0..listOfNumbers.size - 3) {
            var sum = listOfNumbers[i] + listOfNumbers[i + 1] + listOfNumbers[i + 2]
            sums.add(sum)
        }

        for(i in 1 until sums.size) {
            if(sums[i] > sums[i - 1]) {
                increases++
            }
        }

        println("Increases $increases times")

        scanner.close()
    }
}
