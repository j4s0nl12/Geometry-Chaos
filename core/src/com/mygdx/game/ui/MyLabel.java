package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.utility.GameConstants;

public class MyLabel{

    private String text;

    private Label label;
    private LabelStyle style;

    public MyLabel(String text, int size){
        this.init(text, size, 0, 0);
    }

    public MyLabel(String text, int size, float x, float y){
        this.init(text, size, x, y);
    }

    private void init(String text, int size, float x, float y){
        this.text = text;

        this.style = new LabelStyle(AssetManager.generateFont(size), GameConstants.getDefaultThemeColor());
        this.label = new Label(this.text, this.style);
        this.label.setPosition(x, y, Align.center);
    }

    public void draw(SpriteBatch batch){
        this.draw(batch, 1f);
    }

    public void draw(SpriteBatch batch, float alpha){
        this.label.draw(batch, alpha);
    }

    public Actor getActor(){
        return this.label;
    }

    public Color getColor(){
        return this.label.getColor();
    }

    public Vector2 getPos(){
        return new Vector2(this.getX(), this.getY());
    }

    public Vector2 getPosCenter(){
        return this.getPos().cpy().add(new Vector2(this.getWidth()/2, this.getHeight()/2));
    }

    public float getHeight(){
        return this.label.getHeight();
    }

    public float getWidth(){
        return this.label.getWidth();
    }

    public float getX(){
        return this.label.getX();
    }

    public float getY(){
        return this.label.getY();
    }

    public void setColor(Color c){
        this.label.setColor(c);
    }

    public void setColor(float r, float g, float b, float a){
        this.label.setColor(r,g,b,a);
    }

    public void setPosition(float x, float y){
        this.label.setPosition(x,y);
    }

    public void setSize(int size){
        this.style = new LabelStyle(AssetManager.generateFont(size), this.getColor());
        this.label.setStyle(this.style);
    }

    public void setText(String text){
        this.label.setText(text);
    }

    public void setX(float x){
        this.label.setX(x);
    }

    public void setY(float y){
        this.label.setY(y);
    }
}
