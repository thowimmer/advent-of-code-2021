import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(3)
        val day3 = Day3(input)
        assertEquals(3374136, day3.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(3)
        val day3 = Day3(input)
        assertEquals(-1, day3.runPart2())
    }
}