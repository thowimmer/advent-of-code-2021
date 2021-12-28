import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(12)
        val day12 = Day12(input)
        assertEquals(-1, day12.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(12)
        val day12 = Day12(input)
        assertEquals(-1, day12.runPart2())
    }
}