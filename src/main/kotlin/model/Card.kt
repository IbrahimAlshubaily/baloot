package model

import model.enums.Suit
import model.enums.Value

data class Card(val suit: Suit, val value: Value){
    override fun toString(): String {
        return "$suit $value"
    }
}
