package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import actor.Actor;
import actor.Bomb;
import actor.BombBang;
import actor.Bomber;
import actor.Box;
import actor.Item;
import actor.Monster;
import hightscore.HightScore;
import sound.GameSound;

public class Manager {
	private ArrayList<Box> arrBox;
	private ArrayList<Box> arrShawDow;
	private ArrayList<Item> arrItem;
	private ArrayList<Bomb> arrBomb;
	private ArrayList<BombBang> arrBombBang;
	private ArrayList<Monster> arrMonster;
	private ArrayList<HightScore> arrHightScore;
	private Bomber mBomber;
	private String Background;
	private int round=1;
	private int nextRound = 0;
	private int status = 0;
	private int indexImgBomd = 0;
	private boolean btn_Menu = false;
	private Random random = new Random();
	
	public Manager() {
		innitManager();
	}
	public void innitManager() {
		switch (round) {
		case 1:
			mBomber = new Bomber(0, 540, Actor.BOMBER, Actor.DOWN, 5, 1, 1);
			innit("src/Map1/BOX.txt", "src/Map1/SHADOW.txt", "src/Map1/MONSTER.txt");
			nextRound = 0;
			status = 0;
			break;
		case 2:
			mBomber = new Bomber(315, 270, Actor.BOMBER, Actor.DOWN, 5, 1, 1, mBomber.getHeart(), mBomber.getScore());
			innit("src/Map2/BOX.txt", "src/Map2/SHADOW.txt","src/Map2/MONSTER.txt");
			nextRound = 0;
			status = 0;
			break;
		case 3:
			mBomber = new Bomber(315, 495, Actor.BOMBER, Actor.DOWN, 5, 1, 1, mBomber.getHeart(), mBomber.getScore());
			innit("src/Map3/BOX.txt", "src/Map3/SHADOW.txt","src/Map3/MONSTER.txt");
			nextRound = 0;
			status = 0;
			break;

		default:
			break;
		}
		
		
	}
	public void innit(String pathBox, String pathShadow, String pathMonster) {
		arrBox = new ArrayList<Box>();
		arrShawDow = new ArrayList<Box>();
		arrItem = new ArrayList<Item>();
		arrBomb = new ArrayList<Bomb>();
		arrBombBang = new ArrayList<BombBang>();
		arrMonster = new ArrayList<Monster>();
		arrHightScore = new ArrayList<HightScore>();
		innitArrBox(pathBox, pathShadow);
		innitArrItem();
		initarrMonster(pathMonster);
		innitArrHightScore("src/hightscore/HightScore.txt");
	}
	private void initarrMonster(String pathMonster) {
		// TODO Auto-generated method stub
		try {
			FileReader file = new FileReader(pathMonster);
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int type = Integer.parseInt(str[2]);
				int orient = Integer.parseInt(str[3]);
				int speed = Integer.parseInt(str[4]);
				int heart = Integer.parseInt(str[5]);
				String images = str[6];
				Monster monster = new Monster(x, y, type, orient, speed, heart,
						images);
				arrMonster.add(monster);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void innitArrItem() {
		int numberBomb = random.nextInt(3) + 4;
		int numberBombsize = random.nextInt(3) + 3;
		int numberShoe = random.nextInt(2) + 3;
		for(int i = 0; i < numberBomb; i++){
			Box b;
			do {
				b = arrBox.get(random.nextInt(arrBox.size()));
			}while(b.getType() == 1);
			Item ite = new Item(b.getX(), b.getY(), 1, "/Images/item/item_bomb.png");
			if(ite.isItemSamePosition(arrItem)) {
				arrItem.add(ite);
			}
			else {
				i = i - 1;
			}
			
		}
		for(int i = 0; i < numberBombsize; i++){
			Box b;
			do {
				b = arrBox.get(random.nextInt(arrBox.size()));
			}while(b.getType() == 1);
			Item ite = new Item(b.getX(), b.getY(), 2, "/Images/item/item_bombsize.png");
			if(ite.isItemSamePosition(arrItem)) {
				arrItem.add(ite);
			}
			else {
				i = i - 1;
			}
			
		}
		for(int i = 0; i < numberShoe; i++){
			Box b;
			do {
				b = arrBox.get(random.nextInt(arrBox.size()));
			}while(b.getType() == 1);
			Item ite = new Item(b.getX(), b.getY(), 3, "/Images/item/item_shoe.png");
			if(ite.isItemSamePosition(arrItem)) {
				arrItem.add(ite);
			}
			else {
				i = i - 1;
			}
			
		}
		
	}
	public void innitArrBox(String pathBox, String pathShadow) {
		// TODO Auto-generated method stub
		try {
			FileReader file = new FileReader(pathBox);
			BufferedReader input = new BufferedReader(file);
			Background = input.readLine();
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int type = Integer.parseInt(str[2]);
				String images = str[3];
				Box box = new Box(x, y, type, images);
				arrBox.add(box);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileReader fileShawDow = new FileReader(pathShadow);
			BufferedReader inputShawDow = new BufferedReader(fileShawDow);
			String line;
			while ((line = inputShawDow.readLine()) != null) {
				String str[] = line.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int type = Integer.parseInt(str[2]);
				String images = str[3];
				Box box = new Box(x, y, type, images);
				arrShawDow.add(box);
			}
			inputShawDow.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void innitBomb() {
		// TODO Auto-generated method stub
		if (mBomber.getStatus() == Bomber.DEAD) {
			return;
		}
		int x = mBomber.getX() + mBomber.getWidth() / 2;
		int y = mBomber.getY() + mBomber.getHeart() / 2;
		for (int i = 0; i < arrBomb.size(); i++) {
			if (arrBomb.get(i).isImpact(x, y)) {
				return;
			}
		}
		if (arrBomb.size() >= mBomber.getQuantityBomb()) {
			return;
		}
		Bomb mBomb = new Bomb(x, y, mBomber.getSizeBomb(), 3000);
		arrBomb.add(mBomb);
		GameSound.getIstance().getAudio(GameSound.BOMB_SET).play();
		
	}
	
	public void innitArrHightScore(String path){
		try {
			FileReader file = new FileReader(path);
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				String name = str[0];
				int score = Integer.parseInt(str[1]);
				HightScore hightScore = new HightScore(name, score);
				arrHightScore.add(hightScore);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void draWBackground(Graphics2D g2d) {
		Image imgBackground = new ImageIcon(getClass().getResource(Background))
				.getImage();
		g2d.drawImage(imgBackground, 0, 0, null);
	}
	public void drawAllBox(Graphics2D g2d) {
		for (int i = 0; i < arrBox.size(); i++) {
			arrBox.get(i).drawBox(g2d);
		}
	}
	
	public void drawAllShawDow(Graphics2D g2d) {
		for (int i = 0; i < arrShawDow.size(); i++) {
			arrShawDow.get(i).drawBox(g2d);
		}
	}
	public void drawAllItem(Graphics2D g2d) {
		for (int i = 0; i < arrItem.size(); i++) {
			arrItem.get(i).drawItem(g2d);
		}
	}
	
	public void drawAllBomb(Graphics2D g2d) {
		for (int i = 0; i < arrBomb.size(); i++) {
			arrBomb.get(i).drawActor(g2d);
		}
		for (int i = 0; i < arrBombBang.size(); i++) {
			arrBombBang.get(i).drawBongBang(g2d);
		}
	}
	
	public void drawAllMonster(Graphics2D g2d) {
		for (int i = 0; i < arrMonster.size(); i++) {
			
			arrMonster.get(i).drawActor(g2d);
		}
	}
	
	
	public void drawInfo(Graphics2D g2d) {
		Image imgInfor = new ImageIcon(getClass().getResource("/Images/map/background_Info.png")).getImage();
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.setColor(Color.RED);	
		g2d.drawImage(imgInfor, 675, 0, null);
		g2d.drawString("HEART", 755, 100);
		Image heart = new ImageIcon(getClass().getResource(
				"/Images/bomber/heart_1.png")).getImage();
		if (mBomber.getHeart() == 3) {
			g2d.drawImage(heart, 750, 120, null);
			g2d.drawImage(heart, 775, 120, null);
			g2d.drawImage(heart, 800, 120, null);
		}
		if (mBomber.getHeart() == 2) {
			g2d.drawImage(heart, 760, 120, null);
			g2d.drawImage(heart, 790, 120, null);
		}
		if (mBomber.getHeart() == 1) {
			g2d.drawImage(heart, 775, 120, null);
		}

		g2d.drawString("SCORE : " + mBomber.getScore(), 740, 200);
	}
	
	public void drawDialog(Graphics2D g2d, int type) {
		g2d.setFont(new Font("Arial", Font.BOLD, 70));
		g2d.setColor(Color.RED);
		if(type==1){
			Image imgLose = new ImageIcon(getClass().getResource("/Images/map/lose.png")).getImage();
			g2d.drawImage(imgLose, 200, 200, null);
		}else{
			if(type==2){
				g2d.drawString("Round "+round, 200, 250);
			}else{
				g2d.drawString("You Win !!!", 200, 250);
			}
		}
	}
	
	public void drawBoss(Graphics2D g2d) {
		for (int i = 0; i < arrMonster.size(); i++) {
			arrMonster.get(i).drawBoss(g2d);
		}
	}
	
	
	public void setRunBomer() {
		if (arrBomb.size() > 0) {
			if (arrBomb.get(arrBomb.size() - 1).setRun(mBomber) == false) {
				mBomber.setRunBomb(Bomber.DISALLOW_RUN);
			}
		}
	}
	public void deadLineAllBomb() {
		for (int i = 0; i < arrBomb.size(); i++) {
			arrBomb.get(i).deadlineBomb();
			if(arrBomb.get(i).getTimeline() % 300 == 0) {
				arrBomb.get(i).setImg(indexImgBomd % 3);
				indexImgBomd++;
			}
			if (arrBomb.get(i).getTimeline() == 250) {
				BombBang bomBang = new BombBang(arrBomb.get(i).getX(), arrBomb
						.get(i).getY(), arrBomb.get(i).getSize(), arrBox);
				GameSound.getIstance().getAudio(GameSound.BOOM_BANG).play();
				arrBombBang.add(bomBang);
				arrBomb.remove(i);
			}
		}
		for(int j=0;j<arrMonster.size();j++){
			for(int i=0;i<arrBomb.size();i++){
				if(arrBomb.get(i).isImpactBombvsActor(arrMonster.get(j))==2){
					BombBang bomBang = new BombBang(arrBomb.get(i).getX(), arrBomb
							.get(i).getY(), arrBomb.get(i).getSize(), arrBox);
					GameSound.getIstance().getAudio(GameSound.BOOM_BANG).play();
					arrBombBang.add(bomBang);
					arrBomb.remove(i);
				}
			}
		}
		for (int i = 0; i < arrBombBang.size(); i++) {
			for (int j = 0; j < arrBomb.size(); j++) {
				if (arrBombBang.get(i).isImpactBombBangvsBomb(arrBomb.get(j))) {
					BombBang bomBang = new BombBang(arrBomb.get(j).getX(),
							arrBomb.get(j).getY(), arrBomb.get(j).getSize(),
							arrBox);
					arrBombBang.add(bomBang);
					arrBomb.remove(j);
				}
			}
		}
		
		
		for (int k = 0; k < arrBombBang.size(); k++) {
			arrBombBang.get(k).deadlineBomb();
			for (int j = 0; j < arrMonster.size(); j++) {
				if (arrBombBang.get(k).isImpactBombBangVsActor(
						arrMonster.get(j))) {
					if(arrMonster.get(j).getHeart()>1){
						arrMonster.get(j).setHeart(arrMonster.get(j).getHeart()-1);
					}
					else{
						if(arrMonster.get(j).getType() == Actor.BOSS) {
							mBomber.setScore(mBomber.getScore() + 200);
							GameSound.getIstance().getAudio(GameSound.MONSTER_DIE).play();
							arrMonster.clear();
						}
						else {
							switch (round) {
							case 1:
								mBomber.setScore(mBomber.getScore() + 10);
								break;
							case 2:
								mBomber.setScore(mBomber.getScore() + 15);
								break;
							case 3:
								mBomber.setScore(mBomber.getScore() + 20);
								break;
							default:
								break;
							}
							
							GameSound.getIstance().getAudio(GameSound.MONSTER_DIE).play();
							arrMonster.remove(j);
						}
						
					}
				}
			}
			if (arrBombBang.get(k).getTimeLine() == 0) {
				arrBombBang.remove(k);
			}
		}
		
		
		
		for (int i = 0; i < arrBombBang.size(); i++) {
			for (int j = 0; j < arrBox.size(); j++) {
				if (arrBombBang.get(i).isImpactBombBangvsBox(arrBox.get(j))) {
					arrBox.remove(j);
					arrShawDow.remove(j);
				}
			}
		}
		for (int i = 0; i < arrBombBang.size(); i++) {
			for (int j = 0; j < arrItem.size(); j++) {
				if (arrBombBang.get(i).isImpactBombBangvsItem(arrItem.get(j))) {
					arrItem.remove(j);
				}
			}
		}
	}
	
	public void checkDead() {
		for (int i = 0; i < arrBombBang.size(); i++) {
			if (arrBombBang.get(i).isImpactBombBangVsActor(mBomber)  && mBomber.getStatus()==Bomber.ALIVE ) {
				
				mBomber.setImgByIndex(0);
				if (mBomber.getStatus() == Bomber.DEAD) {
					return;
				}
				mBomber.setHeart(mBomber.getHeart() - 1);
				mBomber.setStatus(Bomber.DEAD);
				GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
			}
		}
		for (int i = 0; i < arrMonster.size(); i++) {
			if (mBomber.isImpactBomberVsActor(arrMonster.get(i))) {
				mBomber.setImgByIndex(0);
				if (mBomber.getStatus() == Bomber.DEAD) {
					return;
				}
				mBomber.setHeart(mBomber.getHeart() - 1);
				mBomber.setStatus(Bomber.DEAD);
				GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
			}
		}
	}
	
	
	public void checkImpactItem() {
		for (int i = 0; i < arrItem.size(); i++) {
			if (arrItem.get(i).isImpactItemVsBomber(mBomber)) {
				GameSound.instance.getAudio(GameSound.ITEM).play();
				if (arrItem.get(i).getType() == Item.Item_Bomb) {
					mBomber.setQuantityBomb(mBomber.getQuantityBomb() + 1);
					arrItem.remove(i);
					mBomber.setScore(mBomber.getScore() + 5);
					System.out.println("Number Bomb: " + mBomber.getQuantityBomb());
					break;
				}
				if (arrItem.get(i).getType() == Item.Item_BombSize) {
					mBomber.setSizeBomb(mBomber.getSizeBomb() + 1);
					arrItem.remove(i);
					mBomber.setScore(mBomber.getScore() + 5);
					System.out.println("Size Bomb: " + mBomber.getSizeBomb());
					break;
				}
				if (arrItem.get(i).getType() == Item.Item_Shoe) {
					mBomber.setSpeed(mBomber.getSpeed() - 1);
					arrItem.remove(i);
					mBomber.setScore(mBomber.getScore() + 5);
					System.out.println("Speed Bomber: " + mBomber.getSpeed());
					break;
				}
			}
		}
	}
	
	public void checkWinAndLose() {
		if (mBomber.getHeart() == 0 && nextRound == 0) {
			round = 1;
			status = 1;
			nextRound++;
			GameSound.getIstance().stop();
			GameSound.getIstance().getAudio(GameSound.GAME_LOSE).play();
			saveScore();
		}
		if (arrMonster.size() == 0 && nextRound == 0 && btn_Menu == false) {
			if (round == 3) {
				status = 3;
				nextRound++;
				GameSound.getIstance().stop();
				GameSound.getIstance().getAudio(GameSound.GAME_WIN).play();
				saveScore();
				round = 1;
				return;
			}
			mBomber.setSetRun(false);
			GameSound.getIstance().stop();
			GameSound.getIstance().getAudio(GameSound.GAME_WIN).play();
			round = round + 1;
			nextRound++;
			status = 2;
		}
	}
	
	private void saveScore() {
		// TODO Auto-generated method stub
		if(arrHightScore.isEmpty()&& mBomber.getScore() != 0) {
			String name = "";
			try {
				name = JOptionPane.showInputDialog("Please input Your Name");
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(name == null) {
				return;
			}
			HightScore newScore = new HightScore(name, mBomber.getScore());
			arrHightScore.add(newScore);
		}
		else if(arrHightScore.size() < 10 && mBomber.getScore() != 0) {
			String name = JOptionPane.showInputDialog("Please input Your Name");
			if(name.isEmpty()) {
				return;
			}
			HightScore newScore = new HightScore(name, mBomber.getScore());
			arrHightScore.add(newScore);
		}
		else if(mBomber.getScore()>arrHightScore.get(arrHightScore.size()-1).getScore()){
			String name = "";
			try {
				name = JOptionPane.showInputDialog("Please input Your Name");
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(name == null) {
				return;
			}
			HightScore newScore = new HightScore(name, mBomber.getScore());
			arrHightScore.add(newScore);
		}
		Collections.sort(arrHightScore, new Comparator<HightScore>() {

			@Override
			public int compare(HightScore score1, HightScore score2) {
				if(score1.getScore()<score2.getScore()){
					return 1;
				}else{
					if(score1.getScore()==score2.getScore()){
						return 0;
					}else{
						return -1;
					}
				}
			}
		});
		
		if(arrHightScore.size()>10){
			arrHightScore.remove(arrHightScore.size()-1);
		}
		
		try {
			FileOutputStream fileOutput = new FileOutputStream("src/hightscore/HightScore.txt");
			for(int i=0;i<arrHightScore.size();i++){
				String content = arrHightScore.get(i).getName()+":"+arrHightScore.get(i).getScore()+"\n";
				fileOutput.write(content.getBytes());
			}
			fileOutput.close();
		} catch (IOException e ) {
			e.printStackTrace();
		}
	}
	public void changeOrientAll() {
		for (int i = 0; i < arrMonster.size(); i++) {
			int orient = random.nextInt(4) + 1;
			arrMonster.get(i).changeOrient(orient);
		}
	}
	
	public void moveAllMonster(int count) {
		for (int i = 0; i < arrMonster.size(); i++) {
			if (arrMonster.get(i).move(count, arrBomb, arrBox) == false) {
				int orient = random.nextInt(4) + 1;
				arrMonster.get(i).changeOrient(orient);
			}
		}
	}
	
	
	public void setNewBomb() {
		switch (round) {
		case 1:
			mBomber.setNew(0, 540);
			break;
		case 2:
			mBomber.setNew(315, 270);
			break;
		case 3:
			mBomber.setNew(315, 495);
			break;

		default:
			break;
		}
	}
	
	
	public Bomber getmBomber() {
		return mBomber;
	}                                                                                                                                       
	public ArrayList<Box> getArrBox() {
		return arrBox;
	}
	public ArrayList<Bomb> getArrBomb() {
		return arrBomb;
	}
	
	public int getStatus() {
		return status;
	}
	public void setRound(int round) {
		this.round = 1;
	}
	public int getRound() {
		return round;
	}
	public void setBtnMenu(boolean btn) {
		this.btn_Menu = btn;
	}
	
	
}
