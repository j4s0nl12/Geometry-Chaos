package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.main.GeometryChaos;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GeometryChaos";
		//System.setProperty("org.lwjgl.opengl.Window.undecorated","true");
		//config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		//config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.width = 1600;
		config.height = 900;
		config.resizable = false;
		config.fullscreen = false;
		new LwjglApplication(new GeometryChaos(), config);
	}
}
