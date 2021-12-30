class Day14(input: List<String>) : Day<Day14.Input, Long>(input) {

    class Input(val templatePairCounts: Map<String, Long>, val templateCharCounts: Map<Char, Long>, val rules: Map<String, Char>)

    override fun convertInput(input: List<String>): Input {
        val rules = mutableMapOf<String, Char>()
        for(rule in input.subList(2, input.size)){
            val (pair, char) = rule.split(" -> ")
            rules[pair] = char.toCharArray()[0]
        }

        val template = input[0]
        val charCounts = template.toCharArray().groupBy { it }.mapValues { it.value.size.toLong() }
        val pairCounts = mutableMapOf<String, Long>()
        template
            .mapIndexed { index, char -> if(index < template.length - 1) "$char${template[index+1]}" else "" }
            .filterNot { it == "" }
            .forEach {pairCounts.increaseCounter(it, 1L)}

        return Input(pairCounts, charCounts, rules)
    }

    override fun runPart1(input: Input): Long = solve(input, 10)

    override fun runPart2(input: Input): Long = solve(input, 40)

    private fun <T> MutableMap<T, Long>.increaseCounter(key: T, inc: Long){
        val currentValue = this.getOrPut(key) { 0 }
        this[key] = currentValue + inc
    }

    private fun solve(input: Input, times: Int) : Long {
        var pairs = input.templatePairCounts
        val charCounts = input.templateCharCounts.toMutableMap()

        for(i in 1..times){
            val newPairs = mutableMapOf<String, Long>()
            for((pair, value) in pairs){
                val newChar = input.rules[pair]!!
                newPairs.increaseCounter("${pair[0]}$newChar", value)
                newPairs.increaseCounter("$newChar${pair[1]}", value)
                charCounts.increaseCounter(newChar, value)
            }
            pairs = newPairs
        }

        val quantities = charCounts.values.sorted()
        return quantities.last() - quantities.first()
    }
}