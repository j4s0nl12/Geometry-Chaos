package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.managers.PrefManager;

public class CirclePad {

    private Touchpad tp;

    private InputEvent fakeInputEvent;

    public CirclePad(float x, float y, float size){
        this.tp = new Touchpad(10f, AssetManager.getNeonSkin());
        this.tp.setOrigin(x,y);
        this.tp.setSize(size,size);
        this.tp.setPosition(x - this.tp.getWidth()/2, y - this.tp.getHeight()/2);
        this.fakeInputEvent = new InputEvent();
        this.fakeInputEvent.setType(Type.touchDown);
        if(PrefManager.getBoolean(PrefManager.hideCpads_str) && !PrefManager.getBoolean(PrefManager.lockCpads_str)) {
            this.setPosition(100000, 100000);
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
