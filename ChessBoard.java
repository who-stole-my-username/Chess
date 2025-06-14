package Chess;

import java.util.Scanner;

public class ChessBoard {

    public class Board {
        String[][] board = new String[8][8];

        public Board() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j] = " ";
                }
            }
        }

        public void printBoard() {
            System.out.println("+---+---+---+---+---+---+---+---+");
            for (int i = 0; i < 8; i++) {
                System.out.print("|");
                for (int j = 0; j < 8; j++) {
                    System.out.print(" " + board[i][j] + " |");
                }
                System.out.println();
                System.out.println("+---+---+---+---+---+---+---+---+");
            }
        }
    }

    public static void game() {
        ChessBoard gameInstance = new ChessBoard();
        ChessBoard.Board myBoard = gameInstance.new Board();
        myBoard.printBoard();
    }

    public void black() {

    }

    public void white() {

    }

    public static void main(String[] args) {
        System.out.println("+-------------------------+");
        System.out.println("|   Type \"Start\" to play  |");
        System.out.println("+-------------------------+");
        System.out.print("[Player_1]$ ");
        Scanner scnr = new Scanner(System.in);
        String inStart = scnr.nextLine();

        if (inStart.equals("Start")) {
            game();
        }
        scnr.close();
    }
}