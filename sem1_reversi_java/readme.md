Reversi Game in Java
This is a Java implementation of the classic board game Reversi (also known as Othello). The game is played on a square board where two players take turns placing their pieces (Black and White) on the board, with the goal of having the majority of their pieces on the board at the end of the game. The game includes features like move validation, flipping opponent pieces, and determining the winner.

Features
Dynamic Board Size: The game allows users to specify the board size (must be an even number and at least 4x4).

Move Validation: Ensures that all moves are valid according to Reversi rules.

Piece Flipping: Automatically flips opponent pieces when a valid move is made.

Turn Management: Alternates turns between players and handles cases where a player cannot make a move.

Win Condition Detection: Determines the winner based on the number of pieces on the board at the end of the game.

User-Friendly Interface: Displays the board with row and column indices for easy navigation.

How to Play
Start the Game: Run the Reversi class.

Enter Board Size: Input an even number (4 or above) to set the board size.

Make Moves: Players take turns entering their moves by specifying the row and column indices.

Game End: The game ends when neither player can make a valid move. The winner is determined by the number of pieces on the board.

Code Structure
Key Components
Constants:

EMPTY_CELL, BLACK_PIECE, WHITE_PIECE: Represent the state of each cell on the board.

Main Method:

Initializes the game, including board setup and user input handling.

Helper Methods:

getBoardSize(Scanner scanner): Prompts the user to input the board size and validates it.

initializeGameBoard(int size): Sets up the initial board with starting pieces.

printGameBoard(int[][] board): Displays the current state of the board.

playReversi(int[][] board, Scanner scanner): Manages the game flow, including turn alternation and move validation.

isValidMove(int[][] board, int row, int col, int currentPlayer): Validates if a move is legal.

checkInvalidMove(int[][] board, int row, int col, int currentPlayer): Checks if a move is invalid based on Reversi rules.

cannotMove(int[][] board, int currentPlayer): Determines if the current player has no valid moves.

flipPieces(int[][] board, int row, int col, int currentPlayer): Flips opponent pieces after a valid move.

determineWinner(int[][] board): Calculates the final score and declares the winner.

getOpponentPlayer(int currentPlayer): Returns the opponent's player ID.

Techniques Used
2D Arrays: The game board is represented as a 2D array, where each cell stores the state (empty, black, or white).

Input Validation: Ensures that user inputs (board size and moves) are within valid ranges.

Directional Checking: Uses arrays to represent 8 possible directions (up, down, left, right, and diagonals) for move validation and piece flipping.

Looping and Conditionals: Extensive use of loops and conditionals to handle game logic, such as checking for valid moves and flipping pieces.

Modular Design: The code is divided into small, reusable methods for better readability and maintainability.

Scanner Class: Handles user input for board size and moves.

Example Output
Please enter the board size (4 or above and an even number): 4
  0 |  0  0  0  0
  1 |  0  1  2  0
  2 |  0  2  1  0
  3 |  0  0  0  0
    +------------
       0  1  2  3

Please enter the position of '1' (row col):
1 0
  0 |  0  0  0  0
  1 |  1  1  2  0
  2 |  0  1  1  0
  3 |  0  0  0  0
    +------------
       0  1  2  3