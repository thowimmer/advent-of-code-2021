import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(8)
        val day8 = Day8(input)
        assertEquals(255, day8.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(8)
        val day8 = Day8(input)
        assertEquals(-1, day8.runPart2())
    }
}