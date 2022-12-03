package terrene

fun main(args: Array<String>) {
    println("Hello world")
    answer1()
    answer2()
}

fun answer1() {
    var resource = ClassLoader.getSystemResource("test.input")
    var upperToValue = 'A'.toInt() - 27;
    var lowerToValue = 'a'.toInt() - 1;
    var priorityCount = 0

    resource.readText().lines().forEach { line ->
        var first = line.substring(0, line.length / 2)
        var second = line.substring(line.length / 2, line.length)

        var firstSet = mutableSetOf<Char>()
        var secondSet = mutableSetOf<Char>()

        firstSet.addAll(first.toCharArray().toCollection(ArrayList()))
        secondSet.addAll(second.toCharArray().toCollection(ArrayList()))
        firstSet.forEach { item ->
            if(secondSet.contains(item)) {
                var value = item.toInt()


                if (value >= 'A'.toInt() && value <= 'Z'.toInt()) {
                    priorityCount += value - upperToValue
                } else {
                    priorityCount += value - lowerToValue
                }
            }
        }
//        println(badSet.contentToString())
    }
    println("Answer 1")
    println(priorityCount)
    println()
}

fun answer2() {
    var resource = ClassLoader.getSystemResource("test.input")
    var upperToValue = 'A'.toInt() - 27;
    var lowerToValue = 'a'.toInt() - 1;
    var priorityCount = 0
    var groupList = arrayOf("","","")
    var groupCounter = 0

    resource.readText().lines().forEach { line ->
        groupList[groupCounter] = line
        groupCounter++
        if (groupCounter >= 3) {
            groupCounter = 0

            var firstSet = mutableSetOf<Char>()
            firstSet.addAll(groupList[0].toCharArray().toCollection(ArrayList()))

            var secondSet = mutableSetOf<Char>()
            secondSet.addAll(groupList[1].toCharArray().toCollection(ArrayList()))

            var thirdSet = mutableSetOf<Char>()
            thirdSet.addAll(groupList[2].toCharArray().toCollection(ArrayList()))


            firstSet.forEach{ item ->
                if (secondSet.contains(item) && thirdSet.contains(item)) {
                    var value = item.toInt()

                    if (value >= 'A'.toInt() && value <= 'Z'.toInt()) {
                        priorityCount += value - upperToValue
                    } else {
                        priorityCount += value - lowerToValue
                    }
                }
            }
        }
    }
    println("Answer 2")
    println(priorityCount)
    println()
}