package chess;

import boardgame.Board;
import chess.enums.Color;
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

	private void placePiece(ChessPosition chessPosition, ChessPiece piece) {
		board.placePiece(piece, chessPosition.toMatrixPosition());
	}

	private void initialSetup() {
		placePiece(new ChessPosition('b', 6), new Rook(board, Color.WHITE));
		placePiece(new ChessPosition('e', 8), new King(board, Color.BLACK));
		placePiece(new ChessPosition('e', 1), new King(board, Color.WHITE));
	}

	public Board getBoard() {
		return board;
	}

}
