@SuppressWarnings("unused")
public class World{
	private static StartMenu menu;
	private static Board board;
	private static Checker vCheck, hCheck, dCheck;
	
	public static void main(String[] args) {
		World.newGame();
	}

	public static void createBoard(int rounds, String name1, String name2){
		World.board = new Board(rounds, name1, name2);

		World.dCheck = new Checker(1, World.board);
		World.vCheck = new Checker(2, World.board);
		World.hCheck = new Checker(3, World.board);
		
		World.dCheck.start();
		World.vCheck.start();
		World.hCheck.start();

		board.start();
	}
	public static void newGame(){
		World.menu = new StartMenu();
	}
	
}
