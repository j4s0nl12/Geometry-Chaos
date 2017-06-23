package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.utility.GameConstants;

public class MySlider {

    private Slider slider;
    private MyLabel name;
    private MyLabel val;

    private String key;

    public MySlider(float x, float y, String name, String key){
        this.init(x,y, GameConstants.getVirtualWidth()*3/4, 0, 1, .05f, 40, name, key);
    }

    public MySlider(float x, float y, int width, String name, String key){
        this.init(x,y,width,0,1,.05f,(int)(40),name, key);
    }

    public MySlider(float x, float y, int width, int textSize, String name, String key){
        this.init(x,y,width,0,1,.05f,textSize,name, key);
    }

    private void init(float x, float y, int width, float minVal, float maxVal, float tick, int textSize, String name, String k){
        this.key = k;

        this.slider = new Slider(minVal, maxVal, tick, false, AssetManager.getNeonSkin());
        this.slider.setValue(PrefManager.getFloat(this.key));
        this.slider.setWidth(width);
        this.slider.getStyle().knob.setMinWidth(40);
        this.slider.getStyle().knob.setMinHeight(40);
        this.slider.setPosition(x - width/2,y);
        this.slider.setAnimateDuration(.1f);

        this.name = new MyLabel(name, textSize, x - width/2, y + 50);
        this.name.setX(this.name.getX() + this.name.getWidth()/2);

        this.val = new MyLabel(String.format("%.2f",slider.getValue()), textSize, x + width/2, y + 50);
        this.val.setX(this.val.getX() - this.val.getWidth()/2);

        this.slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                val.setText(String.format("%.2f",slider.getValue()));
                PrefManager.setFloat(key, slider.getValue());
            }
        });
    }

    public void draw(SpriteBatch batch){
        this.name.draw(batch);
        this.val.draw(batch);
        this.slider.draw(batch, 1);
    }

    public Slider getSlider(){
        return this.slider;
    }
}
