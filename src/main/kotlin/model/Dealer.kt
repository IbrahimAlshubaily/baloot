package model

class Dealer {
    val deck = Deck()

    fun dealHand(): List<Card> {
        return deck.getCards(8)
    }

    fun reset() {
        deck.reset()
    }
}