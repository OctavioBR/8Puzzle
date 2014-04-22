import java.security.InvalidParameterException;
import java.util.*;

public class Board {
    private int size;

	protected int[][] blocks;

    public Board(int[][] blocks) throws InvalidParameterException {
        if(blocks.length != blocks[0].length)
            throw new InvalidParameterException("Board size is wrong");
        this.size = blocks.length;
        this.blocks = blocks;
    }

    public boolean isGoal() {
		if(Arrays.equals(blocks[0], new int[]{1,2,3}) &&
				Arrays.equals(blocks[1], new int[]{4,5,6}) &&
				Arrays.equals(blocks[2], new int[]{7,8,0}))
			return true;
		return false;
	};//Improve?

//TODO:	public boolean isSolvable(){};

	public int[][] getBlocks() {
		int[][] state = new int[getSize()][getSize()];
		for(int i = 0; i < getSize(); i++) {
			for(int j = 0; j < getSize(); j++) {
				state[i][j]= this.blocks[i][j];
			}
		}
		return state;
	}

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board yBoard = (Board) y;
        if (getSize() != yBoard.getSize()) return false;
        boolean res = true;
        for(int i=0; i<this.getSize(); i++) {
            if(this.blocks[i] != yBoard.blocks[i])
                res = false;
        }
        return res;
    }

	public boolean haveNeighbors() {
		return possibleMoves().size() > 0;
	}

	public Map<Board, Moves> neighbors() {
		if(!haveNeighbors())
			return null;
		Map<Board, Moves> neighbors = new HashMap<Board, Moves>();
		List<Moves> moves = possibleMoves();
		try {
			for(Moves m : moves) {
				if(m.name().equals("UP"))
					neighbors.put(moveUp(), Moves.UP);
				if(m.name().equals("DOWN"))
					neighbors.put(moveDown(), Moves.DOWN);
				if(m.name().equals("LEFT"))
					neighbors.put(moveLeft(), Moves.LEFT);
				if(m.name().equals("RIGHT"))
					neighbors.put(moveRight(), Moves.RIGHT);
			}
		} catch(CannotMoveException e) {
			System.err.println(e.getMessage());
		}
		return neighbors;
	}

	public List<Moves> possibleMoves() {
		ArrayList<Moves> moves = new ArrayList<Moves>();
		Block empty = getEmpty();
		if(empty.getX()>0)
			moves.add(Moves.UP);
		if(empty.getX()<getSize()-1)
			moves.add(Moves.DOWN);
		if(empty.getY()>0)
			moves.add(Moves.LEFT);
		if(empty.getY()<getSize()-1)
			moves.add(Moves.RIGHT);
		return moves;
	}

	public Board moveUp() throws CannotMoveException {
		Board nextBoard = new Board(getBlocks());
		Block empty = getEmpty();
		int x = empty.getX();
		int y = empty.getY();
		if(x<1) {
			throw new CannotMoveException("Can't move empty block UP");
		}
		int movedBLock = nextBoard.blocks[x-1][y];
		nextBoard.blocks[x-1][y] = 0;
		nextBoard.blocks[x][y] = movedBLock;
		return nextBoard;
	}

	public Board moveDown() throws CannotMoveException {
		Board nextBoard = new Board(getBlocks());
		Block empty = getEmpty();
		int x = empty.getX();
		int y = empty.getY();
		if(x>getSize()-2) {
			throw new CannotMoveException("Can't move empty block DOWN");
		}
		int movedBLock = nextBoard.blocks[x+1][y];
		nextBoard.blocks[x+1][y] = 0;
		nextBoard.blocks[x][y] = movedBLock;
		return nextBoard;
	}

	public Board moveLeft() throws CannotMoveException {
		Board nextBoard = new Board(getBlocks());
		Block empty = getEmpty();
		int x = empty.getX();
		int y = empty.getY();
		if(y<1) {
			throw new CannotMoveException("Can't move empty block LEFT");
		}
		int movedBLock = nextBoard.blocks[x][y-1];
		nextBoard.blocks[x][y-1] = 0;
		nextBoard.blocks[x][y] = movedBLock;
		return nextBoard;
	}

	public Board moveRight() throws CannotMoveException {
		Board nextBoard = new Board(getBlocks());
		Block empty = getEmpty();
		int x = empty.getX();
		int y = empty.getY();
		if(y>getSize()-2) {
			throw new CannotMoveException("Can't move empty block RIGHT");
		}
		int movedBLock = nextBoard.blocks[x][y+1];
		nextBoard.blocks[x][y+1] = 0;
		nextBoard.blocks[x][y] = movedBLock;
		return nextBoard;
	}

	public Block getEmpty() {
		Block pos = new Block();
		for(int i = 0; i < getSize(); i++) {
			for(int j = 0; j < getSize(); j++) {
				if(this.blocks[i][j]==0) {
					pos.setX(i);
					pos.setY(j);
				}
			}
		}
		return pos;
	}

    public String toString() {
		String s = new String();
		for(int i = 0; i < getSize(); i++) {
			s += (this.blocks[i][0]+" "+
				this.blocks[i][1]+" "+
				this.blocks[i][2])+"\n";
		}
		return s;
	};

    public int getSize() {
        return this.size;
    }


//TODO:    public int hamming() {}		// number of blocks out of place
//TODO:    public int manhattan() {}	// sum of Manhattan distances between blocks and goal
}