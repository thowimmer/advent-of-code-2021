import Day9.LOCATION.*

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

    override fun runPart2(input: Array<Array<Int>>) : Int {
        val basinSizes = mutableListOf<Int>()
        for(row in input.indices) {
            for(column in input[row].indices) {
                if(isLowPoint(row, column, input)) {
                    val basinSize = basinSize(row, column, input)
                    basinSizes.add(basinSize)
                }
            }
        }

        return basinSizes.sorted().takeLast(3).reduce { product, basin -> product * basin }
    }

    enum class LOCATION {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP,
        LEFT,
        RIGHT,
        BOTTOM,
        MIDDLE;

        fun onTop() = this == TOP_LEFT || this == TOP_RIGHT || this == TOP
        fun onBottom() = this == BOTTOM_LEFT || this == BOTTOM_RIGHT || this == BOTTOM
        fun onRight() = this == TOP_RIGHT || this == BOTTOM_RIGHT || this == RIGHT
        fun onLeft() = this == TOP_LEFT || this == BOTTOM_LEFT || this == LEFT
    }

    private fun locationOf(row: Int, column : Int, map: Array<Array<Int>>) : LOCATION {
        val isLeft = column == 0
        val isRight = column == map[0].size - 1
        val isTop = row == 0
        val isBottom = row == map.size - 1

        return when {
            isTop && isLeft -> TOP_LEFT
            isTop && isRight -> TOP_RIGHT
            isBottom && isLeft -> BOTTOM_LEFT
            isBottom && isRight -> BOTTOM_RIGHT
            isTop -> TOP
            isBottom -> BOTTOM
            isRight -> RIGHT
            isLeft -> LEFT
            else -> MIDDLE
        }
    }

    private fun isLowPoint(row: Int, column : Int, map: Array<Array<Int>>) : Boolean {
        val location = locationOf(row, column, map)

        val isUpLower = if (location.onTop()) false else map[row][column] < map[row-1][column]
        val isRightLower = if(location.onRight()) false else map[row][column] < map[row][column+1]
        val isDownLower = if(location.onBottom()) false else map[row][column] < map[row+1][column]
        val isLeftLower = if(location.onLeft()) false else map[row][column] < map[row][column-1]

        return when(location) {
            TOP_LEFT -> isDownLower && isRightLower
            BOTTOM_LEFT -> isUpLower && isRightLower
            BOTTOM_RIGHT -> isUpLower && isLeftLower
            TOP_RIGHT -> isDownLower && isLeftLower
            TOP -> isLeftLower && isRightLower && isDownLower
            BOTTOM -> isLeftLower && isRightLower && isUpLower
            RIGHT -> isLeftLower && isDownLower && isUpLower
            LEFT -> isRightLower && isDownLower && isUpLower
            else -> isDownLower && isUpLower && isLeftLower && isRightLower
        }
    }

    private fun basinSize(row: Int, column : Int, map: Array<Array<Int>>, visited: MutableList<Pair<Int,Int>> = emptyList<Pair<Int, Int>>().toMutableList()) : Int {
        val currentHeight = map[row][column]

        if(currentHeight == 9 || visited.contains(Pair(row, column)) ) {
            return visited.size
        }

        visited.add(Pair(row, column))

        fun right() = basinSize(row, column+1, map, visited)
        fun down() = basinSize(row+1, column, map, visited)
        fun left() = basinSize(row, column-1, map, visited)
        fun up() = basinSize(row-1, column, map, visited)

        when(locationOf(row, column, map)) {
            TOP_LEFT -> { right(); down() }
            BOTTOM_LEFT -> { right(); up() }
            BOTTOM_RIGHT -> { left(); up() }
            TOP_RIGHT -> { down() ; left() }
            TOP -> { right(); down(); left() }
            BOTTOM -> { right(); left(); up() }
            RIGHT -> { down(); left(); up() }
            LEFT -> { right(); down() ; up() }
            else -> { right(); down(); left(); up() }
        }

        return visited.size
    }
}