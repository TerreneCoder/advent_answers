package com.terrene



fun main(args: Array<String>) {
    println("Hello world")
    readFileLines()
}

fun readFileLines() {

    var totalScore = 0
    var totalCorrectedScore = 0
    var resource = ClassLoader.getSystemResource("test.input")
    resource.readText().lines().forEach { line ->
        val duel = line.split(" ")

        var enemyChoice = choices.forEnemy(duel[0])

        if (enemyChoice != null) {
            var personChoice = choices.forPersonal(duel[1])
            if (personChoice != null) {
                var outcome = personChoice.doesItBeat(enemyChoice.name)
                totalScore += outcome.score + personChoice.score
            }

            var outcome = gameState.forOutcome(duel[1])
            if (outcome != null) {
                var personChoice = enemyChoice.getOutcomeAnswer(outcome)
//            println(enemyChoice)
//            println(personChoice)
//            println(outcome)
                totalCorrectedScore += personChoice.score + outcome.score
//            println(totalScore)
//            println(System.lineSeparator())
            }
        }
    }
    println(totalScore)
    println(totalCorrectedScore)
}
