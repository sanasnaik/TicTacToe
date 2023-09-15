//Author: Sana Naik
import java.util.Scanner;

public class TicTacToe {

    public static void printBoard(char[][] board) { //initial empty board.
        for (int i = 0; i < 3; i++) {
            System.out.println();
            for (int j = 0; j < 3; j++) {
                System.out.print("|-" + board[i][j] + "-|");
            }
        }
    }

    public static void play(char[][] board, int box, boolean[] filledPos, char xo) { //used each time the player or cpu moves.
        filledPos[box] = true;
        switch(box) { //idea inspired from YT: Alex Lee
            case 1:
                board[0][0] = xo;
                break;
            case 2:
                board[0][1] = xo;
                break;
            case 3:
                board[0][2] = xo;
                break;
            case 4:
                board[1][0] = xo;
                break;
            case 5:
                board[1][1] = xo;
                break;
            case 6:
                board[1][2] = xo;
                break;
            case 7:
                board[2][0] = xo;
                break;
            case 8:
                board[2][1] = xo;
                break;
            case 9:
                board[2][2] = xo;
                break;
        }
        printBoard(board);
    }

    public static boolean gameOver(char[][] board) { //checks if board is completely full.
        boolean gameOver = false;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != '\u0000') {
                    count++;
                }
            }
        }
        if (count == 9)
            gameOver = true;
        return gameOver;
    }

    public static boolean isWinner(char[][] board, char xo) { //checks for 3 in a row.
        if(board[0][0] == xo) {
            if (board[0][1] == xo) {
                if (board[0][2] == xo) {
                    return true; //top row
                }
            }
            else if (board[1][0] == xo) {
                if (board[2][0] == xo) {
                    return true; //leftmost col
                }
            }
            else if (board[1][1] == xo) {
                if (board[2][2] == xo) {
                    return true; //diagonal
                }
            }
        }
        else if(board[1][0] == xo) {
            if (board[1][1] == xo) {
                if (board[1][2] == xo) {
                    return true; //mid row
                }
            }
        }
        else if(board[2][0] == xo) {
            if (board[2][1] == xo) {
                if (board[2][2] == xo) {
                    return true; //last row
                }
            }
        }

        else if (board[0][1] == xo) {
            if (board[1][1] == xo) {
                if (board [2][1] == xo) {
                    return true; //mid col
                }
            }
        }

        else if (board[0][2] == xo) {
            if (board[1][2] == xo) {
                if (board [2][2] == xo) {
                    return true; //right col
                }
            }
            else if (board[1][1] == xo) {
                if (board[2][0] == xo) {
                    return true; //diagonal
                }
            }
        }
        return false;
    }

    public static void responseMove(char[][] board, boolean[] filledPos) { //the computer plays a move.
        int rand = (int)Math.floor(Math.random() * 9) + 1;
        if (!filledPos[rand]) {
            play(board, rand, filledPos, 'O');
        }
        else {
            responseMove(board, filledPos);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Want to play tic-tac-toe? (Y/N)");

        if (kb.nextLine().equals("Y")) {
            char[][] board = new char[3][3];
            boolean[] pos = new boolean[10];

            printBoard(board);
            System.out.println("Choose a number to place your X.");
            while(!gameOver(board)&&!isWinner(board, 'X')&&!isWinner(board, 'O')) {
                int box = kb.nextInt();
                if (pos[box]) {
                    System.out.println("Pick an unfilled box.");
                }
                else {
                    play(board, box, pos, 'X');
                    responseMove(board, pos);
                }
            }
            if (gameOver(board)) {
                System.out.println("It's a tie.");
            }
            if (isWinner(board, 'X')) {
                System.out.println("You win!");
            }
            if (isWinner(board, 'O')) {
                System.out.println("I win!");
            }
        } 
        kb.close();
    }
}