package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.ui.FreeText;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseScreen extends InputAdapter implements Screen{

    final GeometryChaos game;
    final String TAG = this.getClass().getSimpleName();
    final static int STATE_RUN = 0;
    final static int STATE_PAUSED = 1;

    private int state;

    public InputMultiplexer iM;
    public Stage stage;

    private static FreeText time;
    private static int hour;
    private static int min;

    private ShapeRenderer sr;

    private boolean debug = false;

    private Color bgColor;

    public BaseScreen(final GeometryChaos gam){
        this.game = gam;
        this.state = STATE_RUN;
        this.bgColor = new Color(.05f,.05f,.05f,1);
        this.iM = new InputMultiplexer();
        this.stage = new Stage(game.viewport);
        this.time = new FreeText(GameConstants.getVirtualWidth()*24/25, GameConstants.getVirtualHeight()*49/50f,
                                 40, "");

        this.sr = new ShapeRenderer();
        this.hour = 0;
        this.min = 0;
    }

    @Override
    public void show() {
        this.iM.addProcessor(this.stage);
        this.iM.addProcessor(this);
        Gdx.input.setInputProcessor(this.iM);
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        Gdx.graphics.setTitle("[FPS:" + Gdx.graphics.getFramesPerSecond() + "] Geometry Chaos");

        Gdx.gl.glClearColor(this.bgColor.r,this.bgColor.g,this.bgColor.b,this.bgColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(this.compareState(STATE_RUN)) {
            this.stage.act(delta);
        }

        game.batch.setProjectionMatrix(game.camera.combined);

        this.sr.setProjectionMatrix(game.camera.combined);
        this.sr.begin(ShapeRenderer.ShapeType.Line);
        this.sr.setColor(1,1,1,1);
        this.sr.rect(GameConstants.getGameWorldX(), GameConstants.getGameWorldY(),
                     GameConstants.getGameWorldWidth(), GameConstants.getGameWorldHeight());
        this.sr.end();
    }

    public void displayTime(SpriteBatch batch){
        long t = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(t);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mma");
        String curTime = sdf.format(cal.getTime());
        if(this.hour != cal.get(Calendar.HOUR) || this.min != cal.get(Calendar.MINUTE)){
            this.time.setText(curTime);
            this.hour = cal.get(Calendar.HOUR);
            this.min = cal.get(Calendar.MINUTE);
        }
        this.time.draw(batch);
    }

    public void setBgColor(Color c){
        this.bgColor = c;
    }

    public void setBgColor(float r, float g, float b, float a){
        this.setBgColor(new Color(r,g,b,a));
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void pause() {
        this.setState(STATE_PAUSED);
    }

    @Override
    public void resume() {
        this.setState(STATE_RUN);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        this.time.dispose();
    }

    public void setState(int state){
        this.state = state;
    }

    public boolean compareState(int state){
        if(this.state == state){
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        if(Utility.debug && this.debug){
            Vector2 tmp = Utility.getUnprojectAt(screenX,screenY,0);
            Gdx.app.log(this.TAG, "TouchDown at (" + tmp.x + "," + tmp.y + ").");
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        if(Utility.debug && this.debug){
            Vector2 tmp = Utility.getUnprojectAt(screenX, screenY, 0);
            Gdx.app.log(this.TAG, "TouchDragged at (" + tmp.x + "," + tmp.y + ").");
        }
        return true;
    }
}
