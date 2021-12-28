import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(11)
        val day11 = Day11(input)
        assertEquals(1691, day11.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(11)
        val day11 = Day11(input)
        assertEquals(216, day11.runPart2())
    }
}