package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			UI.printBoard(chessMatch.getPieces(), chessMatch.getBoard());
			System.out.println();
			System.out.print("Origem: ");
			ChessPosition origem = UI.readChessPosition(sc);
			System.out.println();
			System.out.print("Destino: ");
			ChessPosition destino = UI.readChessPosition(sc);
			ChessPiece capturedPiece = chessMatch.performChessMove(origem, destino);
		}
	}

}
