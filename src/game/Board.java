package game;

public class Board {

	private int size;
	private Square[][] squares;

	public Board() { /* Need this for serialization */ }
	
	public Board(int size) {
		this.size = size;
		squares = new Square[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				squares[row][col] = new Square();
			}
		}
	}

	public int getSize() {
		return size;
	}
	
	public Square getSquare(int row, int col) {
		return squares[row][col];
	}
	
	public boolean placeSymbol(Symbol sym, int row, int col) {
		return squares[row][col].SetSymbol(sym);
	}
}
