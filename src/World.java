
public class World {

	public static void main(String[] args) {
		Board board = new Board(5, "Cid", "Andie");
		while(!board.isLastRound()){
			try{							//fixes threading error
				Thread.sleep(10);
			}catch(Exception e){}
			board.checkValue();
		}
	}

}
