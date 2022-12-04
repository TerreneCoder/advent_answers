package terrene

fun main(args: Array<String>) {
    println("Hello world")
    println("Answer 1 " + answer1())
    println("Answer 2 " + answer2())
}

fun answer1(): String {
    var overlapcount = 0
    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            var elvesDuties = line.split(",")
            var elfOneDuty = elvesDuties[0].split("-")
            var elfTwoDuty = elvesDuties[1].split("-")

            var elfOneMin = elfOneDuty[0].toInt()
            var elfOneMax = elfOneDuty[1].toInt()

            var elfTwoMin = elfTwoDuty[0].toInt()
            var elfTwoMax = elfTwoDuty[1].toInt()

            if (elfOneMin <= elfTwoMin && elfOneMax >= elfTwoMax) {
//                println("$elfOneMin-$elfOneMax,$elfTwoMin-$elfTwoMax")
//                printOverlap(elfOneMin, elfTwoMin, elfOneMax, elfTwoMax)
                overlapcount++
            } else if (elfTwoMin <= elfOneMin && elfTwoMax >= elfOneMax) {
//                println("$elfOneMin-$elfOneMax,$elfTwoMin-$elfTwoMax")
//                printOverlap(elfTwoMin, elfOneMin, elfTwoMax, elfOneMax)
                overlapcount++
            }
        }
    }

    return "" + overlapcount
}

fun answer2(): String {
    var overlapcount = 0
    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        if (line != "") {
            var elvesDuties = line.split(",")
            var elfOneDuty = elvesDuties[0].split("-")
            var elfTwoDuty = elvesDuties[1].split("-")

            var elfOneMin = elfOneDuty[0].toInt()
            var elfOneMax = elfOneDuty[1].toInt()

            var elfTwoMin = elfTwoDuty[0].toInt()
            var elfTwoMax = elfTwoDuty[1].toInt()

            if ((elfOneMin <= elfTwoMax && elfOneMin >= elfTwoMin)
                || (elfOneMax >= elfTwoMin && elfOneMax <= elfTwoMax)) {
//                println("$elfOneMin-$elfOneMax,$elfTwoMin-$elfTwoMax")
//                printOverlap(elfOneMin, elfTwoMin, elfOneMax, elfTwoMax)
                overlapcount++
            } else if ((elfTwoMin <= elfOneMax && elfTwoMin >= elfOneMin)
                || (elfTwoMax >= elfOneMin && elfTwoMax <= elfOneMax)) {
//                println("$elfOneMin-$elfOneMax,$elfTwoMin-$elfTwoMax")
//                printOverlap(elfTwoMin, elfOneMin, elfTwoMax, elfOneMax)
                overlapcount++
            }
        }
    }

    return "" + overlapcount
}

fun printOverlap(min1:Int,min2:Int,max1:Int,max2:Int) {
    var first = ""
    var second = ""
    for (i in min1 - 2 until max1+4 step 1) {
        if (i in min1..max1) {
            first += "$i "
        } else {
            if (i > 9) {
                first += " "
            }
            if (i > 99) {
                first += " "
            }
            first += ". "
        }

        if (i in min2..max2) {
            second += "$i "
        } else {
            if (i > 9) {
                second += " "
            }
            if (i > 99) {
                second += " "
            }
            second += ". "
        }
    }
    println(first)
    println(second)
}