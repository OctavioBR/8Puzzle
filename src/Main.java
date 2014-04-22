import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Board a = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
		Board b = new Board(new int[][]{{1,2,3},{4,5,6},{7,0,8}});
		Board c = new Board(new int[][]{{1,2,3},{4,5,6},{0,7,8}});
		Board d = new Board(new int[][]{{1,2,3},{4,5,0},{7,8,6}});
		Board e = new Board(new int[][]{{1,2,3},{4,0,5},{7,8,6}});

		Board samp = new Board(new int[][]{
				{4,3,5},
				{7,2,8},
				{6,1,0}
		});
		Board samp2 = new Board(new int[][]{
				{8,6,5},
				{2,0,7},
				{3,4,1}
		});

		Solver ai = new Solver(samp2, Algorithms.A_STAR);
		Path result = ai.solve();
		System.out.println(result);
		System.out.printf("Minimum Steps: %d", result.getCost());
	}
}
