import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(2)
        val day2 = Day2(input)
        assertEquals(2322630, day2.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(2)
        val day2 = Day2(input)
        assertEquals(-1, day2.runPart2())
    }
}