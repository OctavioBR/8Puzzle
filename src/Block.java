public class Block {
	private int value;
	private int x;
	private int y;

	public Block(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
	}

	public Block() {
		this.value = 0;
		this.x = 0;
		this.y = 0;
	}

	public int getValue() {
		return value;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
