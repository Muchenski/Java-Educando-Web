package boardgame;

import boardgame.exceptions.BoardException;

public class Board {

	private int rows;
	private int columns;
	// O tabuleiro deve ter uma matriz de peças.
	// Esta mesma matriz deve ter as medidas do número
	// de linhas x colunas do próprio tabuleiro.
	private Piece[][] pieces;

	public Board() {

	}

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro criando tabuleiro! É necessário, no mínimo, uma linha e uma coluna.");
		}
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não existe!");
		}
		return getPiece(position) != null;
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && column >= 0 && row < rows && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Já existe uma peça nesta posição!");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.setPosition(position);
	}

	public Piece getPiece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posição não existe!");
		}
		return pieces[row][column];
	}

	public Piece getPiece(Position position) {
		return getPiece(position.getRow(), position.getColumn());
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

}
