package game;

import java.util.InputMismatchException;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase principal del juego
 * @author sergio
 *
 */
public class Game {
    private static final float SCORE_LIMIT = 7.5f;
    private List<Player> playersList;
    private List<Player> winnersList;
    private Scanner scanner;
    private Deck deck;

    // Indexado por la enumeración "CardNumber"
    public static final String[] CARDS_NAMES = { "As", "2", "3", "4", "5", "6", "7", "Sota", "Caballo", "Rey" };
    // Indexado por la enumeración "CardNumber"
    public static final float[] CARDS_SCORE = { 1, 2, 3, 4, 5, 6, 7, 0.5f, 0.5f, 0.5f };
    // Indexado por la enumeración "CardSuit"
    public static final String[] SUITS_NAMES = { "espadas", "copas", "oros", "bastos" };

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 8;

    public Game() {
        this.playersList = new LinkedList<Player>();
        this.winnersList = new LinkedList<Player>();
        this.scanner = new Scanner(System.in);
        this.deck = new Deck();
    }

    /**
     * Método principal de una partida
     */
    public void run() {
        registerPlayers();
        for (Player player : this.playersList) {
            System.out.print("\n\n____________________________________________________________\n\n");
            playerTurn(player);
        }

        System.out.print("\n\n____________________________________________________________\n\n");
        printWinner();
        System.out.print("\n\n");
    }

    /**
     * Soliticar por teclado los jugadores que participarán en la partida
     */
    private void registerPlayers() {
        int nPlayers = 0;
        boolean inputError;
        do {
            inputError = false;

            System.out.print("Introduzca el número de jugadores: ");
            try {
                nPlayers = scanner.nextInt();
                if (!(nPlayers >= MIN_PLAYERS && nPlayers <= MAX_PLAYERS)) {
                    System.out.println("Mínimo dos jugadores y máximo 8");
                    inputError = true;
                }
            }
            // Este bloque de código se ejecutará cuando no se haya introducido un número
            catch (InputMismatchException e) {
                System.out.println("¡Debe introducir un número!");

                // Limpiar la entrada
                scanner.nextLine();

                inputError = true;
            }
        } while (inputError);

        // Limpiar el salto de línea
        scanner.nextLine();

        for (int i = 1; i <= nPlayers; i++) {
            System.out.print("Introduzca el nombre del jugador " + i + ": ");
            String name = scanner.nextLine();
            this.playersList.add(new Player(name));
        }
    }

    /**
     * Método que se corresponde con el turno de un jugador
     * @param player Jugador que tiene el turno
     */
    private void playerTurn(Player player) {
        System.out.println("¡Turno de " + player.getName() + "!");

        boolean endTurn = false;
        while (!endTurn) {
            if (player.getCardsNumber() == 0)
                System.out.print("Introduzca un comando (pedir): ");
            else
                System.out.print("Introduzca un comando (pedir o plantarse): ");

            String command = scanner.nextLine().toLowerCase();
            if (command.equals("pedir")) {
                // Generar una carta aleatoria
                Card card = deck.getRandomCard();

                // Añadir la carta al jugador
                player.addCard(card);

                // Imprimir la carta. Se llama automáticamente al método "toString" de la clase
                // "Card"
                System.out.println(card);

                if (player.getScore() > SCORE_LIMIT)
                    endTurn = true;
            }
            else if (command.equals("plantarse")) {
                if (player.getCardsNumber() == 0)
                    System.out.println("¡Debe pedir al menos una carta!");
                else
                    endTurn = true;
            }
            else {
                System.out.println("Comando no reconocido");
            }
        }

        System.out.println();
        printScore(player);

        updateWinnersList(player);
    }

    /**
     * Imprime la puntuación de un jugador
     * @param player Puntuación del jugador
     */
    private void printScore(Player player) {
        System.out.println("Tu puntuación: " + player.getScore());
        if (player.getScore() > SCORE_LIMIT) {
            System.out.println("¡Te has pasado!");
        }
    }

    /**
     * Actualiza la lista de ganadores tras la terminación del turno de un jugador
     * 
     * @param player Jugador que ha terminado su turno
     */
    private void updateWinnersList(Player player) {
        // Si el jugador se pasa, la lista de ganadores no se verá afectada
        if (player.getScore() <= SCORE_LIMIT) {
            if (this.winnersList.isEmpty()) {
                this.winnersList.add(player);
            }
            else {
                Player firstWinner = this.winnersList.get(0);
                /*
                 * Si la puntuación del jugador es mayor a la puntuación de los ganadores
                 * actuales, éstos dejan de ser ganadores y pasa a serlo el jugador
                 */
                if (player.getScore() > firstWinner.getScore()) {
                    this.winnersList.clear();
                    this.winnersList.add(player);
                }
                else if (player.getScore() == firstWinner.getScore()) {
                    /*
                     * Si el jugador ha obtenido la puntuación de los ganadores actuales con menos
                     * cartas, éstos dejan de ser ganadores y pasa a serlo el jugador
                     */
                    if (player.getCardsNumber() < firstWinner.getCardsNumber()) {
                        this.winnersList.clear();
                        this.winnersList.add(player);
                    }
                    /*
                     * Si el jugador ha obtenido la puntuación de los ganadores actuales con el
                     * mismo número de cartas, se añade a la lista de ganadores
                     */
                    else if (player.getCardsNumber() == firstWinner.getCardsNumber()) {
                        this.winnersList.add(player);
                    }
                }
            }
        }
    }

    /**
     * Mostrar por pantalla el resultado final de la partida
     */
    private void printWinner() {
        if (winnersList.size() == 0)
            System.out.println("¡No hay ganadores!");
        else {
            if (winnersList.size() == 1)
                System.out.print("Ganador");
            else
                System.out.print("Empate");
            System.out.print(" con " + this.winnersList.get(0).getScore() + " puntos: ");
        }

        for (Player player : winnersList)
            System.out.print(player.getName() + " ");
    }
}
