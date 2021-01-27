package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exceptions.ChessException;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();

		while (!chessMatch.isCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, capturedPieces);
				System.out.println();
				System.out.print("Origem: ");
				ChessPosition origem = UI.readChessPosition(sc);

				boolean[][] possibleMoves = chessMatch.possibleMoves(origem);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), chessMatch.getBoard(), possibleMoves);

				System.out.println();
				System.out.print("Destino: ");
				ChessPosition destino = UI.readChessPosition(sc);
				ChessPiece capturedPiece = chessMatch.performChessMove(origem, destino);

				if (capturedPiece != null) {
					capturedPieces.add(capturedPiece);
				}
				if (chessMatch.getPromoted() != null) {
					String type = null;
					do {
						System.out.print("Digite a peça para promoção [Q/R/B/N]: ");
						type = sc.nextLine().toUpperCase();
					} while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q"));

					chessMatch.replacePromotedPiece(type);
				}
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, capturedPieces);
	}

}
