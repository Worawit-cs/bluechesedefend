package Main;

public enum GameStates { //เก็บค่าคงที่ที่ใช้อยู่ ในที่นี้คือ ฉาก

	PLAYING, MENU, SETTINGS, EDIT, GAME_OVER;

	public static GameStates gameState = MENU;

	public static void SetGameState(GameStates state) {
		gameState = state;
	}

}