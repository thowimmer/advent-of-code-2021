fun main() {
    val inputLoader = InputLoader()

    for(day in 1..13){
        println("--------------DAY$day--------------")
        val inputForDay = inputLoader.loadInput(day)
        val dayClass = Class.forName("Day$day")
        val dayInstance = dayClass.constructors[0].newInstance(inputForDay) as Day<*,*>
        println("Part1: ${dayInstance.runPart1()}")
        println("Part2: ${dayInstance.runPart2()}")
        println("--------------------------------")
    }

}
