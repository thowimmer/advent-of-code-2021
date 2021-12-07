import kotlin.math.absoluteValue

class Day7(input: List<String>) : Day<List<Int>, Int>(input) {

    override fun convertInput(input: List<String>): List<Int> = input[0].split(',').map { it.toInt() }

    override fun runPart1(input: List<Int>) : Int = minFuelCosts(input) { pos1, pos2 -> (pos1 - pos2).absoluteValue }

    override fun runPart2(input: List<Int>) : Int = minFuelCosts(input) { pos1, pos2 -> (0..((pos1 - pos2).absoluteValue)).sum() }

    private fun minFuelCosts(input: List<Int>, calculateCosts: (Int, Int) -> Int) : Int {
        var minFuelCosts = Int.MAX_VALUE

        for(matchPosition in input.distinct()){
            var matchPositionFuelCosts = 0
            for(crabPosition in input){
                matchPositionFuelCosts += calculateCosts(matchPosition, crabPosition)
            }

            if(matchPositionFuelCosts < minFuelCosts) minFuelCosts = matchPositionFuelCosts
        }
        return minFuelCosts
    }
}