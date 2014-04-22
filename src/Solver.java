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
			if(currentPath.tail().haveNeighbors()) {
				System.out.println("expanding node: "+currentPath.tail());
				expand(currentPath.tail());
			}
			if(!this.frontier.isEmpty())
				this.currentPath = this.frontier.remove();
			solved = currentPath.goalNodeInside();
		}
		System.out.println("PUZZLE SOLVED!!!");
		return this.currentPath;
	}

	public void expand(Board node) {
		Map<Board, Moves> neighbors = node.neighbors();
		Set<Board> nextNodes = neighbors.keySet();
		for(Board b : nextNodes) {
			Path valid = this.currentPath.clone();
			Moves move = neighbors.get(b);
			valid.add(b, move);
			System.out.println("Added to frontier:"+valid);
			this.frontier.add(valid);
		}
	}
}
