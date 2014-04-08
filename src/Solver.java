import java.util.List;
import java.util.Queue;

public class Solver {
	private Algorithms algorithm;
	private Board currentBoard;

	public Solver(Board initial, Algorithms algorithm) {
		this.algorithm = algorithm;
		currentBoard = initial;
	};
//    public int moves(){} // min number of moves to solve initial board
//    public List<Board> solution() {}// sequence of boards in a shortest solution
}

class BreadthsFirstFinder {
	private Queue<Path> paths;
	private Path currentPath;
	private List<Board> frontier;

	public void solve(Board node) {
		this.currentPath.add(node);
		this.frontier = node.neighbors();
	}
}

class Path {
	private List<Moves> moveSequence;
	private int cost;
	private List<Board> nodes;

	public void add(Board board, Moves move) {
		this.nodes.add(board);
		this.cost += 1;
		if(move != null)
			moveSequence.add(move);
	}

	public void add(Board board) {
		add(board, null);
	}
}