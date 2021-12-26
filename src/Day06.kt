import java.util.*

fun main() {

    fun part1() {
        Simulator.addFish(LanternFish(6))
//        Simulator.addFish(LanternFish(4))
//        Simulator.addFish(LanternFish(3))
//        Simulator.addFish(LanternFish(1))6+1))
//        Simulator.addFish(LanternFish(2))

        Scanner(System.`in`).use { scanner ->
            scanner.nextLine()
                .split(",")
                .map { it -> it.toInt() }
                .map { timer -> Simulator.addFish(LanternFish(timer)) }
        }

//        println("Number of fishes: ${Simulator.numberOfFishes}")
        Simulator.simulate(160)
        println("Number of fishes: ${Simulator.numberOfFishes}")
    }

    part1()
}

object Simulator {
    private val fishes = mutableListOf<LanternFish>()

    val numberOfFishes : Int
        get() = fishes.count()

    fun addFish(fish: LanternFish) {
        fishes.add(fish)
    }

    private fun printFishTimers() {
        for(fish in fishes) {
            print(fish.timer)
            if (fish != fishes.last()) {
                print(",")
            }
        }
        println()
    }

    fun simulate2(days: Int) {
        val iterations = 64
        for(i in 0 until 64) {
            val newFishes = mutableListOf<LanternFish>()
            fishes.onEach {
                it.timer -= 4

            }
        }
    }

    fun simulate(days: Int) {
        print("Initial state: ")
        printFishTimers()
        for(i in 0 until days) {
            fishes.toList().onEach { it.step() }
//            print("After ${i + 1} days: ")
//            printFishTimers()
            println("${i + 1} $numberOfFishes")
        }
    }
}

class LanternFish(var timer: Int = 6) {
    private val resetValue = 6
    fun step() {
        timer--
        if (timer == -1) {
            Simulator.addFish(LanternFish())
            timer = resetValue
        }
    }
}
