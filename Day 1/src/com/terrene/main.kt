package com.terrene

fun main(args: Array<String>) {
    println("Hello world")
    readFileLines()
}

fun readFileLines() {
    var elfList = arrayOf(0)
    var iter = 0

    var highestCals = arrayOf(0,0,0)
    var highestSingle = 0
    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            elfList[iter] = elfList[iter] + line.toInt()
        } else {
            patDownElf(elfList[iter], highestCals)
            if (highestSingle < elfList[iter]) {
                highestSingle = elfList[iter]
            }
            iter++;
            elfList += 0;
        }
    }
    println("Answer 1")
    println(highestSingle)
    println("Answer 2")
    println(highestCals[0] + highestCals[1] + highestCals[2])
}

fun patDownElf(cals: Int, highestCals: Array<Int>) {
    var passDown = cals
    highestCals.forEachIndexed { index, elfEntry ->
        if (passDown > elfEntry) {
            highestCals[index] = passDown
            passDown = elfEntry
        }
    }
}