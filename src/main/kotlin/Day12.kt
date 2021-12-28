class Day12(input: List<String>) : Day<Day12.Cave, Int>(input) {

    class Cave(val name: String, val next: MutableList<Cave>){
        val isStart = name == "start"
        val isEnd = name == "end"
        val isBig = name.all { it.isUpperCase() }
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
            //big caves are large enough that it might be worth visiting them multiple times
            if(cave.isBig){
                return@traversePath true
            }

            //all paths you find should visit small caves at most once
            if( path.none { it.name == cave.name }) {
                return@traversePath true
            }

            return@traversePath false
        }.count()
    }

    override fun runPart2(input: Cave): Int {
        return traversePath(listOf(input)) { cave, path ->
            //big caves can be visited any number of times
            if(cave.isBig){
                return@traversePath true
            }

            //the caves named start and end can only be visited exactly once each
            if(cave.isStart){
                return@traversePath false
            }

            //a single small cave can be visited at most twice
            if( path.none { it.name == cave.name }) {
                return@traversePath true
            }
            if(path.filterNot { it.isBig }.groupBy { it }.none {it.value.size == 2}) {
                return@traversePath true
            }

            return@traversePath false
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

}