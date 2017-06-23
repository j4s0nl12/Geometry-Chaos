package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.utility.Utility;

public class MyTextButton {

    private TextButton button;

    public MyTextButton(String text){
        this.init(text, 40, 0, 0, 100, 75);
    }

    public MyTextButton(String text, int size, float x, float y, float width, float height){
        this.init(text, size, x, y, width, height);
    }

    public void init(String text, int size, float x, float y, float width, float height){
        Skin skin = AssetManager.getNeonSkin();
        TextButtonStyle style = new TextButtonStyle();
        style.font = AssetManager.generateFont(size);
        style.fontColor = new Color(1,1,1,1);
        style.up = skin.getDrawable("button-c");
        style.down = skin.getDrawable("button-pressed-c");
        style.over = skin.getDrawable("button-over-c");
        this.button = new TextButton(text, style);
        this.button.setOrigin(x, y);
        this.button.setPosition(x - width/2, y - height/2);
        this.button.setWidth(width);
        this.button.setHeight(height);
        this.button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                click();
            }
        });
    }

    public void draw(SpriteBatch batch){
        this.draw(batch, 1f);
    }

    public void draw(SpriteBatch batch, float alpha){
        this.button.draw(batch, alpha);
    }

    public void click(){
        Utility.print("MyTextButton['" + this.button.getText() + "']", "TODO: Override 'click()' method.");
    }

    public Actor getActor(){
        return this.button;
    }

    public Color getColor(){
        return this.button.getColor();
    }

    public boolean getIsDisabled(){
        return this.button.isDisabled();
    }

    public boolean getIsPressed(){
        return this.button.isPressed();
    }

    public Vector2 getPos(){
        return new Vector2(this.getX(), this.getY());
    }

    public Vector2 getPosCenter(){
        return this.getPos().cpy().add(this.getWidth()/2, this.getHeight()/2);
    }

    public float getHeight(){
        return this.button.getHeight();
    }

    public float getWidth(){
        return this.button.getWidth();
    }

    public float getX(){
        return this.button.getX();
    }

    public float getY(){
        return this.button.getY();
    }

    public void setColor(Color c){
        this.button.setColor(c);
    }

    public void setColor(float r, float g, float b, float a){
        this.button.setColor(r,g,b,a);
    }

    public void setDisabled(boolean isDisabled){
        this.button.setDisabled(isDisabled);
    }

    public void setPosition(float x, float y){
        this.button.setPosition(x, y);
    }

    public void setTextSize(int size){
        Color tmp = this.button.getStyle().fontColor;
        this.button.getStyle().font = AssetManager.generateFont(size);
        this.button.getStyle().fontColor = tmp;
    }

    public void setText(String text){
        this.button.setText(text);
    }

    public void setX(float x){
        this.button.setX(x);
    }

    public void setY(float y){
        this.button.setY(y);
    }
}
