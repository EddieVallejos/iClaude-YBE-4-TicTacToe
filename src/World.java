public class World{
	private static StartMenu menu;
	private static Board board;
	private static Checker vCheck, hCheck, dCheck;
	
	public static void main(String[] args) {
		World.newGame();
	}

	/*Instantiates a new board*/
	public static void createBoard(int rounds, String name1, String name2){
		World.board = new Board(rounds, name1, name2);

		
		World.dCheck = new Checker(1, World.board);
		World.vCheck = new Checker(2, World.board);
		World.hCheck = new Checker(3, World.board);
		
		while(true){ //catches exception for null resource
			try{
				World.dCheck.start();
				World.vCheck.start();
				World.hCheck.start();
			}catch(Exception e){e.printStackTrace();continue;}
			break;
		}
		/*
		try{
			World.dCheck.join();
			World.vCheck.join();
			World.hCheck.join();
		}catch(Exception e){}
		*/
		board.start();
	}
	
	/*Starts new game*/
	public static void newGame(){
		World.menu = new StartMenu();
	}
	
}
