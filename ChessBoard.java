package Chess; //Das Packet des Schachspiels

import java.util.Scanner; //importieren des Scanners

class Pieces { //die Klasse für alle Schachfiguren und ihre Farben
    //Figuren
    public static final String King = "King";
    public static final String Queen = "Queen";
    public static final String Bishop = "Bishop";
    public static final String Knight = "Knight";
    public static final String Rook = "Rook";
    public static final String Pawn = "Pawn";
    public static final String Empty = "Empty";
    //Farben
    public static final String White = "White";
    public static final String Black = "Black";
    public static final String None = "None";

    String type; //Es git pro Figur einen Type und eine Color
    String color; //Type um zu wissen welche Figur und Color um zu wissen welche Farbe

    public Pieces(String itype, String icolor) { //Weisst den neu erstellten Figuren ihre Werte zu
        type = itype;
        color = icolor;
    }

    public String gPiece() { //Methode um einzelne Felder ohne eine Figur zu printen
        if (type.equals("Empty")) {
            return " "; 
        }

        String initColor = (color.equals(White)) ? "W" : "B"; //Sagt, wenn die Figur weiss ist hat sie den Wert W, wenn nicht hat sie den Wert B für schwarz
        String initType; //neuer String um den Type als einen Buchstaben zu speichern
        switch (type) { //Weisst den verschiedenen Figuren ihren Buchstaben zu
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
        return initColor + initType; //Gibt für jede Figur zwei Buchstaben aus, Farbe und Type
    }

    public String gType() { //Methode um den Type einer Figur zu bekommen
        return type;
    }

    public String gColor() { //Methode um die Farbe einer Figur zu bekommen
        return color;
    }
}

public class ChessBoard { //Die Klasse in der alles definiert wird, was mit der Positionierung und Bewegung von Figuren auf dem Spielbrett zu tun hat

    public static String turn = Pieces.White; //Weiss beginnt in Schach immer somit ist der turn am Anfang bei Weiss

    public static class Board { //Die Klasse die für das Darstellen des Spielbretts selber zuständig ist
        Pieces[][] board = new Pieces[8][8]; //Ein zweidimensionaler Array um das Schachfeld simpel darzustellen

        public Board() { //Methode mit der man das Spielbrett abrufen kann
            for (int i = 0; i < 8; i++) { //Zwei for-loops um das Spielbrett zu erstellen
                for (int j = 0; j < 8; j++) {
                    board[i][j] = new Pieces(Pieces.Empty, Pieces.None); //Jedes Feld ist zuerst leer
                }
            }
        
            //Alle Spielfiguren werden auf dem Spielbrett verteilt
            board[0][0] = new Pieces(Pieces.Rook, Pieces.Black);
            board[0][1] = new Pieces(Pieces.Knight, Pieces.Black);
            board[0][2] = new Pieces(Pieces.Bishop, Pieces.Black);
            board[0][3] = new Pieces(Pieces.Queen, Pieces.Black);
            board[0][4] = new Pieces(Pieces.King, Pieces.Black);
            board[0][5] = new Pieces(Pieces.Bishop, Pieces.Black);
            board[0][6] = new Pieces(Pieces.Knight, Pieces.Black);
            board[0][7] = new Pieces(Pieces.Rook, Pieces.Black);
            for (int i = 0; i < 8; i++) { //For-loop damit nicht jeder Pawn einzeln gesetzt werden muss
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

        public void prBoard() { //Hier die Methode um das Spielfeld schlussendlich zu printen
            System.out.println("  A   B   C   D   E   F   G   H"); //Felder definierung Columns
            System.out.println("+---+---+---+---+---+---+---+---+");
            for (int i = 0; i < 8; i++) { //Zwei for-loops um alle Array-felder zu printen
                System.out.print("|");
                for (int j = 0; j < 8; j++) {
                    String piece = board[i][j].gPiece(); //Alle Felder zuerst leer ausser sie sind im folgenden if-statement vorhanden
                
                    if (piece.length() == 2) { //If statement damit alle Felder mit Figuren weniger Leerschläge haben und somit gleich lang sind wie die anderen
                        System.out.print(" " + piece + "|");
                    } else {
                        System.out.print(" " + piece + " |");
                    }
                }
                System.out.println(" " + (8 - i)); //Felder definierung Rows
                System.out.println("+---+---+---+---+---+---+---+---+");
            }
        }
    }

    public static int[] pCords(String nota) { //Macht eine Methode mit einem Array, der als Output verlangt wird
        if (nota == null || nota.length() !=2) { //Die Notierung den Feldern zB. "B7" muss richtig geschreiben vorhanden sein
            return null;
        }

        char fChar = nota.toLowerCase().charAt(0); //Alle Row-Notationen werden zu kleinbuchstaben umgewandelt
        char rChar = nota.charAt(1);

        int col = -1; //Fals die Notation des Spielers ungültig ist, behält column den Wert -1
        if (fChar >= 'a' && fChar <= 'h') { //Wandelt den Buchstaben der Notation in eine Zahl um, damit man diese Zahl als positionierung im zweidimensionalen Array verwenden kann
            col = fChar - 'a';
        }

        int row = -1; //Fals der Input des Spielers falsch ist behält Row den Wert -1
        if (rChar >= '1' && rChar <= '8') { //Aus dem String wird eine wirkliche Zahl gemacht, da man mit einem String keinen Array-platz definieren kann
            row = 8 - (rChar - '0');
        }

        if (row == -1 || col == -1) { //Fals der Spieler falsche Koordinaten eingegeben hat, werden diese als ungültig erklärt
            return null;
        }

        int[] cords = {row, col}; //Die errechneten Koordinaten werden in einen Array gespeichert und returned
        return cords;
    }

    public static void Turn() { //Nach jedem Zug wird diese Methode abgefragt und der Spieler der am Zug ist gewechselt
        if (turn.equals(Pieces.White)) {
            turn = Pieces.Black;
        } else {
            turn = Pieces.White;
        }
    }

    public static boolean IPCS(int sRow, int sCol, int eRow, int eCol, Board board) { //Diese Methode schaut, dass wenn man eine Figur gerade verschiebt, keine andere Figur dazwischen ist
        if (sRow == eRow) { //horizontal
            if (sCol < eCol) { //nach rechts
                for (int i = sCol + 1; i < eCol; i++) {
                    if (!board.board[sRow][i].gType().equals(Pieces.Empty)) {
                        return false;
                    }
                }
            }

            if (sCol > eCol) { //nach links
                for (int i = eCol +1; i < sCol; i++) {
                    if (!board.board[sRow][i].gType().equals(Pieces.Empty)) {
                        return false;
                    }
                }
            }
        }

        if (sCol == eCol) { //vertikal
            if (sRow < eRow) { //nach oben
                for (int i = sCol + 1; i < eCol; i++) {
                    if (!board.board[i][sCol].gType().equals(Pieces.Empty)) {
                        return false;
                    }
                }
            }

            if (sRow > eRow) { //nach unten
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

    public static boolean IPCD(int sCol, int eCol, int sRow, int eRow, Board board) { //Diese Methode schaut, dass wenn man eine Figur Diagonal verschiebt, keine andere Figur dazwischen ist
        int howDoICallThisIntRow = (eRow > sRow) ? (eRow - sRow) : (sRow - eRow); //Differenz der Rows
        int howDoICallThisIntCol = (eCol > sCol) ? (eCol - sCol) : (sCol - eCol); //Differenz der Columns

        if (howDoICallThisIntRow != howDoICallThisIntCol) { //Wenn die Differenzen nicht gleich sind ist es nicht diagonal
            return false;
        }

        int someRow = (eRow > sRow) ? 1 : -1; //Legt die Richtung fest (nach oben oder nach unten)
        int someCol = (eCol > sCol) ? 1 : -1; //Legt die Richtung fest (nach links oder nach rechts)

        for (int i = 1; i < howDoICallThisIntRow; i++) {
            if (!board.board[sRow + i * someRow][sCol + i * someCol].gType().equals(Pieces.Empty)) { //Prüft die Diagonale ab ob es Figuren darauf hat
                return false;
            }
        }
        return true;
    }

    public static boolean IVMB(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) { //Diese Methode legt grundlegende Bedingung fest
        if (sRow == eRow && sCol == eCol) { //Fals der Spieler als Start- und Endkoordinaten die gleichen Werte eingegeben hat
            System.out.println("Not a valid move!");
            return false;
        }

        Pieces tP = board.board[eRow][eCol]; //Das Zielfeld muss mit einer gegnerischen Figur besetzt sein oder leer. Es kann keine eigene Figur dort sein
        if (!tP.gType().equals(Pieces.Empty) && tP.gColor().equals(piece.gColor())) { //Prüft, fals Figuren auf dem Zielfeld sind, ob diese die eigenen sind oder nicht
            System.out.println("Do you really want to beat up yourself?");
            return false;
        } else {
            return true;
        }
    }

    public static boolean mPawn(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) { //Diese Methode legt die Regeln für den Pawn fest
        int fD = (piece.gColor().equals(Pieces.White)) ? -1 : 1; //Weiss kann Pawn's nur nach oben schieben und scharz nur nach unten
        int stRow = (piece.gColor().equals(Pieces.White)) ? 6 : 1; //Start Row der Pawns (weiss -> 1, schwarz -> 6)

        if (sCol == eCol) { //Schaut ob der Zug gültig ist
            if (eRow == sRow + fD && board.board[eRow][eCol].gType().equals(Pieces.Empty)) {
                return true;
            }
            if (stRow == sRow && eRow == sRow + 2 * fD && board.board[eRow][eCol].gType().equals(Pieces.Empty) && board.board[sRow + fD][eCol].gType().equals(Pieces.Empty)) {
                return true; //Fals ein Pawn noch auf dem Startfeld ist kann er um zwei nach vorne gezogen werden, wenn dort keine andere Figur steht
            }
        } else if ((eCol == sCol + 1 || eCol == sCol - 1) && eRow == sRow + fD) { //Wenn man diagonal jemanden schlagen will
            if (!board.board[eRow][eCol].gType().equals(Pieces.Empty) && !board.board[eRow][eCol].gColor().equals(piece.gColor())) {
                return true;
            }
        }
        return false;
    }

    public static boolean mRook(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) { //Diese Methode legt die Regeln für den Rook fest
        if ((sRow == eRow && sCol != eCol) || (sRow != eRow && sCol == eCol)) { //Nicht diagonal nur gerade
            return IPCS(sRow, sCol, eRow, eCol, board); //Gibt Parameter an IPCS-Methode, um zu schauen, ob alle Felder frei sind
        }
        return false;
    }

    public static boolean mKnight(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) { //Diese Methode legt die Regeln für den Knight fest
        int someRowAgain = (sRow > eRow) ? (sRow - eRow) : (eRow - sRow); //Differenz der Rows
        int someColAgain = (sCol > eCol) ? (sCol - eCol) : (eCol - sCol); //Differenz der Columns

        if ((someRowAgain == 2 && someColAgain == 1) || (someRowAgain == 1 && someColAgain == 2)) { //Knight kann immer nur 2 Felder in eine Richtung und dann 1 Feld senkrecht dazu
            return true;
        }
        return false;
    }

    public static boolean mBishop(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) { //Diese Methode legt die Regeln für den Bishop fest
        int someRowAgain = (sRow > eRow) ? (sRow - eRow) : (eRow - sRow); //Differenz der Rows
        int someColAgain = (sCol > eCol) ? (sCol - eCol) : (eCol - sCol); //Differenz der Columns

        if (someRowAgain == someColAgain) { //Nur diagonal und nicht gerade
            return IPCD(sRow, sCol, eRow, eCol, board); //Gibt die Parameter an IPCD-Methode weiter, um zu schauen, ob alle Felder frei sind
        }
        return false;
    }
    
    public static boolean mQueen(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) { //Diese Methode legt die Regeln für die Queen fest
        return (mRook(sRow, sCol, eRow, eCol, board, piece) || mBishop(sRow, sCol, eRow, eCol, board, piece)); //Die Queen bewegt sich gleich wie der Rook und der Bishop zusammen
    }

    public static boolean mKing(int sRow, int sCol, int eRow, int eCol, Board board, Pieces piece) { //Diese Methode legt die Regeln für den King fest
        int someRowAgain = (sRow > eRow) ? (sRow - eRow) : (eRow - sRow); //Differenz der Rows
        int someColAgain = (sCol > eCol) ? (sCol - eCol) : (eCol - sCol); //Differenz der Columns

        if (someRowAgain <= 1 && someColAgain <= 1) { //King kann sich nur 1 Feld in jede Richtung bewegen
            return true;
        }
        return false;
    }

    public static int[] lKing(String kColor, Board board) { //Diese Methode findet die Position des Kings der angegebenen Farbe heraus
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { 
                Pieces piece = board.board[i][j]; //Scannt alle Felder des Schachbretts
                if (piece.gType().equals(Pieces.King) && piece.gColor().equals(kColor)) { //Wenn der King der entsprechenden Farbe gefunden wird
                    return new int[]{i, j}; //Werden die Koordinaten in einem Array gespeichert und returned
                }
            }
        }
        return null;
    }

    public static boolean ISA(int row, int col, String aColor, Board board) { //Diese Methode überprüft, ob ein bestimmtes Feld von einer gegnerischen Figur angegriffem wird
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieces atk = board.board[i][j]; //Alle Felder werden abgescannt
                if (!atk.gType().equals(Pieces.Empty) && atk.gColor().equals(aColor)) { //Wenn das Feld eine eingene Figur enthält, und es unter attacke steht
                    Pieces oTar = board.board[row][col]; //Speichert die Figur auf dem attackierten Feld für später
                    board.board[row][col] = new Pieces(Pieces.Empty, Pieces.None); //Die Figur auf dem attackierten Feld wird weggenommen, um zu testen ob das Feld für die angreifende Figur theoretisch erreichbar ist

                    boolean cAtk = false; //Boolean, ob ein Feld Attackiert werden kann und nicht
                    switch (atk.gType()) { 
                        case Pieces.Pawn: //Falls die Angreifende Figur des Gegners ein Pawn ist
                            int fD = (atk.gColor().equals(Pieces.White)) ? -1 : 1;
                            if ((Math.abs(col - j)) == 1 && row == i + fD) { //Steht der Pawn diagonal zum Zielfeld? Wir haben herausgefunden das es Math.abs Methode gibt
                                cAtk = true;
                            }
                            break;
                        case Pieces.Rook: //Falls die angreifende Figur des Gegners ein Rook ist
                            cAtk = mRook(i, j, row, col, board, atk);
                            break;
                        case Pieces.Knight: //Falls die angreifende Figur des Gegners ein Knight ist
                            cAtk = mKnight(i, j, row, col, board, atk);
                            break;
                        case Pieces.Bishop: //Falls die angreifende Figur des Gegners ein Bishop ist
                            cAtk = mBishop(i, j, row, col, board, atk);
                            break;
                        case Pieces.Queen: //Falls die angreifende Figur des Gegners ein Queen ist
                            cAtk = mQueen(i, j, row, col, board, atk);
                            break;
                        case Pieces.King: //Falls die angreifende Figur des Gegners ein King ist 
                            boolean someRow = (row == i || row == i + 1 || row == i - 1); //Ist in der selben Row oder ein Feld entfernt
                            boolean someCol = (col == j || col == j + 1 || col == j - 1); //Ist in der selben Column oder ein Fed entfernt
                            cAtk = someRow && someCol; //Column und Row müssen valid sein
                            break;
                    }

                    board.board[row][col] = oTar; //Die vorhin weggenommene Figur wird wieder platziert

                    if (cAtk) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean ICKing(String kColor, Board board) { //Prüft, ob der König im Schach steht
        int[] kingPos = lKing(kColor, board); //Findet die Position des Königs
        if (kingPos == null) {
            return false;
        }

        String opColor = kColor.equals(Pieces.White) ? Pieces.Black : Pieces.White; //Schaut, was die Farbe des Gegners ist
        return ISA(kingPos[0], kingPos[1], opColor, board); //Gibt die Parameter an ISA weiter, um zu schauen ob der König im Schach stehen
    }

    public static boolean IVM(int sRow, int sCol, int eRow, int eCol, Board board) { //Schaut nur ob die Figur gültig ist und nicht, ob es im Schach selber gültig ist. Also ohne Schachregeln
        Pieces piece = board.board[sRow][sCol]; //Definiert Piece

        if (!IVMB(sRow, sCol, eRow, eCol, board, piece)) { //Falls IVMB nicht erfüllt ist automatisch schon falsch
            return false;
        }

        switch (piece.gType()) { //Prüft alle Figuren durch
            case Pieces.Pawn:
                return mPawn(sRow, sCol, eRow, eCol, board, piece);
            case Pieces.Rook:
                return mRook(sRow, sCol, eRow, eCol, board, piece);
            case Pieces.Knight:
                return mKnight(sRow, sCol, eRow, eCol, board, piece);
            case Pieces.Bishop:
                return mBishop(sRow, sCol, eRow, eCol, board, piece);
            case Pieces.Queen:
                return mQueen(sRow, sCol, eRow, eCol, board, piece);
            case Pieces.King:
                return mKing(sRow, sCol, eRow, eCol, board, piece);
            default:
                System.out.println("Not a valid Piece!");
                return false;
        }
    }

    public static void game() { //Der Ablauf des Schachspieles als Methode

        Scanner scnr = new Scanner(System.in); //Neues Scanner-objekt
        Board board = new Board(); //Neues Board-objekt

        while (true) {
            board.prBoard(); //Zeichnet das Spielbrett
            System.out.println("+-------------------------+"); //Die Zug-optionen
            System.out.println("| Enter your desired turn. |");
            System.out.println("|      Example: g2 f2      |");
            System.out.println("|      Don't type: :q      |");
            System.out.println("|    Or maybe do it :-)    |");
            System.out.println("+-------------------------+");
            System.out.print("[" + (turn.equals(Pieces.White) ? "Player_1" : "Player_2") + "]$ "); //Damit nicht steht "Weiss ist an der Reihe" haben wir hinzugefügt das dort ein Benutzername steht
            String move = scnr.nextLine(); //Input des Spielers in move speichert

            if (move.equals(":q")) { //Input um das Spiel zu beenden
                System.out.println("You either don't know what vim is or you just don't use it!");
                System.out.println("So you should definitely try vim or neovim!");
                break;
            }

            String[] input = move.split(" "); //Speichert den Input des Spielers getrennt in einem Array
            if (input.length != 2) { 
                System.out.println("Not a valid move!"); //Falls der Input nicht 2 Koordinaten enthält, wird wiederholt
                continue;
            }

            int[] fInput = pCords(input[0]); //Die Row Koordinate
            int[] sInput = pCords(input[1]); //Die Col Koordinate

            if (fInput == null || sInput == null) {
                System.out.println("Not valid coordinates!"); //Wenn es Koordinaten sind die nicht auf dem Spielbrett sind, wird wiederholt
                continue;
            }

            int sRow = fInput[0]; //Die Row Koordinate vor dem Zug
            int sCol = fInput[1]; //Die Column Koordinate vor dem zug
            int eRow = sInput[0]; //Die Row Koordinate nach dem Zug
            int eCol = sInput[1]; //Die Column Koordinate nach dem Zug

            Pieces mpiece = board.board[sRow][sCol]; //Die Start-Koordinaten des gewünsten Zuges

            if (mpiece.gType().equals(Pieces.Empty)) {
                System.out.println("Not a valid move, there is no piece on that field!"); //Falls es keine Figur auf dem gewünsten Feld hat, wird wiederholt
                continue;
            }

            if (!mpiece.gColor().equals(turn)) { 
                System.out.println("Not your turn, dear Mr. not neovim user."); //Falls der Spieler versucht, die Figuren des Gegners zu bewegen, wird wiederholt
                continue;
            }

            if (IVMB(sRow, sCol, eRow, eCol, board, mpiece)) { //Prüfen, ob der Zug gültig ist
                board.board[eRow][eCol] = mpiece; //Führt den erwünschten Zug aus, wenn er gültig ist
                board.board[sRow][sCol] = new Pieces(Pieces.Empty, Pieces.None); //Entfernt die Figur auf dem ursrpünglichen Feld
                Turn(); //Wechselt den Spieler, der am Zug ist
            } else {
            }
        }

        scnr.close(); //Scanner wird geschlossen
    }

    public static void main(String[] args) { //Main Methode
        System.out.println("+--------------------------+"); //Startmenu
        System.out.println("|   Type \"Start\" to play.  |");
        System.out.println("+--------------------------+");
        System.out.print("[Player_1]$ "); //Username für ersten User
        Scanner scnr = new Scanner(System.in); //Neuer Scanner
        String inStart = scnr.nextLine();

        if (inStart.equals("Start")) { //Der Spieler muss Start schreiben
            game(); //Dann wird Das ganze spiel ausgeführt
        }
        scnr.close(); //Scanner wird geschlossen
    }
}