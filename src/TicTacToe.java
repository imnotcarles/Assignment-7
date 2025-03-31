import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void createBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    public static void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
                || (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    public static boolean makeMove(int row, int col) {
        if (board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        System.out.println("Spot already taken. Try again.");
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        createBoard();
        printBoard();
        while (true) {
            try {
                System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
                int row = keyboard.nextInt();
                int col = keyboard.nextInt();

                if (makeMove(row, col)) {
                    printBoard();
                    if (checkWin()) {
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    }
                    if (checkBoardFull()) {
                        System.out.println("It's a draw!");
                        break;
                    }
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid input. Please enter numbers only.");
                keyboard.nextLine();
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Invalid move. Row and column must be between 0 and 2.");
            }
        }
        keyboard.close();
    }
}