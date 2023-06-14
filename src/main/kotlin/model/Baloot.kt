package model

import java.lang.Exception
import java.util.Arrays


class Baloot {
    private val dealer = Dealer()
    private val players = List<MutableSet<Card>>(4) {
        mutableSetOf()
    }

    init {
        deal()
        rollOut()
    }

    private fun deal() {
        for (player in players) {
            player.addAll(dealer.dealHand().sortedWith(compareBy(Card::suit, Card::value)))
        }
    }
    private fun rollOut() {
        var startIdx = 0
        var curPlayerIdx = 0
        val roundOrderedCards = arrayOfNulls<Card>(4)
        val roundCards = mutableListOf<Card>()
        val seenCards = mutableSetOf<Card>()
        for (round in 1..8) {
            for (idx in startIdx until startIdx + 4) {
                curPlayerIdx = idx % 4
                val selectedCard = if (curPlayerIdx == 2) {
                    selectCardFromConsole(players[curPlayerIdx], roundCards)
                } else {
                    minMax(curPlayerIdx, players[curPlayerIdx], roundCards, seenCards )
                }
                roundOrderedCards[curPlayerIdx] = selectedCard
                roundCards.add(selectedCard)
                players[curPlayerIdx].remove(selectedCard)
                seenCards.add(selectedCard)
            }
            printRoundCards(roundOrderedCards  as Array<Card>, startIdx)
            startIdx = dealer.scoreRound(roundOrderedCards as Array<Card>, startIdx)
            Arrays.fill(roundOrderedCards, null)
            roundCards.clear()
            println()
        }
        println(dealer.getScore())
    }

    private fun readInt(): Int {
        return try {
            readln().split(' ').first().toInt()
        } catch (e: Exception) {
            readInt()
        }

    }

    private fun selectCardFromConsole(cards: MutableSet<Card>, roundCards: MutableList<Card>): Card {
        println(roundCards)
        var tmp = cards.toList()
        if (roundCards.isNotEmpty()) {
            tmp = tmp.sortedBy { it.suit ==  roundCards[0].suit}.reversed()
        }
        var idx = 0
        for (card in tmp) {
            println(""+idx++ +": "+ card)
        }
        val cardIdx = readInt()
        return tmp[cardIdx]
    }

    private fun printRoundCards(cards: Array<Card>, startIdx: Int) {
        val padLen = 50
        val padStr = " "
        print(padStr.repeat(padLen))
        printCard(cards[0], 0, startIdx)
        println(padStr.repeat(padLen))
        println()
        printCard(cards[1], 1, startIdx)
        print(padStr.repeat((padLen * 1.5).toInt()))
        printCard(cards[3], 3, startIdx)
        println()
        print(padStr.repeat(padLen))
        printCard(cards[2], 2, startIdx)
        println(padStr.repeat(padLen))
        println()
        println()
        println()
        println()

    }

    private fun printCard(card: Card, idx: Any, startIdx: Int) {
        if (idx == startIdx) {
            print("\u001B[32m" + card + "\u001b[0m")
        } else {
            print(card)
        }
    }
}