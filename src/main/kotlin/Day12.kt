class Day12(input: List<String>) : Day<Day12.Cave, Int>(input) {

    class Cave(val name: String, val next: MutableList<Cave>){
        val isEnd = name == "end"
        fun connect(node: Cave) { next.add(node) }
    }

    override fun convertInput(input: List<String>): Cave {
        val nodes = mutableMapOf<String, Cave>()
        for(edge in input){
            val (leftNode, rightNode) = edge.split("-")
            if(nodes[leftNode] == null){
                nodes[leftNode] = Cave(leftNode, mutableListOf())
            }
            if(nodes[rightNode] == null){
                nodes[rightNode] = Cave(rightNode, mutableListOf())
            }
            nodes[leftNode]!!.connect(nodes[rightNode]!!)
            nodes[rightNode]!!.connect(nodes[leftNode]!!)
        }

        return nodes["start"]!!
    }

    override fun runPart1(input: Cave): Int {
        return traversePath(listOf(input)) { cave, path ->
            cave.name.all { it.isUpperCase() } || !path.any { cave.name == it.name }
        }.count()
    }


    private fun traversePath(path: List<Cave>, canVisit : (cave: Cave, path: List<Cave>) -> Boolean) : List<List<String>>{
        val lastCave = path.last()

        if(lastCave.isEnd){
            return listOf(path.map { it.name })
        }

        return lastCave.next
            .filter { canVisit(it, path) }
            .flatMap { traversePath(path + it, canVisit) }
    }

    override fun runPart2(input: Cave): Int {
        return -1
    }

}