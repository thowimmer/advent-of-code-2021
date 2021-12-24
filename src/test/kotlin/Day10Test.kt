import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(10)
        val day10 = Day10(input)
        assertEquals(370407, day10.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(10)
        val day10 = Day10(input)
        assertEquals(3249889609, day10.runPart2())
    }
}