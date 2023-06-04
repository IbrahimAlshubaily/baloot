package model

class Dealer {
    val deck = Deck()

    fun dealHand(): List<Card> {
        return deck.getCards(8)
    }

    fun getWinnerIdx(cards: Array<Card>, startIdx: Int): Int {
        return cards.indices.filter {
            cards[it].suit == cards[startIdx].suit
        }.maxBy {  cards[it].value }
    }
    fun reset() {
        deck.reset()
    }
}