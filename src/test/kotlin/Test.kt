import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    lateinit var deck: Deck

    //Создание новой колоды перед каждым тестом
    @BeforeEach
    fun setUp() {
        deck = Deck()
    }

    //Проверка на наличие 36 карт в колоде
    @Test
    fun ChekingForCardsInTheDeck() {
        assertEquals(36, deck.cardsLeft())
    }

    //Проверка на изменяемость в колоде
    @Test
    fun CheckingForChangeabilityInTheDeck() {
        val initialCardsLeft = deck.cardsLeft()
        deck.dealCard()
        assertEquals(initialCardsLeft - 1, deck.cardsLeft())
    }

    //Проверка на возврат различных карт
    @Test
    fun CheckingForTheReturnOfVariousCards() {
        val card1 = deck.dealCard()
        val card2 = deck.dealCard()
        assertNotEquals(card1, card2)
    }

    //Проверка на пустую колоду
    @Test
    fun CheckingForAnEmptyDeck() {
        repeat(36) {
            deck.dealCard()
        }
        assertEquals(0, deck.cardsLeft())
    }

    //Проверка на возврат нуля при исчерпании колоды
    @Test
    fun CheckingForExhaustionOfTheDeck() {
        repeat(36) {
            deck.dealCard()
        }
        assertNull(deck.dealCard())
    }
}