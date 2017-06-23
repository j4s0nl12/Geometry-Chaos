package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.tilemap.LevelParser;
import com.mygdx.game.utility.GameConstants;

public class ObjectScrollPane {

    private Table scrollTable;
    private Table t;
    private ScrollPane sp;

    private ShapeRenderer sr;

    private int selectedIdx;

    public ObjectScrollPane(){
        this.selectedIdx = -1;
        this.scrollTable= new Table();
        this.t = new Table();
        this.sr = new ShapeRenderer();

        float width = 200;
        float height = 175f;

        //Adding stuff
        MyImageButton player = new MyImageButton(AssetManager.getPlayerSprite(), width, height){
            @Override
            public void click(){
                selectedIdx = LevelParser.PLAYER;
            }
        };
        MyImageButton dummy = new MyImageButton(AssetManager.getDummySprite(), width, height){
            @Override
            public void click(){
                selectedIdx = LevelParser.DUMMY;
            }
        };
        MyImageButton superdummy = new MyImageButton(AssetManager.getSuperDummySprite(), width, height){
            @Override
            public void click(){
                selectedIdx = LevelParser.SUPERDUMMY;
            }
        };

        this.add(player.getActor());
        this.add(dummy.getActor());
        this.add(superdummy.getActor());

        //Init
        this.start();
    }

    public void draw(SpriteBatch batch){
        this.sp.draw(batch, 1f);
        batch.end();

        this.sr.setProjectionMatrix(GeometryChaos.camera.combined);
        this.sr.begin(ShapeRenderer.ShapeType.Line);
        this.sr.rect(this.sp.getX(), this.sp.getY(), this.sp.getWidth(), this.sp.getHeight());
        this.sr.end();
        batch.begin();
    }

    public void add(Actor a){
        this.scrollTable.add(a);
        this.scrollTable.row();
    }

    public void start(){
        this.sp = new ScrollPane(this.scrollTable, AssetManager.getNeonSkin());
        this.sp.setScrollingDisabled(true, false);
        this.sp.setX(10);
        this.sp.setY(GameConstants.getGameWorldY());
        this.sp.setHeight(GameConstants.getGameWorldHeight()*.8f);
        this.sp.setWidth(GameConstants.getGameWorldX()-this.sp.getX()*2);
        this.t.setFillParent(true);
        this.t.row();
        this.t.add(this.sp).fill().expand();
    }

    public Actor getScrollPane(){
        return this.t;
    }

    public int getSelectedIdx(){
        return this.selectedIdx;
    }
}
