import java.util.LinkedList;

public class Path {
	private LinkedList<Moves> moveSequence;
	private LinkedList<Board> nodes;
	private int cost;

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

	public boolean add(Board board, Moves move) {
		if(nodes.contains(board))
			return false; //avoid looping to same node inside path
		this.nodes.add(board);
		this.cost += 1;
		if(move != null)
			moveSequence.add(move);
		return true;
	}

	public boolean add(Board board) {
		boolean sucess = add(board, null);
		if(this.nodes.size() == 1)
			this.cost -= 1;	//no cost for the first node of path
		return sucess;
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
		LinkedList<Moves> moves = new LinkedList<Moves>();
		for(Moves m : this.moveSequence)
			moves.add(m);
		LinkedList<Board> nodes = new LinkedList<Board>();
		for(Board b : this.nodes)
			nodes.add(b);
		return new Path(moves, getCost(), nodes);
	}

	public String toString() {
		String s = "Path:\n";
		for(Board b : this.nodes) {
			s += b.toString() + "\n";
		}
		return s;
	}

	public int getCost() {return cost;}
}