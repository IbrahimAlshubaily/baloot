package model

class Dealer {
    private val deck = Deck()
    private val score = IntArray(2)
    fun dealHand(): List<Card> {
        return deck.getCards(8)
    }

    fun scoreRound(cards: Array<Card>, startIdx: Int): Int {
        val winnerIdx =  cards.indices.filter {
            cards[it].suit == cards[startIdx].suit
        }.maxBy {  cards[it].value }
        val currScore = cards.sumOf { card: Card -> card.value.ordinal }
        if (winnerIdx == 0 ||  winnerIdx == 2) {
            score[0] += currScore
        } else {
            score[1] += currScore
        }
        return winnerIdx
    }
    fun getScore(): String {
        val result = if (score[0] > score[1]) {
            "A Won"
        } else if (score[0] < score[1]) {
            "B Won"
        } else {
            "TIE"
        }
        return "$result : ${score[0]} - ${score[1]} "
    }
}