import java.util.*;

public class Solver {
	private Algorithms algorithm;
	private Board input;

	public Solver(Board input, Algorithms algorithm) {
		this.algorithm = algorithm;
		this.input = input;
	};

	public Path solve() {
		if(this.algorithm==Algorithms.BREADTHS_FIRST) {
			BreadthsFirstFinder solver = new BreadthsFirstFinder();
			return solver.solve(this.input);
		}
		return null;
	}
//    public int moves(){} // min number of moves to solve initial board
//    public List<Board> solution() {}// sequence of boards in a shortest solution
}

class BreadthsFirstFinder {
	private LinkedList<Path> frontier;
	private Path currentPath;

	public BreadthsFirstFinder() {
		this.frontier = new LinkedList<Path>();
		this.currentPath = new Path();
	}

	public Path solve(Board node) {

		this.currentPath.add(node);
		boolean solved = this.currentPath.goalNodeInside();

		while(!solved) {
			if(currentPath.tail().haveNeighbors())
				expand(node);
			if(!this.frontier.isEmpty())
				this.currentPath = this.frontier.remove();
			solved = currentPath.goalNodeInside();
		}
		return this.currentPath;
	}

	public void expand(Board node) {
		Map<Board, Moves> neighbors = node.neighbors();
		Set<Board> nextNodes = neighbors.keySet();
		for(Board b : nextNodes) {
			Path valid = this.currentPath.clone();
			Moves move = neighbors.get(b);
			valid.add(b, move);
			this.frontier.add(valid);
		}
	}
}

class Path {
	private LinkedList<Moves> moveSequence;
	private int cost;
	private LinkedList<Board> nodes;

	Path(LinkedList<Moves> moveSequence, int cost, LinkedList<Board> nodes) {
		this.moveSequence = moveSequence;
		this.cost = cost;
		this.nodes = nodes;
	}

	Path() {
		this.moveSequence = new LinkedList<Moves>();
		this.cost = 0;
		this.nodes = new LinkedList<Board>();
	}

	public void add(Board board, Moves move) {
		this.nodes.add(board);
		this.cost += 1;
		if(move != null)
			moveSequence.add(move);
	}

	public void add(Board board) {
		add(board, null);
		if(this.nodes.size() == 1)
			this.cost -= 1;	//no cost for the first node of path
	}

	public boolean goalNodeInside() {
		Board endNode = this.nodes.peekLast();
		if(endNode != null)
			return endNode.isGoal();
		return false;
	}

	public Board tail() {
		return nodes.peekLast();
	}

	public Path clone() {
		return new Path(this.moveSequence, this.cost, this.nodes);
	}

	public String toString() {
		String s = "Path:\n";
		for(Board b : this.nodes) {
			s += b.toString() + "\n";
		}
		return s;
	}
}