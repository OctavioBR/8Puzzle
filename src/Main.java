import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Board a = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
		Board b = new Board(new int[][]{{1,2,3},{4,5,6},{7,0,8}});
		Board c = new Board(new int[][]{{1,2,3},{4,5,6},{0,7,8}});
		Board cc = new Board(new int[][]{{1,2,3},{4,5,6},{0,7,8}});
		Board d = new Board(new int[][]{{1,2,3},{4,5,0},{7,8,6}});
		Board e = new Board(new int[][]{{1,2,3},{4,0,5},{7,8,6}});

		Solver ai = new Solver(c, Algorithms.BREADTHS_FIRST);
		Path result = ai.solve();
		System.out.println(result);
	}
}
