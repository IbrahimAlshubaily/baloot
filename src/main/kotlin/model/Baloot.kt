package model

import java.util.*

class Baloot {
    val dealer = Dealer()
    val players = initPlayers()
    init {
        deal()
        play()
    }

    private fun deal() {
        for (player in players) {
            player.setCards(dealer.dealHand())
        }
    }
    private fun play() {
        println("START")
        var startIdx = 0
        var currentRoundCards = arrayOfNulls<Card>(4)
        for (round in 1..8) {
            println("Round $round")
            for (idx in startIdx until startIdx + 4) {
                currentRoundCards[idx % 4] = players[idx % 4].play(currentRoundCards.filterNotNull())
                // println("${idx % 4}  ${currentRoundCards[idx % 4]}")
            }
            printRoundCards(currentRoundCards  as Array<Card>, startIdx)
            startIdx = dealer.getWinnerIdx(currentRoundCards as Array<Card>, startIdx)
            println("Winner idx : $startIdx")
            println()
            Arrays.fill(currentRoundCards, null)
        }
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
            print("\u001b[31m" + card + "\u001b[0m")
        } else {
            print(card)
        }
    }

    private fun initPlayers(): Array<Player> {
        return Array(4) {
            Player()
        }
    }

}