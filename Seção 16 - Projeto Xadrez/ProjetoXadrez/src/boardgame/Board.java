package boardgame;

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
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public Piece getPiece(int row, int column) {
		return pieces[row][column];
	}

	public Piece getPiece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

}
