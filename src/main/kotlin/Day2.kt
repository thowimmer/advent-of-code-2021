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

        executeMovements(input,
            forward = { units -> horizontal += units },
            down = { units -> depth += units },
            up = { units -> depth -= units }
        )

        return horizontal * depth
    }

    override fun runPart2(input: List<Movement>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        executeMovements(input,
            forward = { units -> horizontal += units; depth += aim * units },
            down = { units -> aim += units },
            up = { units -> aim -= units }
        )

        return horizontal * depth

    }

    private fun executeMovements(movements: List<Movement>, forward: (units: Int) -> Unit, down: (units: Int) -> Unit, up: (units: Int) -> Unit){
        for(movement in movements){
            when(movement.command){
                Command.FORWARD -> forward(movement.units)
                Command.DOWN -> down(movement.units)
                Command.UP -> up(movement.units)
            }
        }
    }
}