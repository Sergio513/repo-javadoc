package game;

import java.util.List;
import java.util.ArrayList;

/**
 * Baraja de cartas
 * @author sergio
 *
 */
public class Deck {
    private List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>(40);

        // Generar la baraja
        for (CardSuit suit : CardSuit.values())
            for (CardNumber number : CardNumber.values())
                this.deck.add(new Card(number, suit));
    }

    /**
     * Devuelve una carta de una posición aleatoria de la baraja
     * @return Carta aleatoria
     */
    public Card getRandomCard() {
        Card card = null;

        if (this.deck.size() > 0) {
            // Coger una carta de una posición aleatoria
            int cardPosition = (int) (Math.random() * (this.deck.size() - 1));
            card = this.deck.get(cardPosition);
            this.deck.remove(cardPosition);
        }

        return card;
    }
}
