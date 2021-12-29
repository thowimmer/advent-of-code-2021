import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(13)
        val day13 = Day13(input)
        assertEquals(724, day13.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(13)
        val day13 = Day13(input)
        assertEquals(-1, day13.runPart2())
    }
}