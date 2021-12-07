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

    override fun runPart1(input: Field) : Int = countOverlappingPoints(input, true)

    override fun runPart2(input: Field) : Int = countOverlappingPoints(input, false)

    class Field(private val maxX: Int, private val maxY: Int, private val lines: List<Line>){

        fun applyLines(ignoreDiagonals: Boolean = false) : Array<IntArray> {
            val field = Array(maxY + 1) { IntArray(maxX + 1) }

            for(line in lines){
                when {
                    line.isHorizontalLeftToRight() -> {
                        for(x in line.startX..line.endX){
                            val fieldValue = field[line.startY][x]
                            field[line.startY][x] = fieldValue + 1
                        }
                    }
                    line.isVerticalTopToBottom() -> {
                        for(y in line.startY..line.endY){
                            val fieldValue = field[y][line.startX]
                            field[y][line.startX] = fieldValue + 1
                        }
                    }
                    line.isDiagonalFromTopLeftToBottomRight() && !ignoreDiagonals -> {
                        var x = line.startX
                        var y = line.startY
                        while (x <= line.endX && y <= line.endY){
                            val fieldValue = field[y][x]
                            field[y][x] = fieldValue + 1
                            x++
                            y++

                        }
                    }
                    line.isDiagonalFromTopRightToBottomLeft() && !ignoreDiagonals -> {
                        var x = line.startX
                        var y = line.startY
                        while (x >= line.endX && y <= line.endY){
                            val fieldValue = field[y][x]
                            field[y][x] = fieldValue + 1
                            x--
                            y++
                        }
                    }
                }
            }

            return field
        }
    }

    class Line(var startX: Int, var startY: Int, var endX: Int, var endY: Int){

        init { swapStartWithEndIfRequired() }

        private fun swapStartWithEndIfRequired() {
            when {
                isHorizontalRightToLeft() ||
                        isVerticalBottomToTop() ||
                        isDiagonalFromBottomRightToTopLeft() ||
                        isDiagonalFromBottomLeftToTopRight() -> swapStartWithEnd()
            }
        }

        private fun swapStartWithEnd() {
            val newStartX = endX
            val newStartY = endY
            val newEndX = startX
            val newEndY = startY

            startX = newStartX
            startY = newStartY
            endX = newEndX
            endY = newEndY
        }

        private fun isHorizontalRightToLeft() = startY == endY && startX > endX
        fun isHorizontalLeftToRight() = startY == endY && startX < endX
        private fun isVerticalBottomToTop() = startX == endX && startY > endY
        fun isVerticalTopToBottom() = startX == endX && startY < endY
        private fun isDiagonalFromBottomLeftToTopRight() = startX < endX && startY > endY
        fun isDiagonalFromTopLeftToBottomRight()  = startX < endX && startY < endY
        private fun isDiagonalFromBottomRightToTopLeft() = startX > endX && startY > endY
        fun isDiagonalFromTopRightToBottomLeft() = startX > endX && startY < endY

        override fun toString(): String = "$startX,$endX -> $endX,$endY"
    }

    private fun countOverlappingPoints(field: Field, ignoreDiagonals: Boolean) : Int {
        val fieldWithAppliedLines = field.applyLines(ignoreDiagonals)
        return countOverlappingPointsInField(fieldWithAppliedLines)
    }

    private fun countOverlappingPointsInField(field: Array<IntArray>) : Int {
        var count = 0
        for(x in field.indices){
            for(y in field[x].indices){
                if (field[x][y] > 1) count++
            }
        }

        return count
    }
}