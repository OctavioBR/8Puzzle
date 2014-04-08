public class Main {
	public static void main(String[] args) {
		Board b = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
		Solver ai = new Solver(b, Algorithms.BREADTHS_FIRST);
		Path result = ai.solve();
		System.out.println(result);
	}
}
