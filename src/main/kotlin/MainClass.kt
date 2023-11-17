data class Card(val rank: String, val suit: String)

class Deck {
    private val ranks = listOf("6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз")
    private val suits = listOf("Черви", "Бубны", "Пики", "Трефы")
    private val cards = mutableListOf<Card>()

    init {
        for (rank in ranks) {
            for (suit in suits) {
                cards.add(Card(rank, suit))
            }
        }
        cards.shuffle()
    }

    fun dealCard(): Card? {
        return if (cards.isNotEmpty()) {
            cards.removeAt(0)
        } else {
            null
        }
    }

    fun cardsLeft(): Int {
        return cards.size
    }
}
fun printHand(player: String, hand: List<Card>) {
    println("$player рука:")
    hand.forEachIndexed { index, card ->
        println("$index - ${card.rank} ${card.suit}")
    }
}

fun main() {
    val deck = Deck()
    val ranks = listOf("6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз")
    val player1Hand = mutableListOf<Card>()
    val player2Hand = mutableListOf<Card>()

    repeat(6) {
        player1Hand.add(deck.dealCard()!!)
        player2Hand.add(deck.dealCard()!!)
    }


}