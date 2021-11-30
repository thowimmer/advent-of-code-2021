import java.io.File
import java.nio.charset.StandardCharsets

class InputLoader {
    fun loadInput(numberOfDay : Int) : List<String> {
        val resource = javaClass.classLoader.getResource("day$numberOfDay.txt")
        return File(resource!!.toURI()).readLines(StandardCharsets.UTF_8)
    }
}
