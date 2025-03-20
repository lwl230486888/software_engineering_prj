/*
Class: IT114105-1A
Name: WongChakYuen
Student number: 230486888
 */
import java.util.Scanner;

public class Reversi {

    // Constants for cell states
    static final int EMPTY_CELL = 0;
    static final int BLACK_PIECE = 1;
    static final int WHITE_PIECE = 2;

    public static void main(String[] args) {
        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Get the board size from the user
        int boardSize = getBoardSize(scanner);

        // Initialize the game board
        int[][] gameBoard = initializeGameBoard(boardSize);

        // Display the initial game board
        printGameBoard(gameBoard);

        // Start the game
        playReversi(gameBoard, scanner);

        // Close the scanner
        scanner.close();
    }

    // Function to get the board size from the user
    private static int getBoardSize(Scanner scanner) {
        int size;
        while (true) {
            System.out.print("Please enter the board size (4 or above and an even number): ");
            size = scanner.nextInt();

            // Validate user input for board size
            if (size % 2 != 0 || size < 4) {
                System.out.println("Error - input should be 4 or above and an even number.");
            } else {
                break;
            }
        }
        return size;
    }

    // Function to initialize the game board with starting pieces
    private static int[][] initializeGameBoard(int size) {
        int[][] board = new int[size][size];
        int middle = size / 2;

        // Set initial pieces on the board
        board[middle - 1][middle - 1] = BLACK_PIECE;
        board[middle][middle] = BLACK_PIECE;
        board[middle - 1][middle] = WHITE_PIECE;
        board[middle][middle - 1] = WHITE_PIECE;

        return board;
    }

    // Function to display the game board
    private static void printGameBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            System.out.printf("%3d |", row);
            for (int col = 0; col < board[row].length; col++) {
                System.out.printf("%3d", board[row][col]);
            }

            System.out.println();
        }
        System.out.print("    +");
        for (int k = 0; k < board.length; k++) {
            System.out.print("---");
        }
        System.out.println();
        System.out.print("     ");
        for (int b = 0; b < board.length; b++) {
            System.out.printf("%3d", b);
        }

        System.out.println();
    }

    // Function to handle the game flow
    private static void playReversi(int[][] board, Scanner scanner) {
        int currentPlayer = BLACK_PIECE;
        boolean gameFinishes = false;

        while (!gameFinishes) {
            // Check if the current player cannot make a move
            if (cannotMove(board, currentPlayer)) {
                currentPlayer = getOpponentPlayer(currentPlayer);

                // If the opponent also cannot move, the game finishes
                if (cannotMove(board, currentPlayer)) {
                    gameFinishes = true;
                    break;
                } else {
                    System.out.printf("\n '%d' cannot move. Pass to '%d' ", getOpponentPlayer(currentPlayer), currentPlayer);
                }
            }

            // Get the user's move
            System.out.printf("\nPlease enter the position of '%d' (row col): \n", currentPlayer);
            int playerRow = scanner.nextInt();
            int playerCol = scanner.nextInt();

            // Validate and make the move
            if (!isValidMove(board, playerRow, playerCol, currentPlayer)) {
                continue;
            }

            // Place the piece on the board and flip opponent pieces
            board[playerRow][playerCol] = currentPlayer;
            flipPieces(board, playerRow, playerCol, currentPlayer);

            // Display the updated board
            printGameBoard(board);

            // Switch to the opponent's turn
            currentPlayer = getOpponentPlayer(currentPlayer);
        }

        // Game has finished, determine the winner
        if (gameFinishes) {
            determineWinner(board);
        }
    }

    // Function to validate if the move is valid
    private static boolean isValidMove(int[][] board, int row, int col, int currentPlayer) {
        if (row < 0 || row >= board.length || col < 0 || col >= board.length) {
            System.out.print("Error - input numbers should be 0 to " + (board.length - 1) + "!");
            return false;
        }

        if (board[row][col] != EMPTY_CELL) {
            System.out.print("Error - input cell is not empty.");
            return false;
        }

        if (checkInvalidMove(board, row, col, currentPlayer)) {
            System.out.print("Error - Invalid move.");
            return false;
        }

        return true;
    }

    // Function to check if the move is invalid
    private static boolean checkInvalidMove(int[][] board, int row, int col, int currentPlayer) {
        int opponentPlayer = getOpponentPlayer(currentPlayer);
        int[] dirRows = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dirCols = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int dirR = dirRows[i];
            int dirC = dirCols[i];
            int currentRow = row + dirR;
            int currentCol = col + dirC;
            boolean opponentDetected = false;

            // Check in the current direction for opponent pieces
            while (currentRow >= 0 && currentRow < board.length && currentCol >= 0 && currentCol < board.length && board[currentRow][currentCol] == opponentPlayer) {
                opponentDetected = true;
                currentRow += dirR;
                currentCol += dirC;
            }

            // If the move is valid, return false
            if (currentRow >= 0 && currentRow < board.length && currentCol >= 0 && currentCol < board.length && board[currentRow][currentCol] == currentPlayer && opponentDetected) {
                return false;
            }
        }

        // If no valid direction is found, return true (invalid move)
        return true;
    }

    // Function to check if the current player cannot make a move
    private static boolean cannotMove(int[][] board, int currentPlayer) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == EMPTY_CELL && !checkInvalidMove(board, row, col, currentPlayer)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to flip the opponent's pieces after a move
    private static void flipPieces(int[][] board, int row, int col, int currentPlayer) {
        int opponentPlayer = getOpponentPlayer(currentPlayer);
        int[] directionRows = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] directionCols = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int dirR = directionRows[i];
            int dirC = directionCols[i];
            int currentRow = row + dirR;
            int currentCol = col + dirC;
            boolean opponentDetected = false;

            // Find the opponent pieces in the current direction
            while (currentRow >= 0 && currentRow < board.length && currentCol >= 0 && currentCol < board.length && board[currentRow][currentCol] == opponentPlayer) {
                opponentDetected = true;
                currentRow += dirR;
                currentCol += dirC;
            }

            // If opponent pieces are detected, flip them
            if (currentRow >= 0 && currentRow < board.length && currentCol >= 0 && currentCol < board.length && board[currentRow][currentCol] == currentPlayer && opponentDetected) {
                int flipRow = row + dirR;
                int flipCol = col + dirC;

                while (flipRow != currentRow || flipCol != currentCol) {
                    board[flipRow][flipCol] = currentPlayer;
                    flipRow += dirR;
                    flipCol += dirC;
                }
            }
        }
    }

    // Function to determine the winner and display the result
    private static void determineWinner(int[][] board) {
        int blackScore = 0;
        int whiteScore = 0;

        // Count the number of black and white pieces on the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == BLACK_PIECE) {
                    blackScore++;
                } else if (board[row][col] == WHITE_PIECE) {
                    whiteScore++;
                }
            }
        }

        // Display the result of the game
        System.out.println("Game Finishes.");
        System.out.printf("'1' - %d\n", blackScore);
        System.out.printf("'2' - %d\n", whiteScore);

        if (blackScore > whiteScore) {
            System.out.println("Black wins.");
        } else if (whiteScore > blackScore) {
            System.out.println("White wins.");
        } else {
            System.out.println("Draw.");
        }
    }

    // Function to get the opponent player
    private static int getOpponentPlayer(int currentPlayer) {
        return currentPlayer == BLACK_PIECE ? WHITE_PIECE : BLACK_PIECE;
    }
}
