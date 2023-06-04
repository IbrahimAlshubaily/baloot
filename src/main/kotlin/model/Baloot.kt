package model

class Baloot {
    val dealer = Dealer()
    val players = initPlayers()
    init {
        for (player in players) {
            player.setCards(dealer.dealHand())
        }
        println("START")
        var currentRoundCards = mutableListOf<Card>()
        for (round in 1..8) {
            println("Round $round")
            currentRoundCards.removeAll { true }
            for (player in players) {
                currentRoundCards.add(player.play(currentRoundCards))
            }
            println(currentRoundCards)
        }
    }
    private fun initPlayers(): Array<Player> {
        return Array(4) {
            Player()
        }
    }

}