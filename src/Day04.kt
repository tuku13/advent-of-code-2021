import java.util.*

fun main() {
    class BingoBoard(rows: MutableList<String>) {
        private val numbers = Array(5) { _ -> Array(5) { _ -> 0 } } // 5x5 table
        private val marks = Array(5) { _ -> Array(5) { _ -> false } } // 5x5 table

        override fun toString(): String {
            var result = ""
            numbers.map { row ->
                result += "[ "
                row.map {
                    result += "$it "
                }
                result += "]\n"
            }

            result += "\n"

            marks.map { row ->
                result += "[ "
                row.map {
                    result += if(it) "1 " else "0 "
                }
                result += "]\n"
            }

            return result
        }

        init {
            for(i in 0 until 5) {
                val row = rows[i]
                val numberList = row
                    .split(" ")
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
                    .toTypedArray()
                numbers[i] = numberList
            }
        }

        fun mark(number: Int) {
            for(i in 0 until 5) {
                for (j in 0 until 5) {
                    if(numbers[i][j] == number) {
                        marks[i][j] = true
                    }
                }
            }
        }

        fun calculateScore(): Int {
            var sum = 0
            for(i in 0 until 5) {
                for (j in 0 until 5) {
                    if(!marks[i][j]) {
                        sum += numbers[i][j]
                    }
                }
            }
            return sum
        }

        fun isCompleted() : Boolean {
            // by rows
            marks.map { row ->
                val markedNumbers = row.count { it }
                if(markedNumbers == 5) {
                    return true
                }
            }

            // by columns
            for(i in 0 until 5) {
                val marksByColumnId = mutableListOf<Boolean>()
                marks.map {
                    marksByColumnId.add(it[i])
                }

                val markedNumbers = marksByColumnId.count { it }
                if(markedNumbers == 5) {
                    return true
                }
            }
            return false
        }
    }

    fun part1() {
        val boards = mutableListOf<BingoBoard>()
        var drawnNumbers = mutableListOf<Int>()
        Scanner(System.`in`).use {

            drawnNumbers = it.nextLine()
                .split(",")
                .filter { s -> s.isNotBlank() }
                .map { i-> i.toInt() }
                .toMutableList()

            it.nextLine()

            while (it.hasNextLine()) {
                val line = it.nextLine()
                if(line.isNullOrBlank() || line == "q") {
                    break
                }

                val rowList = mutableListOf<String>().also { list ->
                    list.add(line)
                    for(i in 1 until 5) {
                        list.add(it.nextLine())
                    }
                    it.nextLine()
                }

                boards.add(BingoBoard(rowList))
            }
        }

        for(i in drawnNumbers) {
            boards.map {
                it.mark(i)
                if(it.isCompleted()) {
                    var score = it.calculateScore() * i
                    println(it)
                    println("score: ${it.calculateScore()} * $i = $score")
                    return
                }
            }
        }

    }

    fun part2() {
        var boards = mutableListOf<BingoBoard>()
        var drawnNumbers = mutableListOf<Int>()
        Scanner(System.`in`).use {

            drawnNumbers = it.nextLine()
                .split(",")
                .filter { s -> s.isNotBlank() }
                .map { i-> i.toInt() }
                .toMutableList()

            it.nextLine()

            while (it.hasNextLine()) {
                val line = it.nextLine()
                if(line.isNullOrBlank() || line == "q") {
                    break
                }

                val rowList = mutableListOf<String>().also { list ->
                    list.add(line)
                    for(i in 1 until 5) {
                        list.add(it.nextLine())
                    }
                    it.nextLine()
                }

                boards.add(BingoBoard(rowList))
            }
        }
        val maxBoards = boards.size
        var completed = 0

        for(i in drawnNumbers) {
            boards.map {
                it.mark(i)
            }

            boards.filter { it.isCompleted() }.map { board ->
                completed++
                if(completed == maxBoards) {
                    var score = board.calculateScore() * i
                    println(board)
                    println("score: ${board.calculateScore()} * $i = $score")
                    return
                }
            }

            boards = boards.filter { !it.isCompleted()}.toMutableList()
        }

    }
}