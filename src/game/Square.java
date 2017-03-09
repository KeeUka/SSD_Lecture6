package game;

public class Square {

	private Symbol symbol;

	public Square() { /* Need this for serialization */ }
	
	public boolean SetSymbol(Symbol symbol) {
		if (this.symbol != null) {
			return false;
		}
		this.symbol = symbol;
		return true;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public String toString() {
		if (symbol == null) {
			return "_";
		}
		return symbol.getValue();
	}

}
