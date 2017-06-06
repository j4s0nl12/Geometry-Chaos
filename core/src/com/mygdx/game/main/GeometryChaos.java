package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.utility.Camera;

public class GeometryChaos extends Game {

	private static int GAME_WORLD_WIDTH = 1600;
	private static int GAME_WORLD_HEIGHT = 900;

	private static Camera camera;
	private static ScreenManager sm;
	private static SpriteBatch batch;

	@Override
	public void create() {
		camera = new Camera(this.getWidth(), this.getHeight());
		sm = new ScreenManager(this);
		batch = new SpriteBatch();
	}

	public void update(){
		camera.update();
		batch.setProjectionMatrix(camera.combined());
	}

	public void resize(int width, int height){
		camera.resize(width, height);
	}

	public void dispose(){
		this.batch.dispose();
	}

	public ScreenManager getSM(){
		return sm;
	}

	public Camera getCamera(){
		return camera;
	}

	public SpriteBatch getBatch(){
		return batch;
	}

	public static int getWidth(){
		return GAME_WORLD_WIDTH;
	}

	public static int getHeight(){
		return GAME_WORLD_HEIGHT;
	}
}
