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

    public String gType() {
        return type;
    }

    public String gColor() {
        return color;
    }
}

public class ChessBoard {

    public static String turn = Pieces.White;

    public static class Board {
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

    public static int[] pCords(String nota) {
        if (nota == null || nota.length() !=2) {
            return null;
        }

        char fChar = nota.toLowerCase().charAt(0);
        char rChar = nota.charAt(1);

        int col = -1;
        if (fChar >= 'a' && fChar <= 'h') {
            col = fChar - 'a';
        }

        int row = 0;
        if (rChar >= '1' && rChar <= '8') {
            row = 8 - (rChar - '0');
        }

        if (row == -1 || col == -1) {
            return null;
        }

        int[] cords = {row, col};
        return cords;
    }

    public static void Turn() {
        if (turn.equals(Pieces.White)) {
            turn = Pieces.Black;
        } else {
            turn = Pieces.White;
        }
    }

    public boolean IPCS(int sRow, int sCol, int eRow, int eCol, Board board) {
        if (sRow == eRow) {
            if (sRow < eRow) {
                for (int i = sCol + 1; i < eCol; i++) {
                    if (!board.board[sRow][i].gType().equals(Pieces.Empty)) {
                        return false;
                    }
                }
            }

            if (sRow > eRow) {
                for (int i = eCol +1; i < sCol; i++) {
                    if (!board.board[sRow][i].gType().equals(Pieces.Empty)) {
                        return false;
                    }
                }
            }
        }

        if (sCol == eCol) {
            if (sCol < eCol) {
                for (int i = sCol + 1; i < eCol; i++) {
                    if (!board.board[i][sCol].gType().equals(Pieces.Empty)) {
                        return false;
                    }
                }
            }

            if (sCol > eCol) {
                for (int i = eCol; i < sCol; i++) {
                    if (!board.board[i][sCol].gType().equals(Pieces.Empty)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean IPCD(int sCol, int eCol, int sRow, int eRow, Board board) {
        int howDoICallThisIntRow = (eRow > sRow) ? (eRow - sRow) : (sRow - eRow);
        int howDoICallThisIntCol = (eCol > sCol) ? (eCol - sCol) : (sCol - eCol);

        if (howDoICallThisIntRow != howDoICallThisIntCol) {
            return false;
        }

        int someRow = (eRow > sRow) ? 1 : -1;
        int someCol = (eCol > sCol) ? 1 : -1;

        for (int i = 1; i < howDoICallThisIntRow; i++) {
            if (!board.board[sRow + i * someRow][sCol + i * someCol].gType().equals(Pieces.Empty)) {
                return false;
            }
        }
        return true;
    }

    public boolean IVM(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) {
        if (sRow == eRow && sCol == eCol) {
            System.out.println("Not a valid move!");
            return false;
        }

        Pieces tP = board.board[eRow][eCol];
        if (!tP.gType().equals(Pieces.Empty) && tP.gColor().equals(piece.gColor())) {
            System.out.println("Do you really want to beat up yourself?");
            return false;
        } else {
            return true;
        }
    }

    public static boolean IVPM(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) {
        int fD = (piece.gColor().equals(Pieces.White)) ? -1 : 1;
        int stRow = (piece.gColor().equals(Pieces.White)) ? 6 : 1;

        if (sCol == eCol) {
            if (eRow == sRow + fD && board.board[eRow][eCol].gType().equals(Pieces.Empty)) {
                return true;
            }
            if (stRow == sRow && eRow == sRow + 2 * fD && board.board[eRow][eCol].gType().equals(Pieces.Empty) && board.board[sRow + fD][eCol].gType().equals(Pieces.Empty)) {
                return true;
            }
        } else if ((eCol == sCol + 1 || eCol == sCol - 1) && eRow == sRow + fD) {
            if (!board.board[eRow][eCol].gType().equals(Pieces.Empty) && !board.board[eRow][eCol].gColor().equals(piece.gColor())) {
                return true;
            }
        }
        return false;
    }


    public static void game() {
        Scanner scnr = new Scanner(System.in);
        Board board = new Board();

        while (true) {
            board.prBoard();
            System.out.println("+-------------------------+");
            System.out.println("| Enter your desired turn. |");
            System.out.println("|      Example: g2 f2      |");
            System.out.println("|      Don't type: :q      |");
            System.out.println("|    Or maybe do it :-)    |");
            System.out.println("+-------------------------+");
            System.out.print("[" + (turn.equals(Pieces.White) ? "Player_1" : "Player_2") + "]$ ");
            String move = scnr.nextLine();

            if (move.equals(":q")) {
                System.out.println("You either don't know what vim is or you just don't use it!");
                System.out.println("So you should definitely try vim or neovim!");
                break;
            }

            String[] input = move.split(" ");
            if (input.length != 2) {
                System.out.println("Not a valid move!");
                continue;
            }

            int[] fInput = pCords(input[0]);
            int[] sInput = pCords(input[1]);

            if (fInput == null || sInput == null) {
                System.out.println("Not valid coordinates!");
                continue;
            }

            int sRow = fInput[0];
            int sCol = fInput[1];
            int eRow = sInput[0];
            int eCol = sInput[1];

            Pieces mpiece = board.board[sRow][sCol];
            if (mpiece.gType().equals(Pieces.Empty) || !mpiece.gColor().equals(turn)) {
                System.out.println("Not a valid move or it's just not your turn!");
                continue;
            }

            board.board[eRow][eCol] = mpiece;
            board.board[sRow][sCol] = new Pieces(Pieces.Empty, Pieces.None);

            Turn();
        }

        scnr.close();
    }

    public void black() {

    }

    public void white() {

    }

    public static void main(String[] args) {
        System.out.println("+--------------------------+");
        System.out.println("|   Type \"Start\" to play.  |");
        System.out.println("+--------------------------+");
        Scanner scnr = new Scanner(System.in);
        String inStart = scnr.nextLine();

        if (inStart.equals("Start")) {
            game();
        }
        scnr.close();
    }
}