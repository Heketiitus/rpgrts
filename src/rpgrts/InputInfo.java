package rpgrts;

public class InputInfo {
	
	public double mouseX;
	public double mouseY;
	public boolean[] click;
	public boolean[] clickheld;
	private boolean[] keysheld;
	private boolean[] keys;
	
	public boolean keysupdated = false;
	
	public int minKey=-1;
	public int maxKey=345;
	
	public void init() {
		click = new boolean[5];
		clickheld = new boolean[5];
		keysheld = new boolean[maxKey-minKey];
		keys = new boolean[maxKey-minKey];
	}
	
	public void tick() {
		click = new boolean[5];
		if(keysupdated)keys = new boolean[maxKey-minKey];
		keysupdated = false;
	}
	
	public void setKey(int keyCode) {

		if(keyCode<maxKey) {
			keys[keyCode-minKey] = true;
			keysheld[keyCode-minKey] = true;
		}else System.out.println("key not recognized " + keyCode);
		keysupdated = true;
	}
	
	public void unsetKey(int keyCode) {
		if(keyCode<maxKey) {
			keysheld[keyCode-minKey] = false;
		}else System.out.println("key not recognized " + keyCode);
		keysupdated = true;
	}
	
	public void setKey(int keyCode,boolean pressed) {
		if(keyCode<maxKey) {
			if(pressed)
			keys[keyCode-minKey] = true;
			keysheld[keyCode-minKey] = pressed;
		}else System.out.println("key not recognized " + keyCode);
		keysupdated = true;
	}
	public void toggleKey(int keyCode) {
		if(keyCode<maxKey) {
			if(!keysheld[keyCode-minKey]) keys[keyCode-minKey] = true;
			keysheld[keyCode-minKey] = !keysheld[keyCode-minKey];
		}else System.out.println("key not recognized " + keyCode);
		keysupdated = true;
	}
	
	public boolean isKeyToggled(int keyCode) {
		return keysheld[keyCode-minKey];
		
	}
	
	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode-minKey];
	}
	
}
