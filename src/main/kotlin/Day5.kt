class Day5(input: List<String>) : Day<Day5.Field, Int>(input) {

    override fun convertInput(input: List<String>): Field  {
        val lineRegex = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()
        var maxX = 0
        var maxY = 0

        val lines = mutableListOf<Line>()

        for(textLine in input){
            val points = lineRegex.matchEntire(textLine)!!.groupValues.subList(1,5).map { it.toInt() }

            val line = Line(points[0], points[1], points[2], points[3])
            lines.add(line)

            when {
                line.startX > maxX -> maxX = line.startX
                line.endX > maxX -> maxX = line.endX
                line.startY > maxY -> maxY = line.startY
                line.endY > maxY -> maxY = line.endY
            }
        }

        return Field(maxX, maxY, lines)
    }

    class Field(val maxX: Int, val maxY: Int, val lines: List<Line>){
        private val field = Array(maxX + 1) { IntArray(maxY + 1) }

        fun applyLines() {
            for(line in lines){
                when {
                    line.isHorizontal() -> {
                        for(x in line.startX..line.endX){
                            val fieldValue = field[x][line.startY]
                            field[x][line.startY] = fieldValue + 1
                        }
                    }
                    line.isVertical() -> {
                        for(y in line.startY..line.endY){
                            val fieldValue = field[line.startX][y]
                            field[line.startX][y] = fieldValue + 1
                        }
                    }
                }
            }
        }

        fun countOverlappingPoints() : Int {
            var count = 0
            for(x in field.indices){
                for(y in field[x].indices){
                    if (field[x][y] > 1) count++
                }
            }

            return count
        }
    }

    class Line(var startX: Int, var startY: Int, var endX: Int, var endY: Int){

        init {
            when {
                isHorizontal() && startX > endX -> {
                    val newStartX = endX
                    endX = startX
                    startX = newStartX
                }
                isVertical() && startY > endY -> {
                    val newStartY = endY
                    endY = startY
                    startY = newStartY
                }
            }
        }
        fun isHorizontal() = startY == endY
        fun isVertical() = startX == endX
    }

    override fun runPart1(input: Field) : Int {
        input.applyLines()
        return input.countOverlappingPoints()
    }

    override fun runPart2(input: Field) : Int {
        return -1
    }
}