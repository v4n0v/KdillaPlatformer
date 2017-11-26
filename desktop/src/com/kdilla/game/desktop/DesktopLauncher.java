package com.kdilla.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kdilla.game.KdillaPlatformer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 16f / 9f;
		config.height = 800;
		config.width = (int)(config.height * aspect);
		new LwjglApplication(new KdillaPlatformer(), config);
	}
}
