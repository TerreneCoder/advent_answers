package terrene

enum class Direction(val x: Int, val y: Int) {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1);
}

fun main(args: Array<String>) {
    println("Hello world")
    println("Answer 1 " + answer1())
    println("Answer 2 " + answer2())
}

fun answer1(): String {
    var visibleTrees = 0
    var treeGrid = ArrayList<ArrayList<Int>>()
    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            treeGrid.add(line.toCharArray().map{ Character.getNumericValue(it) }.toList() as ArrayList<Int>)
        }
    }

    visibleTrees += treeGrid[0].size + treeGrid[0].size - 1 + treeGrid.size - 1 + treeGrid.size - 2

    var width = treeGrid[0].size
    var height = treeGrid.size

    for(x in 1 until width - 1 step 1) {
        for(y in 1 until height - 1 step 1) {
            println("" + x + y)
            if(checkTree(treeGrid, x, y)) {
                visibleTrees++
            }
        }
    }

    return "" + visibleTrees
}

fun answer2(): String {
    var treeGrid = ArrayList<ArrayList<Int>>()
    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            treeGrid.add(line.toCharArray().map{ Character.getNumericValue(it) }.toList() as ArrayList<Int>)
        }
    }

    var bestTree = 0

    var width = treeGrid[0].size
    var height = treeGrid.size

    for(x in 1 until width - 1 step 1) {
        for(y in 1 until height - 1 step 1) {
            println("" + x + y)
            var currentScore = viewScore(treeGrid, x, y)
            if (currentScore > bestTree) {
                bestTree = currentScore
            }
        }
    }

    return "" + bestTree
}

fun viewScore(treeGrid: ArrayList<ArrayList<Int>>, x: Int, y: Int): Int {
    var left: Int
    var right: Int
    var up: Int
    var down: Int

    left = count(Direction.LEFT, treeGrid, x, y)
    right = count(Direction.RIGHT, treeGrid, x, y)
    up = count(Direction.UP, treeGrid, x, y)
    down =  count(Direction.DOWN, treeGrid, x, y)

    println("$left $right $up $down")
//    if (left || right || up || down) {
//        return true
//    }

    return left * right * up * down
}

fun count(dir: Direction, treeGrid: ArrayList<ArrayList<Int>>, startingX: Int, startingY: Int): Int {
    var maxX = treeGrid[0].size
    var maxY = treeGrid.size

    var x = startingX + dir.x
    var y = startingY + dir.y

    var treeVal = treeGrid[startingX][startingY]
    var compare: Int

    var count = 0
    var visible = true

    while (x in 0 until maxX && y in 0 until maxY && visible) {
        compare = treeGrid[x][y]
        count++
        if (compare >= treeVal) {
            visible = false
        }
        x += dir.x
        y += dir.y
    }

    return count
}


fun checkTree(treeGrid: ArrayList<ArrayList<Int>>, x: Int, y: Int): Boolean {
    var left: Boolean
    var right: Boolean
    var up: Boolean
    var down: Boolean

    left = walk(Direction.LEFT, treeGrid, x, y)
    right = walk(Direction.RIGHT, treeGrid, x, y)
    up = walk(Direction.UP, treeGrid, x, y)
    down =  walk(Direction.DOWN, treeGrid, x, y)

    println("$left $right $up $down")
    if (left || right || up || down) {
        return true
    }

    return false
}

fun walk(dir: Direction, treeGrid: ArrayList<ArrayList<Int>>, startingX: Int, startingY: Int): Boolean {
    var maxX = treeGrid[0].size
    var maxY = treeGrid.size

    var x = startingX + dir.x
    var y = startingY + dir.y

    var treeVal = treeGrid[startingX][startingY]
    var compare: Int

    var visible = true

    while (x in 0 until maxX && y in 0 until maxY && visible) {
        compare = treeGrid[x][y]
        if (compare >= treeVal) {
            visible = false
        }
        x += dir.x
        y += dir.y
    }

    return visible
}
