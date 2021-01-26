package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import boardgame.Board;
import boardgame.Piece;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
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

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String chessPositionData = sc.nextLine();
			char column = chessPositionData.charAt(0);
			int row = Integer.parseInt(chessPositionData.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException(
					"Erro lendo a posição de Xadrez! Linha e/ou coluna inválidos. Tente os valores de a1 até h8!");
		}
	}

	public static void printBoard(Piece[][] pieces, Board board, boolean[][] possibleMoves) {

		int boardRows = board.getRows();
		int boardColumns = board.getColumns();

		for (int i = 0; i < boardRows; i++) {

			System.out.print(boardRows - i + " ");

			for (int j = 0; j < boardColumns; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.print("  ");
		for (int i = 97; i < boardColumns + 97; i++) {
			System.out.print((char) i + " ");
		}
	}

	public static void printBoard(Piece[][] pieces, Board board) {

		int boardRows = board.getRows();
		int boardColumns = board.getColumns();

		for (int i = 0; i < boardRows; i++) {

			System.out.print(boardRows - i + " ");

			for (int j = 0; j < boardColumns; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.print("  ");
		for (int i = 97; i < boardColumns + 97; i++) {
			System.out.print((char) i + " ");
		}
	}

	public static void printPiece(Piece piece, boolean background) {

		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}

		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
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

	public static void printMatch(ChessMatch match) {
		printBoard(match.getPieces(), match.getBoard());
		System.out.println();
		System.out.println("Turno: " + match.getTurn());
		System.out.println("Aguardando jogador atual: " + match.getCurrentPlayer());
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
