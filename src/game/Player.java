package game;

import java.util.List;
import java.util.LinkedList;

/**
 * Jugador de una partida
 * @author sergio
 *
 */
public class Player {
    private String name;
    private List<Card> cardsList;

    /**
     * 
     * @param name Nombre del jugador
     */
    public Player(String name) {
        this.name = name;
        this.cardsList = new LinkedList<Card>();
    }

    /**
     * Devuelve el nombre del jugador
     * @return Nombre del jugador
     */
    public String getName() {
        return this.name;
    }

    /**
     * Devuelve la puntuación obtenida por el jugador
     * @return Puntuación del jugador
     */
    public float getScore() {
        float score = 0;
        for (Card card : this.cardsList)
            score += Game.CARDS_SCORE[card.getNumber().ordinal()];
        return score;
    }

    /**
     * Añade una carta al conjunto de cartas que el jugador ha pedido
     * @param card Carta a añadir
     */
    public void addCard(Card card) {
        this.cardsList.add(card);
    }

    /**
     * Devuelve el número de cartas que el jugador ha pedido
     * @return Número de cartas
     */
    public int getCardsNumber() {
        return this.cardsList.size();
    }
}
