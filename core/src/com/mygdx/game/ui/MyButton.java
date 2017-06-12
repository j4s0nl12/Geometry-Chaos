package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.managers.AssetManager;

public class MyButton {

    private Button button;
    private FreeText text;

    public MyButton(float x, float y, float width, float height, String text){
        this.init(x,y,width,height,(int)(width*height/1000),text);
    }

    public MyButton(float x, float y, float width, float height, int textSize, String text){
        this.init(x,y,width,height,textSize,text);
    }

    private void init(float x, float y, float width, float height, int textSize, String text){
        this.button = new Button(AssetManager.getNeonSkin());
        this.button.setOrigin(x,y);
        this.button.setSize(width, height);
        this.button.setPosition(x - width/2, y - height/2);
        this.text = new FreeText(x, y, textSize, text);
        this.text.setColor(1,1,1,1);
    }

    public void draw(SpriteBatch batch){
        this.button.draw(batch, 1f);
        this.text.draw(batch);
    }

    public boolean isPressed(){
        return this.button.isPressed();
    }

    public boolean isChecked(){
        return this.button.isChecked();
    }

    public void clicked(){
        this.setChecked(false);
    }

    public void setChecked(boolean checked){
        this.button.setChecked(checked);
    }

    public Button getButton(){
        return this.button;
    }

    public void setDisabled(boolean disabled){
        this.button.setDisabled(disabled);
    }

    public boolean isDisabled(){
        return this.button.isDisabled();
    }

    public void set(float x, float y){
        this.button.setOrigin(x,y);
        this.button.setPosition(x - this.button.getWidth()/2, y - this.button.getHeight()/2);
    }

    public void setX(float x){
        this.set(x, this.button.getOriginY());
    }

    public void setY(float y){
        this.set(this.button.getOriginX(), y);
    }
}
