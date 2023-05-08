package game;

/**
 * Carta de una baraja
 * @author sergio
 *
 */
public class Card {
    private CardNumber number;
    private CardSuit suit;

    /**
     * 
     * @param number Valor de la carta
     * @param suit Palo de la carta
     */
    public Card(CardNumber number, CardSuit suit) {
        this.number = number;
        this.suit = suit;
    }

    /**
     * Devuelve el valor de la carta
     * @return Valor de la carta
     */
    public CardNumber getNumber() {
        return this.number;
    }

    /**
     * Devuelve el palo de la carta
     * @return Palo de la carta
     */
    public CardSuit getSuit() {
        return this.suit;
    }

    /**
     * Devuelve la representación textual de la carta
     * @return Representación textual de la carta
     */
    @Override
    public String toString() {
        return Game.CARDS_NAMES[this.number.ordinal()] + " de " + Game.SUITS_NAMES[this.suit.ordinal()];
    }
}
