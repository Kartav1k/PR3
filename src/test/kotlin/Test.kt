import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck()
    }

    @Test
    fun `deck should be initialized with 36 cards`() {
        assertEquals(36, deck.cardsLeft())
    }

    @Test
    fun `dealing a card should reduce the number of cards left`() {
        val initialCardsLeft = deck.cardsLeft()
        deck.dealCard()
        assertEquals(initialCardsLeft - 1, deck.cardsLeft())
    }

    @Test
    fun `dealing cards should return distinct cards`() {
        val card1 = deck.dealCard()
        val card2 = deck.dealCard()
        assertNotEquals(card1, card2)
    }

    @Test
    fun `dealing all cards should result in an empty deck`() {
        repeat(36) {
            deck.dealCard()
        }
        assertEquals(0, deck.cardsLeft())
    }

    @Test
    fun `dealing a card from an empty deck should return null`() {
        repeat(36) {
            deck.dealCard()
        }
        assertNull(deck.dealCard())
    }
}