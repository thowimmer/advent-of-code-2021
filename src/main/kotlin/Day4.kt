class Day4(input: List<String>) : Day<Day4.Bingo, Int>(input) {

    override fun convertInput(input: List<String>): Bingo  {
        var currentBoard = Array(5) { Array(5){ -1} }
        var rowIndex = 0

        val boards = mutableListOf<Board>()

        for(i in 2 until input.size){
            val currentLine = input[i]
            if (currentLine.isEmpty()) {
                boards.add(Board(currentBoard))
                currentBoard = Array(5) { emptyArray() }
                rowIndex = 0

            } else {
                currentBoard[rowIndex] = input[i].split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toTypedArray()
                rowIndex++
            }
        }

        boards.add(Board(currentBoard))
        val numbers = input[0].split(",").map { it.toInt() }

        return Bingo(numbers, boards)
    }

    class Bingo(val numbers: List<Int>, val boards: List<Board>){

        private var winners = mutableMapOf<Int, Board>()

        fun numberDrawn(drawnNumber: Int) {
            for((boardIndex, board) in boards.withIndex()){
                board.numberDrawn(drawnNumber)
                if(board.hasBingo() && !winners.contains(boardIndex)){
                    winners[boardIndex] = board
                }
            }
        }

        fun firstWinnerSum() = winners.values.firstOrNull()?.sumUnmarked()

        fun lastWinnerSum() : Int ? {
            val winnerBoards = winners.values
            if(winnerBoards.size == boards.size){
                return winnerBoards.last().sumUnmarked()
            }

            return null
        }

        fun reset() {
            winners = mutableMapOf()
            boards.forEach { it.reset() }
        }
    }

    class Board(private val board : Array<Array<Int>>){
        private var checked = Array(5) { Array(5){ false } }

        fun numberDrawn(drawnNumber: Int) {
            iterateRowWise(board) { row, column, number ->
                if(drawnNumber == number){
                    checked[row][column] = true
                }
            }
        }

        fun hasBingo() : Boolean {
            for(row in checked){
                if(row.all { it }) return true
            }

            for(column in checked[0].indices) {
                var allChecked = true
                for(row in checked.indices){
                    if(!checked[row][column]){
                        allChecked = false
                        break
                    }
                }

                if(allChecked) return true
            }

            return false
        }

        fun sumUnmarked() : Int {
            var sum = 0

            iterateRowWise(board) { row, column, number ->
                if(!checked[row][column]){
                    sum += number
                }
            }

            return sum
        }

        fun reset() {
            checked = Array(5) { Array(5){ false } }
        }

        private fun <T> iterateRowWise(matrix: Array<Array<T>>, step: (row : Int, column: Int, value: T) -> Unit){
            for(row in board.indices){
                for(column in board[row].indices){
                    step(row, column, matrix[row][column])
                }
            }
        }
    }

    override fun runPart1(input: Bingo) = play(input) { it.firstWinnerSum() }

    override fun runPart2(input: Bingo) : Int {
        return play(input) { it.lastWinnerSum() }
    }

    private fun play(input: Bingo, winnerSum: (input: Bingo)-> Int?) : Int {
        input.reset()
        for(drawnNumber in input.numbers){
            input.numberDrawn(drawnNumber)
            winnerSum(input)?.let {
                return it * drawnNumber
            }
        }
        return -1
    }

}