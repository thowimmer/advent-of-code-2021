class Day6(input: List<String>) : Day<List<Int>, Int>(input) {

    override fun convertInput(input: List<String>): List<Int> = input[0].split(',').map { it.toInt() }

    //the number of days until it creates a new lanternfish -> 7 (0-6)
    //new fish + 2 (0-8)

    override fun runPart1(input: List<Int>) : Int {
        val fishPopulation = mutableListOf<Int>()
        fishPopulation.addAll(input)

        for(day in 1..80){
            val newFishPopulation = mutableListOf<Int>()
            for((i, fish) in fishPopulation.withIndex()){
                if(fish == 0){
                    fishPopulation[i] = 6
                    newFishPopulation.add(8)
                }else {
                    fishPopulation[i] = fish - 1
                }
            }

            fishPopulation.addAll(newFishPopulation)
        }

        return fishPopulation.size
    }

    override fun runPart2(input: List<Int>) : Int {
        return -1
    }
}