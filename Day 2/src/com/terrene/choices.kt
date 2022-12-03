package com.terrene

enum class choices(val enemy: String, val personal: String, val beats: String, val loses: String, val score: Int) {
    ROCK("A", "X", "SCISSORS", "PAPER", 1),
    PAPER("B", "Y", "ROCK", "SCISSORS", 2),
    SCISSORS("C", "Z", "PAPER", "ROCK", 3);

    companion object {
        fun forEnemy(enemy: String) = values().find { it.enemy == enemy }
        fun forPersonal(personal: String) = values().find { it.personal == personal }
        fun forName(name: String) = values().find { it.name == name }
    }



    fun doesItBeat(enemy: String): gameState {
        if (enemy == beats) {
            return gameState.WIN
        } else if (enemy == name) {
            return gameState.DRAW
        }
        return gameState.LOSE
    }

    fun getOutcomeAnswer(outcome: gameState): choices {
        var answer = SCISSORS
        when (outcome) {
            gameState.WIN -> answer = valueOf(loses)
            gameState.LOSE -> answer = valueOf(beats)
            gameState.DRAW -> answer = this
        }
        return answer
    }
}

enum class gameState(val score: Int, val outcome: String) {
    WIN(6, "Z"),
    LOSE(0, "X"),
    DRAW(3, "Y");

    companion object {
        fun forOutcome(outcome: String) = gameState.values().find { it.outcome == outcome }
    }
}