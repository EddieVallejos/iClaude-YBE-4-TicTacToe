import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class World implements ActionListener{
	private static JFrame frame;
	private static Container cont;
	private static	JButton start;
	
	public static void main(String[] args) {
		frame = new JFrame();
		cont = frame.getContentPane();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String p1 = null;
		String p2 = null;
		int round =0;

		frame.add(startMenu(p1,p2,round, start), BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);


	
	}

	@Override
	public void actionPerformed(ActionEvent e){

		if((JButton)e.getSource() == start){
			frame.setVisible(false);
			frame.dispose();
			Board board = new Board(5, "Cid", "Andie");
			while(!board.isLastRound()){
				try{							//fixes threading error
					Thread.sleep(10);
				}catch(Exception ex){}
				board.checkValue();
			}
		}
	}


	private static JPanel startMenu(String p1,String p2, int round, JButton start){
		JPanel menu = new JPanel();
		JPanel input = new JPanel();
		JPanel navigation = new JPanel();
		
		start = new JButton("START");
		
		JLabel pl1 = new JLabel("Player 1: ");
		JLabel pl2 = new JLabel("Player 2: ");
		JLabel turns = new JLabel("Best of: ");
		
		JTextField name1 = new JTextField("Enter your name here..");
		JTextField name2 = new JTextField("Enter your name here..");
		JTextField rounds = new JTextField();

		//start.addActionListener();
		menu.setLayout(new GridLayout(2,1,0,100));
		menu.setOpaque(false);

		menu.add(pl1);
		menu.add(name1);
		menu.add(pl2);
		menu.add(name2);
		menu.add(turns);
		menu.add(rounds);
		menu.add(start);

		p1 = name1.getText();
		p2 = name2.getText(); 
		//String a = rounds.getText();
		//round = Integer.parseInt(a);
		
		return menu;
	}

}
