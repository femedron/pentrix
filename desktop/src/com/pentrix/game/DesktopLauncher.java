package com.pentrix.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.pentrix.game.parameters.GameParameters;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(GameParameters.width, GameParameters.height);
		config.setTitle("Pentrix");
		new Lwjgl3Application(new Pentrix(), config);
	}
}
