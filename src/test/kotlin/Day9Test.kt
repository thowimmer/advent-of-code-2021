import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(9)
        val day9 = Day9(input)
        assertEquals(560, day9.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(9)
        val day9 = Day9(input)
        assertEquals(-1, day9.runPart2())
    }
}