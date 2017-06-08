package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.game.managers.PrefManager;

public class CirclePad {

    private final String DIR = "Skins/neon/skin/";
    private final String JSON = "neon-ui.json";
    private final String ATLAS = "neon-ui.atlas";

    private Touchpad tp;
    private Skin skin;

    private InputEvent fakeInputEvent;

    public CirclePad(float x, float y, float size){
        this.skin = new Skin(Gdx.files.internal(DIR + JSON));
        this.skin.addRegions(new TextureAtlas(DIR + ATLAS));
        this.tp = new Touchpad(10f, this.skin);
        this.tp.setOrigin(x,y);
        this.tp.setSize(size,size);
        this.tp.setPosition(x - this.tp.getWidth()/2, y - this.tp.getHeight()/2);
        this.fakeInputEvent = new InputEvent();
        this.fakeInputEvent.setType(Type.touchDown);
        if(PrefManager.getHideCpads() && !PrefManager.getLockCpads()) {
            this.setVisible(false);
        }
    }

    public void draw(SpriteBatch batch){
        if(this.tp.isVisible())
            this.tp.draw(batch, 1f);
    }

    public Touchpad getTouchpad(){
        return this.tp;
    }

    public boolean isTouched(){
        return this.tp.isTouched();
    }

    public Vector2 getPercents(){
        return new Vector2(this.getPercentX(), this.getPercentY());
    }

    public float getPercentX(){
        return this.tp.getKnobPercentX();
    }

    public float getPercentY(){
        return this.tp.getKnobPercentY();
    }

    public void setPosition(float x, float y){
        this.tp.setOrigin(x,y);
        this.tp.setPosition(x - this.tp.getWidth()/2, y - this.tp.getHeight()/2);
    }

    public void setPosition(Vector2 pos){
        this.setPosition(pos.x, pos.y);
    }

    public void fire(float x, float y, int pointer){
        this.fakeInputEvent.setPointer(pointer);
        this.fakeInputEvent.setStageX(x);
        this.fakeInputEvent.setStageY(y);
        this.tp.fire(this.fakeInputEvent);
    }

    public void fire(Vector2 pos, int pointer){
        this.fire(pos.x,pos.y, pointer);
    }

    public void setVisible(boolean isVisible){
        this.tp.setVisible(isVisible);
    }
}
