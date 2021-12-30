class Day14(input: List<String>) : Day<Day14.Input, Int>(input) {

    class Input(val template: List<String>, val rules: Map<String, Char>)

    override fun convertInput(input: List<String>): Input {
        val rules = mutableMapOf<String, Char>()
        for(rule in input.subList(2, input.size)){
            val (pair, char) = rule.split(" -> ")
            rules[pair] = char.toCharArray()[0]
        }
        return Input(input[0].toPairs(), rules)
    }

    override fun runPart1(input: Input): Int {
        var polymerPairs = input.template.toMutableList()
        for(i in 1..10){
            var newPolymerPairs = polymerPairs
            var insertions = 0
            for((index, pair) in polymerPairs.withIndex()){
                val insertionChar = input.rules[pair]
                if(insertionChar != null){
                    val insertionIndex = insertions + index
                    newPolymerPairs = (
                            newPolymerPairs.subList(0, insertionIndex)
                            + listOf("${pair[0]}$insertionChar")
                            + listOf("$insertionChar${pair[1]}")
                            + newPolymerPairs.subList(insertionIndex+1, newPolymerPairs.size)
                    ).toMutableList()

                    insertions++
                }
            }
            polymerPairs = newPolymerPairs
        }

        val quantities = polymerPairs.mergePairs().groupBy { it }.values.map { it.size }.sorted()
        return quantities.last() - quantities.first()
    }

    private fun String.toPairs() : List<String> {
        val pairs = mutableListOf<String>()
        var pairIndex = 0
        while(pairIndex <= this.length - 2){
            pairs.add("${this[pairIndex]}${this[pairIndex+1]}")
            pairIndex++
        }

        return pairs
    }

    private fun List<String>.mergePairs() : String {
        val sb = StringBuilder()
        for((index, pair) in this.withIndex()){
            sb.append(pair[0])
            if(index == this.size-1){
                sb.append(pair[1])
            }
        }
        return sb.toString()
    }

    override fun runPart2(input: Input): Int {
        return -1
    }
}