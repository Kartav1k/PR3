data class Card(val rank: String, val suit: String)

class Deck {
    private val ranks = listOf("6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз")
    private val suits = listOf("♥", "♦", "♠", "♣")
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
    while (true) {
        printHand("Игрок 1", player1Hand)
        printHand("Игрок 2", player2Hand)

        // Ход Игрока 1
        println("Ход Игрока 1")
        val player1Index = readLine()?.toIntOrNull()
        if (player1Index != null && player1Index in 0 until player1Hand.size) {
            val playedCard = player1Hand.removeAt(player1Index)
            println("Игрок 1 сыграл ${playedCard.rank} ${playedCard.suit}")

            // Ход Игрока 2
            println("Ход Игрока 2")
            val defendingCardIndex = readLine()?.toIntOrNull()
            if (defendingCardIndex != null && defendingCardIndex in 0 until player2Hand.size) {
                val defendingCard = player2Hand[defendingCardIndex]
                println("Игрок 2 отбивается ${defendingCard.rank} ${defendingCard.suit}")

                // Проверка, может ли игрок 2 отбиться
                if (playedCard.rank != defendingCard.rank && ranks.indexOf(playedCard.rank) < ranks.indexOf(defendingCard.rank)) {
                    println("Игрок 2 отбился")

                    // Убираем карту, которой игрок 2 отбился
                    player2Hand.removeAt(defendingCardIndex)

                    // Выдача новой карты Игроку 2
                    val newCard = deck.dealCard()
                    if (newCard != null) {
                        player2Hand.add(newCard)
                    }
                } else {
                    player2Hand.add(playedCard)
                    println("Игрок 2 берет карту")
                }
            } else {
                println("Некорректный ввод. Попробуйте еще раз.")
                player1Hand.add(playedCard)
            }

            // Выдача новой карты Игроку 1
            val newCard = deck.dealCard()
            if (newCard != null) {
                player1Hand.add(newCard)
            } else {
                println("Карты в колоде закончились. Игра завершена.")
                break
            }
        } else {
            println("Некорректный ввод. Попробуйте еще раз.")
        }

        // Вывод количества оставшихся карт в колоде
        println("Осталось карт в колоде: ${deck.cardsLeft()}")
    }
}