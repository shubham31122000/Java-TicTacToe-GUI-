import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {
	JLabel la=new JLabel(new ImageIcon(getClass().getResource("images/ttt.jpg")));
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/x.jpg"));
	ImageIcon icon2=new ImageIcon(getClass().getResource("images/o.jpg"));
	JPanel pa[]=new JPanel[3];
	JLabel msg=new JLabel("First Player Turn...");
	JButton bt[]=new JButton[9];
	JButton reset=new JButton("RESET");
	int user=1, count=0;
	boolean winnerFound=false;
	
	public TicTacToe() {
		super("Tic Tac Toe");
		setSize(700,800);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		setResizable(false);
		add(la);
		addPanels();
		setVisible(true);
	}
	
	private void addPanels() {
		la.setLayout(null);
		for(int i=0;i<3;i++) {
			pa[i]=new JPanel();
			la.add(pa[i]);
		}
		pa[0].setBounds(140,70,400,50);
		pa[1].setBounds(120,170,450,450);
		pa[2].setBounds(150,650,400,80);
		addInfo();
	}
	
	private void addInfo() {
		pa[0].add(msg);
		msg.setFont(new Font("elephant",0,25));
		msg.setForeground(Color.blue);
		pa[2].add(reset);
		pa[2].setOpaque(false);
		reset.setFont(new Font("elephant",0,20));
		reset.addActionListener(new ResetListener());
		addButtons();
	}
	
	private void addButtons() {
		pa[1].setLayout(new GridLayout(3,3));
		TicListener listener=new TicListener();
		
		for(int i=0;i<9;i++) {
			bt[i]=new JButton();
			bt[i].setBackground(Color.yellow);
			bt[i].addActionListener(listener);
			pa[1].add(bt[i]);
		}
	}
	
	class TicListener implements ActionListener {

		public void actionPerformed(ActionEvent evt) {
			
			JButton source=(JButton)evt.getSource();
			Icon icon=source.getIcon();
			if(icon!=null || winnerFound) {
				return;
			}
 			if(user==1) {   //or we can do, create one user variable of int type and assign it to 1 in if condition check for user==1 then it enters if block and set user=2 and else block set user=1
				source.setIcon(icon1);
				msg.setText("Second Player Turn...");
				user=2;
				findWinner(icon1);
			}
			else {
				source.setIcon(icon2);
				msg.setText("First Player Turn...");
				user=1;
				findWinner(icon2);
			}
			count++;
			if(count==9 && winnerFound==false) {
				msg.setText("It's ended in a draw..");
				msg.setForeground(Color.red);
			}
				
		}
		
		private void findWinner(ImageIcon icon) {
			if(bt[0].getIcon()==icon && bt[1].getIcon()==icon && bt[2].getIcon()==icon)
				announceWinner(0,1,2,icon);
			else if(bt[3].getIcon()==icon && bt[4].getIcon()==icon && bt[5].getIcon()==icon)
				announceWinner(3,4,5,icon);
			else if(bt[6].getIcon()==icon && bt[7].getIcon()==icon && bt[8].getIcon()==icon)
				announceWinner(6,7,8,icon);
			else if(bt[0].getIcon()==icon && bt[3].getIcon()==icon && bt[6].getIcon()==icon)
				announceWinner(0,3,6,icon);
			else if(bt[1].getIcon()==icon && bt[4].getIcon()==icon && bt[7].getIcon()==icon)
				announceWinner(1,4,7,icon);
			else if(bt[2].getIcon()==icon && bt[5].getIcon()==icon && bt[8].getIcon()==icon)
				announceWinner(2,5,8,icon);
			else if(bt[0].getIcon()==icon && bt[4].getIcon()==icon && bt[8].getIcon()==icon)
				announceWinner(0,4,8,icon);
			else if(bt[2].getIcon()==icon && bt[4].getIcon()==icon && bt[6].getIcon()==icon)
				announceWinner(2,4,6,icon);
		}
		
		private void announceWinner(int i, int j, int k, ImageIcon ic) {
			if(ic==icon1) {
				msg.setText("First Player is WINNER..");
			}
			else {
				msg.setText("Second Player is WINNER..");
			}
			bt[i].setBackground(Color.red);
			bt[j].setBackground(Color.red);
			bt[k].setBackground(Color.red);
			winnerFound=true;
		}
		
	}
	
	class ResetListener implements ActionListener {

		public void actionPerformed(ActionEvent evt) {
			user=1;
			msg.setText("First Player Turn...");
			for(JButton tt:bt) {
				tt.setIcon(null);
				tt.setBackground(Color.yellow);
			}
			winnerFound=false;
			count=0;
			msg.setForeground(Color.blue);
		}
		
	}
	
	public static void main(String[] args) {
		new TicTacToe();
	}

}
