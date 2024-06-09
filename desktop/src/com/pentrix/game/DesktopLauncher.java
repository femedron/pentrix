package com.pentrix.game;

import com.badlogic.cubocy.Cubocy;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.pentrix.game.Drop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		int w = 1200;
		int h = 900;
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(w,h);
		config.setTitle("pentrix");
		new Lwjgl3Application(new Drop(w,h), config);
	}
}
