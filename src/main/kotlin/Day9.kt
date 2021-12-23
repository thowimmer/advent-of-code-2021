class Day9(input: List<String>) : Day<Array<Array<Int>>, Int>(input) {

    override fun convertInput(input: List<String>): Array<Array<Int>> {
        return input.map { line -> line .toCharArray().map { char -> char.digitToInt() }.toTypedArray() }.toTypedArray()
    }

    override fun runPart1(input: Array<Array<Int>>) : Int {
        var sumRiskLevel = 0
        for(row in input.indices) {
            for(column in input[row].indices) {
                if(isLowPoint(row, column, input)) sumRiskLevel += input[row][column] + 1
            }
        }

        return sumRiskLevel
    }

    private fun isLowPoint(row: Int, column : Int, map: Array<Array<Int>>) : Boolean {
        val isLeft = column == 0
        val isRight = column == map[0].size - 1
        val isTop = row == 0
        val isBottom = row == map.size - 1

        val isLeftTop = isLeft && isTop
        val isLeftBottom = isLeft && isBottom
        val isRightTop = isRight && isTop
        val isRightBottom = isRight && isBottom

        val isUpLower = if (isTop) false else map[row][column] < map[row-1][column]
        val isRightLower = if(isRight) false else map[row][column] < map[row][column+1]
        val isDownLower = if(isBottom) false else map[row][column] < map[row+1][column]
        val isLeftLower = if(isLeft) false else map[row][column] < map[row][column-1]

        return when {
            isLeftTop -> isDownLower && isRightLower
            isLeftBottom -> isUpLower && isRightLower
            isRightBottom -> isUpLower && isLeftLower
            isRightTop -> isDownLower && isLeftLower
            isTop -> isLeftLower && isRightLower && isDownLower
            isBottom -> isLeftLower && isRightLower && isUpLower
            isRight -> isLeftLower && isDownLower && isUpLower
            isLeft -> isRightLower && isDownLower && isUpLower
            else -> isDownLower && isUpLower && isLeftLower && isRightLower
        }
    }

    override fun runPart2(input: Array<Array<Int>>) : Int {
        return -1
    }
}