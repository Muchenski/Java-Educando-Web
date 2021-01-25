package chess;

import boardgame.Board;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
	}

	// Nosso jogo não tem que conhecer as peças genéricas,
	// mas sim deve conhecer as peças do próprio jogo.
	public ChessPiece[][] getPieces() {
		ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				chessPieces[i][j] = (ChessPiece) board.getPiece(i, j);
			}
		}
		return chessPieces;
	}

	public Board getBoard() {
		return board;
	}

}
