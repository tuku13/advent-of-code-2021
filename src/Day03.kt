import java.util.*

fun main() {
    fun day3FilterData(data: MutableList<String>, column: Int, isMostCommon: Boolean): MutableList<String> {
        var ones = 0
        var zeros = 0

        data.map {
            when (it[column]) {
                '0' -> zeros++
                '1' -> ones++
                else -> {}
            }
        }

        val searchedBit = if(isMostCommon) {
            if(ones >= zeros) '1' else '0'
        } else {
            if(ones >= zeros) '0' else '1'
        }

        val result = data.filter {
            it[column] == searchedBit
        }.toMutableList()

        return result
    }

    fun part1() {
        var data = mutableListOf<String>()
        Scanner(System.`in`).use {
            while (it.hasNextLine()) {
                var line = it.nextLine()
                if(line.isNullOrEmpty() || line == "\n") {
                    break
                }

                data.add(line)
            }
        }

        var gammaString = ""
        var epsilonString = ""

        for(i in 0 until data[0].length) {
            var ones = 0
            var zeros = 0
            data.map {
                when (it[i]) {
                    '0' -> zeros++
                    '1' -> ones++
                    else -> {}
                }
            }
            val mostCommonBit = if(ones > zeros) '1' else '0'
            val leastCommonBit = if(ones > zeros) '0' else '1'

            gammaString += mostCommonBit
            epsilonString += leastCommonBit
        }

        val gamma = Integer.parseInt(gammaString, 2)
        val epsilon = Integer.parseInt(epsilonString, 2)

        println("Gamma as binary: $gammaString, as decimal: $gamma")
        println("Epsilon as binary: $epsilonString, as decimal: $epsilon")

        val power = gamma * epsilon
        println(power)
    }

    fun part2() {
        val data = mutableListOf<String>()
        Scanner(System.`in`).use {
            while (it.hasNextLine()) {
                var line = it.nextLine()
                if(line.isNullOrEmpty() || line == "\n") {
                    break
                }

                data.add(line)
            }
        }

        var mostCommonList = mutableListOf<String>().also {
            it.addAll(data)
        }
        var leastCommonList = mutableListOf<String>().also {
            it.addAll(data)
        }

        for(i in 0 until data[0].length) {
            mostCommonList = day3FilterData(mostCommonList, i, true)
            if(mostCommonList.size == 1) {
                break
            }
        }

        for(i in 0 until data[0].length) {
            leastCommonList = day3FilterData(leastCommonList, i, false)
            if(leastCommonList.size == 1) {
                break
            }
        }

        val oxygenString = mostCommonList[0]
        val co2String = leastCommonList[0]

        val oxygen = Integer.parseInt(oxygenString, 2)
        val co2 = Integer.parseInt(co2String, 2)

        val lifeSupportRating = oxygen * co2
        println(lifeSupportRating)
    }
}