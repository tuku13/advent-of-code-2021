import java.util.*

fun main() {
    fun part1() {
        val heightmap = mutableListOf<MutableList<Int>>()

        Scanner(System.`in`).use { scanner ->
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                if(line.isNullOrEmpty() || line == "\n") { break }

                val row = line.map { it.toString().toInt() }.toMutableList()
                heightmap.add(row)
            }
        }

        val lowPoints = mutableListOf<Int>()

        for(i in 0 until heightmap.size) {
            for(j in 0 until heightmap[i].size) {
                val possibleLowPoint = heightmap[i][j]

                val up = if(i - 1 >= 0) heightmap[i - 1][j] else Int.MAX_VALUE
                val down = if(i + 1 < heightmap.size) heightmap[i + 1][j] else Int.MAX_VALUE
                val left = if(j - 1 >= 0) heightmap[i][j - 1] else Int.MAX_VALUE
                val right = if(j + 1 < heightmap[i].size) heightmap[i][j + 1] else Int.MAX_VALUE

                val neighbours = listOf(up, down, left, right)
                if(possibleLowPoint < (neighbours.minOrNull() ?: 0)) {
                    lowPoints.add(possibleLowPoint)
                }

            }
        }

//        lowPoints.map { println(it) }

        val risk = lowPoints.toList().sumOf { it + 1 }
        println("Risk: $risk")

        val a = 8
        val b = 8

        println(a === b)
    }

    fun part2() {
        val basins = mutableListOf<Basin>()
        val heightmap = mutableListOf<MutableList<Int>>()
        val points = mutableListOf<MutableList<Point>>()

        Scanner(System.`in`).use { scanner ->
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                if(line.isNullOrEmpty() || line == "\n") { break }

                val row = line.map { it.toString().toInt() }.toMutableList()
                heightmap.add(row)
            }
        }

        for(i in 0 until heightmap.size) {
            for(j in 0 until heightmap[i].size) {
                val possibleLowPoint = heightmap[i][j]

                val up = if(i - 1 >= 0) heightmap[i - 1][j] else Int.MAX_VALUE
                val down = if(i + 1 < heightmap.size) heightmap[i + 1][j] else Int.MAX_VALUE
                val left = if(j - 1 >= 0) heightmap[i][j - 1] else Int.MAX_VALUE
                val right = if(j + 1 < heightmap[i].size) heightmap[i][j + 1] else Int.MAX_VALUE

                val neighbours = listOf(up, down, left, right)
                if(possibleLowPoint < (neighbours.minOrNull() ?: 0)) {
                    basins.add(Basin(possibleLowPoint, i, j))
                }

            }
        }

    }

    part2()

    // TODO egy pontból indítani keresést, szomszédnak vissza nem adunk
    // TODO és olyan pontnak sem ami már hozzá van adva a basinhez vagy 9 az értéke
    // TODO kiinduló pontok a lowest pontok
}

class Basin(private val lowestValue: Int, private val x: Int, private val y: Int) {
    private val points = mutableListOf<Point>()

    init {
        points.add(lowPoint)
    }

    private val lowPoint: Point
        get() = Point(x, y)

    val size: Int
        get() = points.size

    fun addPoint(point: Point) {
        val contains = points.find { it == point } != null

        if(!contains) {
            points.add(point)
        }
    }

    fun printPoints() {
        points.map { println(it) }
    }

}

data class Point(val x: Int, val y: Int, var addedToBasin: Boolean = false)