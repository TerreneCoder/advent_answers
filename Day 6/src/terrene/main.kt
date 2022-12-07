package terrene

fun main(args: Array<String>) {
    println("Hello world")
    println("Answer 1 " + answer1())
    println("Answer 2 " + answer2())
}

@ExperimentalStdlibApi
fun answer1(): String {
    var output = ""
    var resource = ClassLoader.getSystemResource("test.input")
    var input = ""
    var msgLength = 4
    resource.readText().lines().forEach { line ->
        input = line
    }

    var queue = ArrayDeque<Char>()
    var iter = 0
    run loop@{
        input.toCharArray().forEach { packet ->
            iter++
            if (queue.size < msgLength || !isUniqueTakeTwo(queue, packet, msgLength)) {
                queue.addLast(packet)
                if (queue.size > msgLength) {
                    queue.removeFirst()
                }
            } else {
                println(queue.toString())
                output = iter.toString()
                return@loop
            }
        }
    }

    return "" + output
}

fun answer2(): String {
    var output = ""
    var resource = ClassLoader.getSystemResource("test.input")
    var input = ""
    var msgLength = 14
    resource.readText().lines().forEach { line ->
        input = line
    }

    var queue = ArrayDeque<Char>()
    var iter = 0
    run loop@{
        input.toCharArray().forEach { packet ->
            iter++
            if (queue.size < msgLength || !isUniqueTakeTwo(queue, packet, msgLength)) {
                queue.addLast(packet)
                if (queue.size > msgLength) {
                    queue.removeFirst()
                }
            } else {
                println(queue.toString())
                output = iter.toString()
                return@loop
            }
        }
    }

    return "" + output
}

fun isUniqueTakeTwo(queue: ArrayDeque<Char>, packet: Char, msgSize: Int): Boolean {
    var subList = queue.subList(1, msgSize)
    if (subList.contains(packet) ||
        subList.size != subList.toSet().size) {
        return false
    }
    return true
}