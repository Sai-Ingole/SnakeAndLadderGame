# SnakeAndLadderGame

This is a console-based Snake and Ladder game implemented in Java. The game supports two players, with a visual representation of the board, including snakes, ladders, and player positions.

## Features

- **Snakes**: Represented on the board with a starting point (`S->`) and an endpoint (`<-S`), which slide the player down.
- **Ladders**: Represented on the board with a starting point (`L->`) and an endpoint (`<-L`), which move the player up.
- **Player Positions**: Displayed as `P1` and `P2` on the board.
- **Dice Roll**: The game simulates a dice roll for each player's turn.
- **Board Display**: The 10x10 game board is displayed after each move, with indicators for snakes, ladders, and player positions.

## Technical Details

### Data Structures

- **HashMap**: 
  - The game uses `HashMap<Integer, Integer>` to store the positions of snakes and ladders. 
  - The key represents the starting point of the snake or ladder, and the value represents the endpoint.
  - This data structure allows for efficient O(1) lookup when a player lands on a position to check if there is a snake or ladder at that position.

### Dice Simulation

- **Random Class**: 
  - The game uses the `Random` class to simulate rolling a six-sided dice.
  - The `rollDice()` method returns a random integer between 1 and 6 using `random.nextInt(6) + 1`.
  - This randomness ensures that the dice rolls are unpredictable, simulating the real-life uncertainty of rolling a dice.

## How to Play

1. **Starting the Game**: Both players start at position 0.
2. **Rolling the Dice**: Players take turns rolling the dice by pressing Enter.
3. **Movement**:
   - If a player lands on the bottom of a ladder, they move up to the top of the ladder.
   - If a player lands on the head of a snake, they slide down to the tail of the snake.
4. **Winning the Game**: The first player to reach position 100 wins the game.

## How to Run the Game

1. **Clone the Repository**: Clone this repository to your local machine.
   ```sh
   git clone <repository-url>

