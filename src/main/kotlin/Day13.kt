class Day13(input: List<String>) : Day<Day13.Input, Array<Array<Char>>>(input) {
    override fun convertInput(input: List<String>): Input{
        val instructionsSeparatorIndex = input.indexOf("")
        val paper = parsePaper(input, instructionsSeparatorIndex)
        val instructions = parseInstructions(input, instructionsSeparatorIndex)
        return Input(paper, instructions)
    }

    private fun parsePaper(input: List<String>, instructionsSeparatorIndex: Int) : Array<Array<Char>> {
        var maxX = -1
        var maxY = -1
        val dots = mutableListOf<Pair<Int, Int>>()
        for(dot in input.subList(0, instructionsSeparatorIndex)){
            val (x,y) = dot.split(",").map { it.toInt() }
            dots.add(Pair(x,y))
            when {
                x > maxX -> maxX = x
                y > maxY -> maxY = y
            }
        }

        val paper = Array(maxY+1) { Array(maxX+1){ ' ' } }
        paper.iterate({x, y, _ -> if(dots.any { it.first == x && it.second == y }) paper[y][x] = '#' })
        return paper
    }
    private fun parseInstructions(input: List<String>, instructionsSeparatorIndex: Int) : List<Pair<Char, Int>> {
        val instructions = mutableListOf<Pair<Char, Int>>()
        for(instruction in input.subList(instructionsSeparatorIndex+1, input.size)){
            val (direction, offset) = instruction.substringAfterLast("fold along ").split("=")
            instructions.add(Pair(direction.toCharArray()[0], offset.toInt()))
        }
        return instructions
    }

    class Input(val paper: Array<Array<Char>>, val foldInstructions: List<Pair<Char, Int>>)

    override fun runPart1(input: Input) = fold(input.paper, input.foldInstructions.subList(0,1))

    override fun runPart2(input: Input) = fold(input.paper, input.foldInstructions)

    private fun fold(paper: Array<Array<Char>>, foldInstructions: List<Pair<Char, Int>>) : Array<Array<Char>> {
        var foldedPaper = paper.copyOf()
        for((fold, offset) in foldInstructions){
            when(fold){
                'x' -> foldedPaper = foldX(foldedPaper, offset)
                'y' -> foldedPaper = foldY(foldedPaper, offset)
            }
        }
        return foldedPaper
    }

    private fun foldY(paper: Array<Array<Char>>, yFold: Int) : Array<Array<Char>> {
        val paperToFold = paper.cutHorizontal(yFold+1, paper.size)
        val foldedPaper = paper.cutHorizontal(0, yFold)

        var newY = yFold
        paperToFold.iterate(next = { x, _, value ->
            if(value == '#') foldedPaper[newY][x] = value
        }, nextRow = {newY--})
        return foldedPaper
    }

    private fun foldX(paper: Array<Array<Char>>, xFold: Int) : Array<Array<Char>> {
        val paperToFold = paper.cutVertical(xFold+1, paper[0].size)
        val foldedPaper = paper.cutVertical(0, xFold)

        var newX = xFold
        paperToFold.iterate(next = { _, y, value ->
            if(value == '#') foldedPaper[y][newX] = value
            newX--
        }, nextRow = {newX = xFold-1})

        return foldedPaper
    }
}


fun <T> Array<Array<T>>.iterate(next: (x: Int, y: Int, value: T) -> Unit, nextRow: ((y: Int) -> Unit)? = null) {
    for(y in this.indices){
        if(nextRow != null) nextRow(y)
        for(x in this[y].indices){
            next(x, y, this[y][x])
        }
    }
}

fun <T> Array<Array<T>>.cutHorizontal(begin: Int, end: Int) = this.copyOfRange(begin, end)
inline fun <reified T> Array<Array<T>>.cutVertical(begin: Int, end: Int) : Array<Array<T>>{
    val copy = mutableListOf<Array<T>>()
    for(y in this.indices){
        copy.add(this[y].copyOfRange(begin, end))
    }
    return copy.toTypedArray()
}



