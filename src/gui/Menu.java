package gui;

import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;


import sound.GameSound;



public class Menu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6642593115548636318L;
	private int padding = 15;
	private GUI mGui;
	private MyContainer mContainer;
	private JLabel lbbackground;
	private JLabel lbPlayGame;
	private JLabel lbOption;
	private JLabel lbHigthScore;
	private JLabel lbExit;
	private ImageIcon backgroundIcon;
	
	public Menu(MyContainer mContainer){
		this.mContainer = mContainer;
		this.mGui = mContainer.getGui();
		setBackground(Color.YELLOW);
		setLayout(null);
		initComps(mGui);
		initbackground();
	}
	
	public void initComps(GUI mGui){
		lbPlayGame = setLabel((mGui.getWidth()-150)/2-30, (mGui.getHeight()-30)/2-150, "/Images/menu/Play.png");
		lbOption = setLabel(lbPlayGame.getX(),lbPlayGame.getY() + lbPlayGame.getHeight()+padding, "/Images/menu/Option.png");
		lbHigthScore = setLabel(lbOption.getX(),lbOption.getY() + lbOption.getHeight()+padding, "/Images/menu/HightScore.png");
		lbExit = setLabel(lbHigthScore.getX(),lbHigthScore.getY() + lbHigthScore.getHeight()+padding, "/Images/menu/Exit.png");
		
		lbPlayGame.addMouseListener(mMouseAdapter);
		lbOption.addMouseListener(mMouseAdapter);
		lbHigthScore.addMouseListener(mMouseAdapter);
		lbExit.addMouseListener(mMouseAdapter);
		
		add(lbPlayGame);
		add(lbOption);
		add(lbHigthScore);
		add(lbExit);
		
	}
	
	public void initbackground(){
		lbbackground = new JLabel();
		lbbackground.setBounds(0, -10, mGui.getWidth(), mGui.getHeight());
		lbbackground.setBackground(Color.BLACK);
		backgroundIcon = new ImageIcon(getClass().getResource("/Images/menu/background_Menu.png"));
		lbbackground.setIcon(backgroundIcon);
		add(lbbackground);
	}
	
	public JLabel setLabel(int x, int y, String ImageIcon){
		JLabel label = new JLabel();
		ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
		label.setBounds(x, y, Icon.getIconWidth(), Icon.getIconHeight());
		label.setIcon(Icon);
		return label;
	}
	
	private MouseAdapter mMouseAdapter = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==lbPlayGame){
				ImageIcon playIcon = new ImageIcon(getClass().getResource("/Images/menu/Play2.png"));
				lbPlayGame.setIcon(playIcon);
			}
			if(e.getSource()==lbOption){
				ImageIcon optionIcon = new ImageIcon(getClass().getResource("/Images/menu/Option2.png"));
				lbOption.setIcon(optionIcon);
			}
			if(e.getSource()==lbHigthScore){
				ImageIcon hightScoreIcon = new ImageIcon(getClass().getResource("/Images/menu/HightScore2.png"));
				lbHigthScore.setIcon(hightScoreIcon);
			}
			if(e.getSource()==lbExit){
				ImageIcon exitIcon = new ImageIcon(getClass().getResource("/Images/menu/Exit2.png"));
				lbExit.setIcon(exitIcon);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource()==lbPlayGame){
				ImageIcon playIcon = new ImageIcon(getClass().getResource("/Images/menu/Play.png"));
				lbPlayGame.setIcon(playIcon);
			}
			if(e.getSource()==lbOption){
				ImageIcon optionIcon = new ImageIcon(getClass().getResource("/Images/menu/Option.png"));
				lbOption.setIcon(optionIcon);
			}
			if(e.getSource()==lbHigthScore){
				ImageIcon hightScoreIcon = new ImageIcon(getClass().getResource("/Images/menu/HightScore.png"));
				lbHigthScore.setIcon(hightScoreIcon);
			}
			if(e.getSource()==lbExit){
				ImageIcon exitIcon = new ImageIcon(getClass().getResource("/Images/menu/Exit.png"));
				lbExit.setIcon(exitIcon);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==lbExit){
				GameSound.getIstance().getAudio(GameSound.BG_MENU).stop();
				mGui.dispose();
				PlayGame.IS_RUNNING=false;
			}
			if(e.getSource()==lbPlayGame){
				mContainer.setShowPlay();
			}
			if(e.getSource()==lbOption){
				mContainer.setShowOption();
			}
			if(e.getSource()==lbHigthScore){
				mContainer.setShowHightScore();
			}
		}
	};
	
}	
