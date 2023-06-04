package model

import model.enums.Suit
import java.lang.Math.random

class Player {
    val cards = HashMap<Suit, MutableList<Card>>()
    fun setCards(cards: List<Card>) {
        for (card in cards) {
            if ( this.cards.containsKey(card.suit)) {
                this.cards[card.suit]!!.add(card)
            } else {
                this.cards[card.suit] = mutableListOf(card)
            }
        }
        sortCards()
    }

    private fun sortCards() {
        for (suit in cards.keys){
            cards[suit]!!.sortBy { card: Card ->  card.value.ordinal}
        }
    }

    fun play(cards: List<Card>): Card {
        val selectedCard = if (cards.isEmpty()) {
            selectInitialCard(cards)
        } else if (this.cards[cards[0].suit].isNullOrEmpty()) {
            selectMismatchingCard(cards)
        } else {
            selectMatchingCard(cards)
        }
        this.cards[selectedCard.suit]!!.remove(selectedCard)
        return selectedCard
    }

    private fun selectInitialCard(cards: List<Card>): Card {
        return selectMismatchingCard(cards)
    }

    private fun selectMismatchingCard(cards: List<Card>): Card {
        for (suit in this.cards.keys.shuffled()){
            if (!this.cards[suit].isNullOrEmpty()) {
                return this.cards[suit]!![(random() * this.cards[suit]!!.size).toInt()]
            }
        }
        throw Error("Out of cards")
    }

    private fun selectMatchingCard(cards: List<Card>): Card {
        return this.cards[cards[0].suit]!![(random() * this.cards[cards[0].suit]!!.size).toInt()]
    }

}