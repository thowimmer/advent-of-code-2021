import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {

    @Test
    fun shouldPassPart1(){
        val input = InputLoader().loadInput(13)
        val day13 = Day13(input)
        assertEquals(724, day13.runPart1().flatten().count {it == '#'})
    }

    @Test
    fun shouldPassPart2(){
        val input = InputLoader().loadInput(13)
        val day13 = Day13(input)
        assertEquals("""
         ##  ###    ## ###  #### ###  #  # #    
        #  # #  #    # #  # #    #  # #  # #    
        #    #  #    # ###  ###  #  # #  # #    
        #    ###     # #  # #    ###  #  # #    
        #  # #    #  # #  # #    # #  #  # #    
         ##  #     ##  ###  #### #  #  ##  #### 
        """.trimIndent(), day13.runPart2().asString())
    }

    private fun <T> Array<Array<T>>.asString() : String {
        val sb = StringBuilder()
        this.iterate({ _, _, value -> sb.append(value)}, { y -> if(y > 0) sb.append("\n")})
        return sb.toString()
    }
}