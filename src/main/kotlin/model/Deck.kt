package model

import model.enums.Suit
import model.enums.Value

class Deck {
    val cards = arrayOfNulls<Card>(32)
    var nextCardIdx: Int = 0

    init {
        initDeck()
    }

    fun getCards(n: Int): List<Card> {
        nextCardIdx += n
        if (nextCardIdx > cards.size + 1) {
            throw Error("Out of cards: $n requested > ${cards.size - nextCardIdx + n} remaining")
        }
        return cards.slice(nextCardIdx - n until nextCardIdx) as List<Card>
    }


    private fun initDeck() {
        var idx = 0
        for (suit in Suit.values()){
            for (value in Value.values()){
                cards[idx++] = Card(suit, value)
            }
        }
        nextCardIdx = 0
        cards.shuffle()
    }

    fun reset() {
        initDeck()
    }
}