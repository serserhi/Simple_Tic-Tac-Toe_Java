package tictactoe;

import java.util.List;
import java.util.Scanner;

public class Main {
    static String[][] tablero = new String [3][3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //String str = scanner.nextLine();

        /*
        for (int i = 0; i < 7; i = i + 3) {
            System.out.println("| " + str.charAt(i) + " " + str.charAt(i + 1) + " " + str.charAt(i + 2) + " |");
        }*/
        //rellenarMatrix(str);
        rellenarMatrix();
        printMatrix();
        String symbol = "X";
        while (evaluatePlay()) {
            String jugada = scanner.nextLine();
            String fila = jugada.split(" ")[0];
            String columna = jugada.split(" ")[1];
            if (procesarCoordenadas(fila, columna, symbol)) {
                printMatrix();
                if (symbol.equals("X")) {
                    symbol = "O";
                } else {
                    symbol = "X";
                }
            }
        }

    }

    public static boolean someWins(String s) {
        return ((tablero[0][0].equals(s) && tablero[0][1].equals(s) && tablero[0][2].equals(s))
                || (tablero[1][0].equals(s) && tablero[1][1].equals(s) && tablero[1][2].equals(s))
                || (tablero[2][0].equals(s) && tablero[2][1].equals(s) && tablero[2][2].equals(s))
                || (tablero[0][0].equals(s) && tablero[1][0].equals(s) && tablero[2][0].equals(s))
                || (tablero[0][1].equals(s) && tablero[1][1].equals(s) && tablero[2][1].equals(s))
                || (tablero[0][2].equals(s) && tablero[1][2].equals(s) && tablero[2][2].equals(s))
                || (tablero[0][0].equals(s) && tablero[1][1].equals(s) && tablero[2][2].equals(s))
                || (tablero[0][2].equals(s) && tablero[1][1].equals(s) && tablero[2][0].equals(s)));
    }

    public static boolean emptyCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].matches("\\s")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean manySymbols() {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].matches("X")) {
                    countX++;
                } else if (tablero[i][j].matches("O")) {
                    countO++;
                }
            }
        }
        return (countX > countO + 1) || (countO > countX + 1);
    }

    public static void rellenarMatrix() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = " ";
            }
        }
    }

    public static void printMatrix() {
        System.out.println("---------");
        System.out.println("| " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2] + " |");
        System.out.println("| " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2] + " |");
        System.out.println("| " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2] + " |");
        System.out.println("---------");
    }

    public static boolean evaluatePlay() {
        if (manySymbols()) {
            System.out.println("Impossible");
            return false;
        } else {
            if (!someWins( "X") && !someWins( "O")) {
                if (emptyCells()) {
                    //System.out.println("Game not finished");
                    return true;
                } else {
                    System.out.println("Draw");
                    return false;
                }
            } else {
                if ((someWins( "X") && someWins( "O"))) {
                    System.out.println("Impossible");
                    return false;
                } else {
                    System.out.println(someWins( "X") ? "X wins" : "O wins");
                    return false;
                }
            }
        }
    }

    public static boolean procesarCoordenadas(String fila, String columna, String symbol) {
        if (!fila.matches("\\d") || !columna.matches("\\d")) {
            System.out.println("You should enter numbers!");
            return false;
        } else {
            int filaN = Integer.parseInt(fila);
            int columnaN = Integer.parseInt(columna);
            if (filaN > 3 || columnaN > 3 || filaN < 1 || columnaN < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            } else {
                if (tablero[filaN - 1][columnaN - 1].equals(" ")) {
                    tablero[filaN - 1][columnaN - 1] = symbol;
                    return true;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    return false;
                }
            }

        }
    }

}
