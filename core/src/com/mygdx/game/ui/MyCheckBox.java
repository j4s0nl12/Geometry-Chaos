package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.utility.GameConstants;

public class MyCheckBox {

    private CheckBox box;
    private MyLabel name;
    private String key;

    public MyCheckBox(float x, float y, String name, String key){
        this.init(x,y,3f, GameConstants.getVirtualWidth()/4,40,name,key);
    }

    private void init(float x, float y, float scale, int width, int textSize, String name, String k){
        this.key = k;

        this.box = new CheckBox("", AssetManager.getNeonSkin());
        this.box.setChecked(PrefManager.getBoolean(this.key));
        this.box.setTransform(true);
        this.box.setScale(scale);
        this.box.setPosition(x+width/2-this.box.getWidth()*1.5f,y-this.box.getHeight()*1.5f);

        this.name = new MyLabel(name, textSize, x - width/2, y);

        this.box.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PrefManager.setBoolean(key, box.isChecked());
            }
        });
    }

    public void draw(SpriteBatch batch){
        this.name.draw(batch);
        this.box.draw(batch, 1f);
    }

    public CheckBox getCheckBox(){
        return this.box;
    }

    public boolean isChecked(){
        return this.box.isChecked();
    }

    public void setChecked(boolean isChecked){
        this.box.setChecked(isChecked);
        PrefManager.setBoolean(this.key, isChecked);
    }
}
