import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day15Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(15)
        val day15 = Day15(input)
        assertEquals(625, day15.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(15)
        val day15 = Day15(input)
        assertEquals(-1, day15.runPart2())
    }
}