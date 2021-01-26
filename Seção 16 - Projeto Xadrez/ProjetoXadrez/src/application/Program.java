package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exceptions.ChessException;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch);
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
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
