import java.lang.IllegalArgumentException

class Day15(input: List<String>) : Day<Array<Array<Int>>, Int>(input) {

    override fun convertInput(input: List<String>): Array<Array<Int>> {
        return input.map { line -> line .toCharArray().map { char -> char.digitToInt() }.toTypedArray() }.toTypedArray()
    }

    override fun runPart1(input: Array<Array<Int>>): Int {
        val destination = Pair(input.size-1, input[0].size-1)
        val remaining = mutableListOf(CostPair(Pair(0,0), 0))
        val visited = mutableListOf<Pair<Int,Int>>()

        while (remaining.isNotEmpty()){
            remaining.sortBy { it.totalCost }
            val current = remaining.removeFirst()
            if(current.pair ==  destination){
                return current.totalCost
            }
            if(!visited.contains(current.pair)){
                visited.add(current.pair)
                current.pair.neighbours(input).forEach { remaining.add(CostPair(it, current.totalCost + input.get(it))) }
            }
        }
        throw IllegalArgumentException("Not valid")
    }

    override fun runPart2(input: Array<Array<Int>>): Int {
        return -1
    }


    private fun Pair<Int, Int>.neighbours(riskMap: Array<Array<Int>>) : List<Pair<Int,Int>> {
        return listOf(
            Pair(this.first-1, this.second),
            Pair(this.first+1, this.second),
            Pair(this.first, this.second-1),
            Pair(this.first, this.second+1))
        .filter { it.first >= 0 && it.second >= 0 && it.first < riskMap.size && it.second < riskMap[0].size}
    }

    fun Array<Array<Int>>.get(pair: Pair<Int, Int>) = this[pair.first][pair.second]

    class CostPair(val pair: Pair<Int, Int>, val totalCost: Int)
}