package terrene

fun main(args: Array<String>) {
    println("Hello world")
    println("Answer 1 " + answer1())
    println("Answer 2 " + answer2())
}

fun answer1(): String {
    var crateList = ClassLoader.getSystemResource("crates.input")

    var stackList = ArrayList<MutableList<Char>>();

    crateList.readText().lines().forEach { input ->
        if (input != "") {
            stackList.add(input.toCharArray().toMutableList())
        }
    }

    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            var instructions = line.split(" ")
            var count = instructions[1].toInt()
            var from = instructions[3].toInt()
            var to = instructions[5].toInt()

            for (i in 0 until count step 1) {
                singleMoveBox(stackList[from-1], stackList[to-1])
            }
        }
    }

    var output = ""
    stackList.forEach{ stack ->
        output += stack.last()
    }

    return "" + output
}

fun answer2(): String {
    var crateList = ClassLoader.getSystemResource("crates.input")

    var stackList = ArrayList<MutableList<Char>>();

    crateList.readText().lines().forEach { input ->
        if (input != "") {
            stackList.add(input.toCharArray().toMutableList())
        }
    }

    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            var instructions = line.split(" ")
            var count = instructions[1].toInt()
            var from = instructions[3].toInt()
            var to = instructions[5].toInt()

            multiMoveBox(stackList[from-1], stackList[to-1], count)
        }
    }

    var output = ""
    stackList.forEach{ stack ->
        output += stack.last()
    }

    return "" + output
}

fun singleMoveBox(from: MutableList<Char>, to: MutableList<Char>) {
    to.add(from.last())
    from.removeAt(from.size-1)
}

fun multiMoveBox(from: MutableList<Char>, to: MutableList<Char>, count: Int) {
//    println(from.size.toString() + " : " + count + " : " + from.subList(from.size - count, from.size))
    to.addAll(from.subList(from.size - count, from.size))
    val length = from.size - 1
    for(i in length downTo length - count + 1 step 1) {
//        println(from[i])
        from.removeAt(i)
    }
//    println(from.size.toString() + " : " + from.toString())
//    println()
}