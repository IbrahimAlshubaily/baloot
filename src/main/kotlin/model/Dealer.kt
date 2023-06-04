package model

class Dealer {
    val deck = Deck()
    init {
        println( dealHand())
        println( dealHand())
        println( dealHand())
        println( dealHand())
        reset()
        println( dealHand())
        println( dealHand())
        println( dealHand())
        println( dealHand())
    }

    fun dealHand(): List<Card> {
        return deck.getCards(8)
    }

    fun reset() {
        deck.reset()
    }
}