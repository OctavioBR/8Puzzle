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
		if(this.algorithm == Algorithms.A_STAR) {
			AStarFinder solver = new AStarFinder();
			return solver.solve(this.input);
		}
		return null;
	}
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
				expand(currentPath.tail());
			if(!this.frontier.isEmpty())
				this.currentPath = this.frontier.remove();
			solved = currentPath.goalNodeInside();
		}

		return this.currentPath;
	}

	public void expand(Board node) {
		Map<Board, Moves> neighbors = node.neighbors();
		Set<Board> nextNodes = neighbors.keySet();
		boolean newNodeToPath;
		for(Board b : nextNodes) {
			Path valid = this.currentPath.clone();
			Moves move = neighbors.get(b);
			newNodeToPath = valid.add(b, move);
			if(newNodeToPath)
				this.frontier.add(valid);
		}
	}
}

class AStarFinder {
	private LinkedList<Path> frontier;
	private Path currentPath;

	public AStarFinder() {
		this.frontier = new LinkedList<Path>();
		this.currentPath = new Path();
	}

	public Path solve(Board node) {
		this.currentPath.add(node);
		boolean solved = this.currentPath.goalNodeInside();

		while(!solved) {
			if(currentPath.tail().haveNeighbors())
				expand(currentPath.tail());
			if(!this.frontier.isEmpty())
				this.currentPath = this.frontier.remove();
			solved = currentPath.goalNodeInside();
		}

		return this.currentPath;
	};

	public void expand(Board node) {
		Map<Board, Moves> neighbors = node.neighbors();
		Set<Board> nextNodes = neighbors.keySet();
		boolean newNodeToPath;
		for(Board b : nextNodes) {
			Path valid = this.currentPath.clone();
			Moves move = neighbors.get(b);
			newNodeToPath = valid.add(b, move);
			if(newNodeToPath)
				this.frontier.add(valid);
		}
		Collections.sort(this.frontier);
	}
}
