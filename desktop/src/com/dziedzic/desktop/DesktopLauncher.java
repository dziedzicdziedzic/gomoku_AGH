package com.dziedzic.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dziedzic.view.AGH_gomoku;

public class DesktopLauncher {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String gameTitle = "gomoku";

	public static void main(String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = gameTitle;
		config.width = WIDTH;
		config.height = HEIGHT;
		config.resizable = false;
		new LwjglApplication(new AGH_gomoku(), config);

	}
}
