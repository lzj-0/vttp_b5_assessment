package vttp.batch5.sdf.task02;

public class Main {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("No TTT configuration file input");
			System.exit(-1);
		}

		String filename = args[0];

		TicTacToe ttt = new TicTacToe(filename);

		System.out.println(ttt.printBoard());
		System.out.println("----------------------");
		System.out.println(ttt.getUtility());
		
	}
}
