package model

fun minMax(playerIdx: Int, playerCards: MutableSet<Card>, roundCards: MutableList<Card>, seenCards: Set<Card>): Card {

    val options = if (roundCards.isEmpty()) {
        playerCards
    } else {
        playerCards.filter { it.suit == roundCards[0].suit }
    }

    if (options.isEmpty()) {
        return playerCards.minBy { it.value }
    }

    val possibleCards = Deck().cards.toMutableSet()
    possibleCards.removeAll(seenCards)
    possibleCards.removeAll(roundCards.toSet())
    possibleCards.removeAll(playerCards)


    return options.maxBy {
        roundCards.add(it)
        val expectedValue = min(playerIdx, roundCards, possibleCards)
        roundCards.remove(it)
        expectedValue
    }

}

fun min(playerIdx: Int, roundCards: MutableList<Card>, possibleCards: Set<Card?>): Int {
    if (roundCards.size == 4) {
        return eval(playerIdx, roundCards)
    }

    return possibleCards.filter { it !in roundCards }.minOf {
        roundCards.add(it!!)
        val expectedValue = max(playerIdx, roundCards, possibleCards)
        roundCards.remove(it)
        expectedValue
    }
}

fun max(playerIdx: Int, roundCards: MutableList<Card>, possibleCards: Set<Card?>): Int {
    if (roundCards.size == 4) {
        return eval(playerIdx, roundCards)
    }

    return possibleCards.filter { it !in roundCards }.maxOf {
        roundCards.add(it!!)
        val expectedValue = min(playerIdx, roundCards, possibleCards)
        roundCards.remove(it)
        expectedValue
    }
}

private fun eval(playerIdx: Int, roundCards: List<Card>): Int {
    val winnerIdx = roundCards.indices.filter { roundCards[it].suit == roundCards[0].suit }.maxBy { roundCards[it].value }
    val score = roundCards.sumOf { card: Card -> card.value.ordinal }
    // True = Team 1 , False = Team 2
    val team = playerIdx == 0 || playerIdx == 2
    val winner = (winnerIdx == 0 || winnerIdx == 2)
    if (team == winner) {
        return score
    }
    return -score
}