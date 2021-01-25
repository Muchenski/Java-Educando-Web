package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
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

	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}

	public Board getBoard() {
		return board;
	}

}
