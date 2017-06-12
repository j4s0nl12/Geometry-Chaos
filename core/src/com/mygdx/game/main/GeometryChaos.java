package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.utility.GameConstants;

public class GeometryChaos extends Game {

	public static OrthographicCamera camera;
	public static Viewport viewport;
	public static SpriteBatch batch;
	private static ScreenManager sm;
	public static PrefManager pm;
	public static AssetManager am;

	@Override
	public void create() {
		am = new AssetManager();
		pm = new PrefManager();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(GameConstants.getVirtualWidth(), GameConstants.getVirtualHeight(), camera);
		sm = new ScreenManager(this);
		sm.goToScreen(sm.MAINMENUSCREEN);
	}

	public void resize(int width, int height){
		viewport.update(width, height, true);
	}

	@Override
	public void dispose(){
		batch.dispose();
		am.dispose();
	}

	public void goToScreen(int screenIdx){
		sm.goToScreen(screenIdx);
	}

	public void newGame(){
		sm.newGame();
	}

	public void goBack(){
		sm.goBack();
	}
}
