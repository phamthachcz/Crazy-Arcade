package sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class GameSound {
	public static GameSound instance;
	
	public static final String BG_MENU = "bg_menu.wav";
	public static final String GAME_START = "game_start.wav";
	public static final String BG_MAP1 = "bg_map1.wav";
	public static final String BG_MAP2 = "bg_map2.wav";
	public static final String BG_MAP3 = "bg_map3.wav";
	public static final String MONSTER_DIE = "monster_die.wav";
	public static final String BOMB_SET = "bomb_set.wav";
	public static final String ITEM = "item.wav";
	public static final String BOOM_BANG = "boom_bang.wav";
	public static final String BOMBER_DIE = "bomber_die.wav";
	public static final String GAME_LOSE = "game_lose.wav";
	public static final String GAME_WIN = "game_win.wav";
	private HashMap<String, AudioClip> audioMap;
	public GameSound() {
		audioMap = new HashMap<>();
		loadAllAudio();
	}
	
	
	public static GameSound getIstance() {
		if (instance == null) {
			instance = new GameSound();
		}

		return instance;
	}
	
	public void loadAllAudio() {
		putAudio(BG_MENU);
		putAudio(GAME_START);
		putAudio(BG_MAP1);
		putAudio(BG_MAP2);
		putAudio(BG_MAP3);
		putAudio(MONSTER_DIE);
		putAudio(BOMB_SET);
		putAudio(ITEM);
		putAudio(BOOM_BANG);
		putAudio(BOMBER_DIE);
		putAudio(GAME_WIN);
		putAudio(GAME_LOSE);
	}
	
	public void stop() {
		getAudio(BG_MENU).stop();
		getAudio(GAME_START).stop();
		getAudio(BG_MAP1).stop();
		getAudio(BG_MAP2).stop();
		getAudio(BG_MAP3).stop();
		getAudio(MONSTER_DIE).stop();
		getAudio(BOMB_SET).stop();
		getAudio(ITEM).stop();
		getAudio(BOOM_BANG).stop();
		getAudio(GAME_WIN).stop();
		getAudio(GAME_LOSE).stop();
	}
	
	public void putAudio(String name) {
		AudioClip auClip = Applet.newAudioClip(GameSound.class.getResource(name));
		audioMap.put(name, auClip);
	}
	public AudioClip getAudio(String name) {
		return audioMap.get(name);
	}
	
	
}
