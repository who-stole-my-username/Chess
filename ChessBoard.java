package Chess;

public class ChessBoard{

    public static void main(String[] args) {

    }

    public class Board {
        private String[][] board;
        public Board() {
            board = new String[8][8];
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    board[i][j] = " "; 
                }
            }
        }

        public void setPiece(int row, int col, String pR) {
            if(row < 8 && row >= 0 && col < 8 && col >= 0) {
                board[row][col] = pR;
            } 
        }
    }

    class Piece {
        Piece King = new Piece();
        Piece Queen = new Piece();
        Piece Rook = new Piece();
        Piece Horse = new Piece();
        Piece Bishop = new Piece();
        Piece Pawn = new Piece();
    }
}