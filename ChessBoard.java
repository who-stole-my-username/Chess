package Chess;

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
            System.out.println("-----------------");
            for (int i = 0; i < 8; i++) {
                System.out.print("|");
                for (int j = 0; j < 8; j++) {
                    System.out.print(board[i][j] + "|");
                }
                System.out.println();
                System.out.println("-----------------");
            }
        }
    }

    public static void black() {

    }

    public static void white() {

    }

    public class Piece {
        
    }

    public static void main(String[] args) {
        ChessBoard game = new ChessBoard(); 
        ChessBoard.Board myBoard = game.new Board();

        myBoard.printBoard();
    }
}