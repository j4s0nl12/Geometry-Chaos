package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.managers.AssetManager;


public class FreeText {

    private BitmapFont font;
    private GlyphLayout layout;

    private float x;
    private float y;
    private String text;

    public FreeText(float x, float y, int size, String text){
        this.x = x;
        this.y = y;
        this.text = text;

        this.font = AssetManager.generateFont(size);

        this.layout = new GlyphLayout();
        this.layout.setText(this.font, this.text);
        this.setColor(0, 204/256f, 204/256f, 1);
    }

    public void draw(SpriteBatch batch){
        this.font.draw(batch, this.text, this.x - this.layout.width/2, this.y + this.layout.height/2, this.layout.width, Align.center, false);
    }

    public void setText(String text){
        this.text = text;
        this.layout.setText(this.font, this.text);
    }

    public void dispose(){
        this.font.dispose();
    }

    public void setColor(float r, float g, float b, float a){
        this.setColor(new Color(r,g,b,a));
    }

    public void setColor(Color c){
        this.font.setColor(c);
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public float getWidth(){
        return this.layout.width;
    }

    public float getHeight(){
        return this.layout.height;
    }
}
