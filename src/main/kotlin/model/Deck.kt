package model

import model.enums.Suit
import model.enums.Value

class Deck {
    val cards = initDeck()
    private var nextCardIdx: Int = 0

    fun getCards(n: Int): List<Card> {
        nextCardIdx += n
        if (nextCardIdx > cards.size + 1) {
            throw Error("Out of cards: $n requested > ${cards.size - nextCardIdx + n} remaining")
        }
        return cards.slice(nextCardIdx - n until nextCardIdx) as List<Card>
    }


    private fun initDeck(): MutableList<Card?> {
        var idx = 0
        val cards = arrayOfNulls<Card>(32)
        for (suit in Suit.values()){
            for (value in Value.values()){
                cards[idx++] = Card(suit, value)
            }
        }
        nextCardIdx = 0
        cards.shuffle()
        return cards.toMutableList()
    }
}