package application;

import boardgame.Board;
import boardgame.Piece;
import chess.ChessPiece;
import chess.enums.Color;

// Classe responsável para realizar as modificações
// de exibição da interface na tela.
public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void printBoard(Piece[][] pieces, Board board) {

		int boardRows = board.getRows();
		int boardColumns = board.getColumns();

		for (int i = 0; i < boardRows; i++) {

			System.out.print(boardRows - i + " ");

			for (int j = 0; j < boardColumns; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.print("  ");
		for (int i = 97; i < boardColumns + 97; i++) {
			System.out.print((char) i + " ");
		}
	}

	public static void printPiece(Piece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			if (piece instanceof ChessPiece) {
				if (((ChessPiece) piece).getColor() == Color.WHITE) {
					System.out.print(ANSI_WHITE + piece + ANSI_RESET);
				} else {
					System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
				}
			}
		}
		System.out.print(" ");
	}
}
