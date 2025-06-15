package Chess;

import java.util.Scanner;

class Pieces {
    public static final String King = "King";
    public static final String Queen = "Queen";
    public static final String Bishop = "Bishop";
    public static final String Knight = "Knight";
    public static final String Rook = "Rook";
    public static final String Pawn = "Pawn";
    public static final String Empty = "Empty";

    public static final String White = "White";
    public static final String Black = "Black";
    public static final String None = "None";

    String type;
    String color;

    public Pieces(String itype, String icolor) {
        type = itype;
        color = icolor;
    }

    public String getPiece() {
        if (type.equals("Empty")) {
            return " ";
        }

        String initColor = (color.equals(White)) ? "W" : "B";
        String initType;
        switch (type) {
            case King:
                initType = "K";
                break;
            case Queen:
                initType = "Q";
                break;
            case Bishop:
                initType = "B";
                break;
            case Knight:
                initType = "N";
                break;
            case Rook:
                initType = "R";
                break;
            case Pawn:
                initType = "P";
                break;
            default:
                initType = " ";
                break;
        }
        return initColor + initType;
    }
}

public class ChessBoard {

    public String curColor = Pieces.White;

    public class Board {
        Pieces[][] board = new Pieces[8][8];

        public Board() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j] = new Pieces(Pieces.Empty, Pieces.None);
                }
            }
        

            board[0][0] = new Pieces(Pieces.Rook, Pieces.Black);
            board[0][1] = new Pieces(Pieces.Knight, Pieces.Black);
            board[0][2] = new Pieces(Pieces.Bishop, Pieces.Black);
            board[0][3] = new Pieces(Pieces.Queen, Pieces.Black);
            board[0][4] = new Pieces(Pieces.King, Pieces.Black);
            board[0][5] = new Pieces(Pieces.Bishop, Pieces.Black);
            board[0][6] = new Pieces(Pieces.Knight, Pieces.Black);
            board[0][7] = new Pieces(Pieces.Rook, Pieces.Black);
            for (int i = 0; i < 8; i++) {
                board[1][i] = new Pieces(Pieces.Pawn, Pieces.Black);
            }
 
            board[7][0] = new Pieces(Pieces.Rook, Pieces.White);
            board[7][1] = new Pieces(Pieces.Knight, Pieces.White);
            board[7][2] = new Pieces(Pieces.Bishop, Pieces.White);
            board[7][3] = new Pieces(Pieces.Queen, Pieces.White);
            board[7][4] = new Pieces(Pieces.King, Pieces.White);
            board[7][5] = new Pieces(Pieces.Bishop, Pieces.White);
            board[7][6] = new Pieces(Pieces.Knight, Pieces.White);
            board[7][7] = new Pieces(Pieces.Rook, Pieces.White);
            for (int i = 0; i < 8; i++) {
                board[6][i] = new Pieces(Pieces.Pawn, Pieces.White);
            }
        }

        public void prBoard() {
            System.out.println("  A   B   C   D   E   F   G   H");
            System.out.println("+---+---+---+---+---+---+---+---+");
            for (int i = 0; i < 8; i++) {
                System.out.print("|");
                for (int j = 0; j < 8; j++) {
                    String piece = board[i][j].getPiece();
                

                    if (piece.length() == 2) {
                        System.out.print(" " + piece + "|");
                    } else {
                        System.out.print(" " + piece + " |");
                    }
                }
                System.out.println(" " + (8 - i));
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