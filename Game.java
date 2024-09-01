import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int WINNING_POSITION = 100;
    private static final int[][] SNAKE_POSITIONS = {{16, 6}, {47, 26}, {49, 11}, {56, 53}, {62, 19}, {64, 60}, {87, 24},{95, 75}, {98, 78}};
    private static final int[][] LADDER_POSITIONS = {{1,38},{4,14},{9,31},{21,42},{28,84},{36,44},{51,67},{71,91},{80,100}};

    private Map<Integer, Integer> ladders;
    private Map<Integer, Integer> snakes;
    private Random random;
    int Player1Position = 0;
    int Player2Position = 0;

    public Game() {
        ladders = new HashMap<>();
        snakes = new HashMap<>();
        random = new Random();
        for (int i =0 ; i < 9 ;i++) {
            ladders.put(LADDER_POSITIONS[i][0] ,LADDER_POSITIONS[i][1] );
            snakes.put(SNAKE_POSITIONS[i][0] ,SNAKE_POSITIONS[i][1] );
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int currentPlayer = 1;
        System.out.println("Game starts! \n");
        int currentPlayerPosition = 0;

        while (Player1Position < WINNING_POSITION && Player2Position < WINNING_POSITION) {
            System.out.println("\nPlayer " + currentPlayer + " here are some suggestions for you: ");
            displayLadderRequiredThrow(currentPlayer == 1 ? Player1Position : Player2Position);
            displaySnakeDangerMessage(currentPlayer == 1 ? Player1Position : Player2Position);
            System.out.print("\nPlayer " + currentPlayer + "'s turn, press Enter to roll the dice: ");
            scanner.nextLine();
            int diceRoll = rollDice();
            System.out.println("Player " + currentPlayer + " rolled the dice and got " + diceRoll);

            if (currentPlayer == 1) {
                Player1Position = updatePlayerPosition(1, Player1Position, diceRoll);
                System.out.println("Player 1 moved to position " + Player1Position);
            } else {
                Player2Position = updatePlayerPosition(2, Player2Position, diceRoll);
                System.out.println("Player 2 moved to position " + Player2Position);
            }

            displayBoard(Player1Position, Player2Position); // Display the board after each move

            if ((currentPlayer == 1 && Player1Position == WINNING_POSITION) ||
                    (currentPlayer == 2 && Player2Position == WINNING_POSITION)) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (diceRoll < 6) {
                currentPlayer = currentPlayer == 1 ? 2 : 1;
            }
        }

        scanner.close();
    }


    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    private int updatePlayerPosition(int currentPlayer, int currentPlayerPosition, int diceRoll) {
        int newPosition = currentPlayerPosition + diceRoll;

        if (ladders.containsKey(newPosition)) {
            newPosition = ladders.get(newPosition);
            System.out.println("Ladder found! Player " + currentPlayer + " climbs up to position " + newPosition);
        }

        if (snakes.containsKey(newPosition)) {
            newPosition = snakes.get(newPosition);
            System.out.println("Snake bite! Player " + currentPlayer + " slides down to position " + newPosition);
        }

        return newPosition;
    }

    private void displayLadderRequiredThrow(int position) {
        int i =0 ;
        int pos = LADDER_POSITIONS[0][0];
        while(position > pos && i < ladders.size() - 1)
        {
            i++ ;
            pos = LADDER_POSITIONS[i][0];
        }
        int req = pos - position;

        System.out.println("Ladder ahead! Require a " + req + " to climb a ladder");
        return;
    }
    private void displaySnakeDangerMessage(int position) {
        int i =1;
        int pos = SNAKE_POSITIONS[0][0] ;

        while(position > pos && i < snakes.size() - 1)
        {
            i++ ;
            pos = SNAKE_POSITIONS[i][0];
        }
        int dang = pos - position;
        System.out.println("Danger! Snake ahead. You are " + dang + " steps away from position snake");
        return ;
    }

    public void displayBoard(int player1Position, int player2Position) {
        int size = 10; // Size of the board (10x10)
        int cellWidth = 10; // Width of each cell for better spacing

        // Create a map to store visual indicators for snakes and ladders
        Map<Integer, String> boardContent = new HashMap<>();

        // Populate the board with snake and ladder indicators
        for (int[] snake : SNAKE_POSITIONS) {
            boardContent.put(snake[0], "S->" + snake[1]); // Snake start
            boardContent.put(snake[1], "<-S"); // Snake end
        }
        for (int[] ladder : LADDER_POSITIONS) {
            boardContent.put(ladder[0], "L->" + ladder[1]); // Ladder start
            boardContent.put(ladder[1], "<-L"); // Ladder end
        }

        for (int row = size; row >= 1; row--) {
            // Display the top border of each cell
            for (int col = 1; col <= size; col++) {
                System.out.print("+");
                for (int i = 0; i < cellWidth; i++) {
                    System.out.print("-");
                }
            }
            System.out.println("+");

            // Display the content of each cell
            for (int col = 1; col <= size; col++) {
                int cellNumber = (row - 1) * size + col;
                String content = "";

                if (cellNumber == player1Position) {
                    content = "P1"; // Player 1 position
                } else if (cellNumber == player2Position) {
                    content = "P2"; // Player 2 position
                } else if (boardContent.containsKey(cellNumber)) {
                    content = boardContent.get(cellNumber); // Snake or ladder
                } else {
                    content = String.format("%2d", cellNumber); // Cell number
                }

                System.out.print("| " + String.format("%-" + (cellWidth - 2) + "s", content) + " ");
            }
            System.out.println("|");

            // Display the bottom border of each cell
            for (int col = 1; col <= size; col++) {
                System.out.print("+");
                for (int i = 0; i < cellWidth; i++) {
                    System.out.print("-");
                }
            }
            System.out.println("+");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.displayBoard(0,0);
        game.playGame();
    }
}
