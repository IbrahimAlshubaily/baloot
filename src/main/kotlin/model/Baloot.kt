package model

class Baloot {
    val dealer = Dealer()
    val players = initPlayers()
    private fun initPlayers(): Array<Player> {
        return Array<Player>(4) {
            Player()
        }
    }

}