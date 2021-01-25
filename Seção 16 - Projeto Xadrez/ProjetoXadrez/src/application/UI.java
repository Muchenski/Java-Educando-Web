package application;

import boardgame.Board;
import boardgame.Piece;

// Classe responsável para realizar as modificações
// de exibição da interface na tela.
public class UI {

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
			System.out.print("- ");
		} else {
			System.out.print(piece + " ");
		}
	}
}
