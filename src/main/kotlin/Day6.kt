class Day6(input: List<String>) : Day<List<Int>, Long>(input) {

    override fun convertInput(input: List<String>): List<Int> = input[0].split(',').map { it.toInt() }

    override fun runPart1(input: List<Int>) : Long = countLanternFishAfterDays(input, 80)

    override fun runPart2(input: List<Int>) : Long = countLanternFishAfterDays(input, 256)

    private fun countLanternFishAfterDays(initialFish: List<Int>, days: Int) : Long {
        val fishTimers = LongArray(9)
        initialFish.forEach { fishTimers[it]++ } //at the beginning there are some fish with different timers

        for(day in 1..days){
            val currentZeroFish = fishTimers[0]
            (1..8).forEach { fishTimers[it-1] = fishTimers[it] } // every non 0 fish has the time decreased by 1
            fishTimers[8] = currentZeroFish // every 0 fish creates a new 8 fish
            fishTimers[6] += currentZeroFish // every 0 fish is reset to 6
        }

        return fishTimers.sum()
    }
}