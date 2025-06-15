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
        

            board[0][0] = "BR";
            board[0][1] = "BN";
            board[0][2] = "BB";
            board[0][3] = "BQ";
            board[0][4] = "BK";
            board[0][5] = "BB";
            board[0][6] = "BN";
            board[0][7] = "BR";
            for (int i = 0; i < 8; i++) {
                board[1][i] = "BP";
            }
 
            board[1][0] = "WR";
            board[1][1] = "WN";
            board[1][2] = "WB";
            board[1][3] = "WQ";
            board[1][4] = "WK";
            board[1][5] = "WB";
            board[1][6] = "WN";
            board[1][7] = "WR";
            for (int i = 0; i < 8; i++) {
                board[6][i] = "WP";
            }
        }

        public void prBoard() {
            System.out.println("  A   B   C   D   E   F   G   H");
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
        myBoard.prBoard();
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