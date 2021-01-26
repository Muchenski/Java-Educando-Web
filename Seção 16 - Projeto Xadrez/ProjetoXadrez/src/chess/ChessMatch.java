package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;
import chess.exceptions.ChessException;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;

	private List<ChessPiece> piecesOnTheBoard = new ArrayList<ChessPiece>();
	private List<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();

	public ChessMatch() {
		turn = 1;
		currentPlayer = Color.WHITE;
		board = new Board(8, 8);
		initialSetup();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toMatrixPosition();
		Position target = targetPosition.toMatrixPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		ChessPiece capturedPiece = makeMove(source, target);
		nextTurn();
		return capturedPiece;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toMatrixPosition();
		validateSourcePosition(position);
		return board.getPiece(position).possibleMoves();
	}

	private ChessPiece makeMove(Position source, Position target) {
		Piece piece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(piece, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add((ChessPiece) capturedPiece);
		}
		return (ChessPiece) capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem!");
		}
		if (!board.getPiece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não existem movimentos possíveis para esta peça!");
		}
		if (currentPlayer != ((ChessPiece) board.getPiece(position)).getColor()) {
			throw new ChessException("Você não pode mover peças adversárias!");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.getPiece(source).possibleMove(target)) {
			throw new ChessException("A peça escolhida não pode se mover para a posição de destino!");
		}
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
		piecesOnTheBoard.add(piece);
	}

	private void initialSetup() {

		placePiece(new ChessPosition('c', 1), new Rook(board, Color.WHITE));
		placePiece(new ChessPosition('c', 2), new Rook(board, Color.WHITE));
		placePiece(new ChessPosition('d', 2), new Rook(board, Color.WHITE));
		placePiece(new ChessPosition('e', 2), new Rook(board, Color.WHITE));
		placePiece(new ChessPosition('e', 1), new Rook(board, Color.WHITE));
		placePiece(new ChessPosition('d', 1), new King(board, Color.WHITE));

		placePiece(new ChessPosition('c', 7), new Rook(board, Color.BLACK));
		placePiece(new ChessPosition('c', 8), new Rook(board, Color.BLACK));
		placePiece(new ChessPosition('d', 7), new Rook(board, Color.BLACK));
		placePiece(new ChessPosition('e', 7), new Rook(board, Color.BLACK));
		placePiece(new ChessPosition('e', 8), new Rook(board, Color.BLACK));
		placePiece(new ChessPosition('d', 8), new King(board, Color.BLACK));
	}

	public Board getBoard() {
		return board;
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	private void nextTurn() {
		turn++;
		if (currentPlayer == Color.WHITE) {
			currentPlayer = Color.BLACK;
		} else {
			currentPlayer = Color.WHITE;
		}
	}

}
