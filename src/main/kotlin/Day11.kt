class Day11(input: List<String>) : Day<Array<Array<Int>>, Int>(input) {
    override fun convertInput(input: List<String>): Array<Array<Int>> {
        return input.map { line -> line .toCharArray().map { char -> char.digitToInt() }.toTypedArray() }.toTypedArray()
    }

    override fun runPart1(input: Array<Array<Int>>) = (1..100).reduce { totalFlashes, _ -> totalFlashes + executeStep(input) }

    override fun runPart2(input: Array<Array<Int>>): Int {
        var step = 0
        while (!input.flatten().all { it == 0 }) { executeStep(input) ; step++ }
        return step
    }

    private fun executeStep(energyLevels: Array<Array<Int>>) : Int {
        val flashes = Array(energyLevels.size) { Array(energyLevels[0].size){ false } }
        iterate(energyLevels) { row, column, energyLevel -> energyLevels[row][column] = energyLevel + 1 }
        iterate(energyLevels) { row, column, energyLevel -> if(energyLevel > 9 && !flashes[row][column]) flash(row, column, energyLevels, flashes) }
        iterate(energyLevels) { row, column, energyLevel -> if(energyLevel > 9) energyLevels[row][column] = 0}
        return flashes.flatten().count { it }
    }

    private fun iterate(energyLevels: Array<Array<Int>>, next: (row: Int, column: Int, energyLevel: Int) -> Unit){
        for(row in energyLevels.indices){
            for(column in energyLevels[row].indices){
                next(row, column, energyLevels[row][column])
            }
        }
    }

    private fun flash(row: Int, column: Int, energyLevels: Array<Array<Int>>, flashes: Array<Array<Boolean>>) {
        flashes[row][column] = true

        iterateNeighbours(row,column, energyLevels) { nRow, nColumn ->
            energyLevels[nRow][nColumn] = energyLevels[nRow][nColumn]+1
            if(energyLevels[nRow][nColumn] > 9 && !flashes[nRow][nColumn]){
                flash(nRow, nColumn, energyLevels, flashes)
            }
        }
    }

    private fun iterateNeighbours(row: Int, column: Int, energyLevels: Array<Array<Int>>, next: (nRow: Int, nColumn: Int) -> Unit) {
        return listOf(
            Pair(row, column+1), //right
            Pair(row+1, column+1), //right-bottom
            Pair(row+1, column), //bottom
            Pair(row+1, column-1), //bottom left
            Pair(row, column-1), //left
            Pair(row-1, column-1), //left-top
            Pair(row-1, column), //top
            Pair(row-1, column+1) //top-right
        ).filter {
            (it.first >= 0 && it.first < energyLevels.size)
                    && (it.second >= 0 && it.second < energyLevels[0].size)
        }.forEach { next(it.first, it.second) }
    }
}