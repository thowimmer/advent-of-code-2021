import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(6)
        val day6 = Day6(input)
        assertEquals(358214, day6.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(6)
        val day6 = Day6(input)
        assertEquals(-1, day6.runPart2())
    }
}