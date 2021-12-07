import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(7)
        val day7 = Day7(input)
        assertEquals(339321, day7.runPart1())
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(7)
        val day7 = Day7(input)
        assertEquals(95476244, day7.runPart2())
    }
}