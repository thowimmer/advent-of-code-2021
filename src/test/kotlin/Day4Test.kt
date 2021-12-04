import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(4)
        val day4 = Day4(input)
        assertEquals(89001, day4.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(4)
        val day4 = Day4(input)
        assertEquals(7296, day4.runPart2())
    }
}