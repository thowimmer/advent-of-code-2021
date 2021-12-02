import java.util.*

class Day2(input: List<String>) : Day<List<Day2.Movement>, Int>(input) {

    override fun convertInput(input: List<String>): List<Movement> = input.map {
        val components = it.split(" ")
        Movement(
            Command.valueOf(components[0].uppercase(Locale.getDefault())),
            components[1].toInt()
        )
    }

    enum class Command {
        FORWARD,
        DOWN,
        UP
    }

    data class Movement(val command: Command, val units: Int)

    override fun runPart1(input: List<Movement>): Int {
        var horizontal = 0
        var depth = 0

        for(movement in input){
            when(movement.command){
                Command.FORWARD -> horizontal += movement.units
                Command.DOWN -> depth += movement.units
                Command.UP -> depth -= movement.units
            }
        }

        return horizontal * depth
    }

    override fun runPart2(input: List<Movement>): Int {
        return -1
    }
}