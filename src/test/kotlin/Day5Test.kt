import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(5)
        val day5 = Day5(input)
        assertEquals(6283, day5.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(5)
        val day5 = Day5(input)
        assertEquals(18864, day5.runPart2())
    }
}