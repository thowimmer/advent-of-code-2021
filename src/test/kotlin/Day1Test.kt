import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(1)
        val day1 = Day1(input)
        assertEquals(1446, day1.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(1)
        val day1 = Day1(input)
        assertEquals(-1, day1.runPart2())
    }
}