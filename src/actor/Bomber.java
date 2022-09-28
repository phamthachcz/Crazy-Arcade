package actor;



import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bomber extends Actor{
	private int delay_draw = 0;
	public static int ALLOW_RUN=0;
	public static int DISALLOW_RUN=1;
	protected int sizeBomb,quantityBomb,status,score,heart;
	private boolean setRun = true;
	
	private int imageIndex=0;
	private int imageIndex2=0;
	private boolean isPlayerRun = false;
	
	
	
	public final Image[] IMAGE_RUN_ANIMATION={
            new ImageIcon(getClass().getResource("/Images/bomber/effect/run_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/run_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/run_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/run_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/run_5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/run_6.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/run_7.png")).getImage(),
    };
	
	public final Image[] IMAGES_BOMB_ANIMATION={
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_11.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_12.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_13.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_14.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_15.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_16.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_17.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/effect/animation_18.png")).getImage(),
    };
	
	public final Image[] IMAGES_DEAD = {
			new ImageIcon(getClass().getResource("/Images/bomber/bomber_die1.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/bomber_die3.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/bomber_die2.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/bomber_die4.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/bomber_die5.png")).getImage(),
            new ImageIcon(getClass().getResource("/Images/bomber/bomber_die6.png")).getImage(),
	};
	
	
	public Bomber(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.runBomb=DISALLOW_RUN;
		this.orient = orient;
		this.speed = speed;
		this.sizeBomb = sizebomb;
		this.quantityBomb = quantityBomb;
		this.heart = 3;
		this.score=0;
		this.status = Actor.ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/bomber/bomber_down.png")).getImage();
		this.img_bm = IMAGE_RUN_ANIMATION[0];
		width = img.getWidth(null);
		height = img.getHeight(null)-20;
	}
	public Bomber(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb, int heart, int score) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.runBomb=DISALLOW_RUN;
		this.orient = orient;
		this.speed = speed;
		this.sizeBomb = sizebomb;
		this.quantityBomb = quantityBomb;
		this.heart = heart;
		this.score= score;
		this.status = Actor.ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/bomber/bomber_down.png")).getImage();
		this.img_bm = IMAGE_RUN_ANIMATION[0];
		width = img.getWidth(null);
		height = img.getHeight(null)-20;
	}
	
	
	
	public void setImg(Image img) {
		this.img = img;
	}
	
	public void setImgByIndex(int index) {
		this.img = IMAGES_DEAD[index];
	}
	public int getStatus() {
		return status;
	}
	public int getSizeBomb() {
		return sizeBomb;
	}

	public int getType() {
		return type;
	}
	
	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}
	public int getQuantityBomb() {
		return quantityBomb;
	}
	public void setQuantityBomb(int quantityBomb) {
		if(quantityBomb>8){
			return;
		}
		this.quantityBomb = quantityBomb;
	}
	public void setSizeBomb(int sizeBomb) {
		if(sizeBomb>7){
			return;
		}
		this.sizeBomb = sizeBomb;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public void drawActor(Graphics2D g2d) {
		// TODO Auto-generated method stub
		super.drawActor(g2d);
		if(getStatus() == 0 || isSetRun() == false) {
			return;
		}
		
		if(getSpeed() < 5) {
			switch (orient) {
			case LEFT:
				if(isPlayerRun == true) {
					imageIndex++;
					img_bm = IMAGE_RUN_ANIMATION[imageIndex/7% IMAGE_RUN_ANIMATION.length]; 
					g2d.drawImage(img_bm, x + 35, y, 45,45,null);
				}
				break;
			case RIGHT:
				if(isPlayerRun == true) {
					imageIndex++;
					img_bm = IMAGE_RUN_ANIMATION[imageIndex/7% IMAGE_RUN_ANIMATION.length];
					g2d.drawImage(img_bm, x-35, y, 45,45,null);
				}
				break;
			case UP:
				if(isPlayerRun == true) {
					imageIndex++;
					img_bm = IMAGE_RUN_ANIMATION[imageIndex/7% IMAGE_RUN_ANIMATION.length];
					g2d.drawImage(img_bm, x, y + 25, 45,45,null);
				}
				break;
			case DOWN:
				if(isPlayerRun == true) {
					imageIndex++;
					img_bm = IMAGE_RUN_ANIMATION[imageIndex/7% IMAGE_RUN_ANIMATION.length];
					g2d.drawImage(img_bm, x, y -50, 45,45,null);
				}
				break;
			default:
				break;
			}
			
			imageIndex++;
		}
		isPlayerRun = false;
		if(delay_draw % 20 == 0) {
			imageIndex2++;
		}
		g2d.drawImage(IMAGES_BOMB_ANIMATION[imageIndex2/7%IMAGES_BOMB_ANIMATION.length],x-10,y-32,65,65,null);
		delay_draw++;
	}
	
	
	public void setNew(int x,int y) {
		this.x = x;
		this.y = y;
		this.status = ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/bomber/bomber_down.png")).getImage();
	}
	
	public boolean isSetRun() {
		return setRun;
	}
	public void setSetRun(boolean setRun) {
		this.setRun = setRun;
	}
	
	@Override
	public boolean move(int count, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox) {
		// TODO Auto-generated method stub
		if(getStatus() == 0 || isSetRun() == false) {
			return false;
		}
		return super.move(count,arrBomb, arrBox);
	}
	@Override
	public void changeOrient(int orient) {
		// TODO Auto-generated method stub
		super.changeOrient(orient);
		if(getStatus() == 0 || isSetRun() == false) {
			return;
		}
		isPlayerRun = true;	
		switch (orient) {
		case LEFT:
			img = new ImageIcon(getClass().getResource("/Images/bomber/bomber_left.png")).getImage();
			break;
		case RIGHT:
			img = new ImageIcon(getClass().getResource("/Images/bomber/bomber_right.png")).getImage();
			break;
		case UP:
			img = new ImageIcon(getClass().getResource("/Images/bomber/bomber_up.png")).getImage();
			break;
		case DOWN:
			img = new ImageIcon(getClass().getResource("/Images/bomber/bomber_down.png")).getImage();
			break;
		default:
			break;
		}
		
	}
	public boolean isImpactBomberVsActor(Actor actor){
		if(status==DEAD){
			return false;
		}
		Rectangle rec1 = new Rectangle(x, y, width, height);
		Rectangle rec2 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
		return rec1.intersects(rec2);
	}
	
	
}
