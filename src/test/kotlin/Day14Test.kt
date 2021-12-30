import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(14)
        val day14 = Day14(input)
        assertEquals(4517, day14.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(14)
        val day14 = Day14(input)
        assertEquals(4704817645083, day14.runPart2())
    }
}