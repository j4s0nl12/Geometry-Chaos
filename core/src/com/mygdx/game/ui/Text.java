package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Text {

    private final String DIR = "Skins/clean-crispy/skin/";
    private final String JSON = "clean-crispy-ui.json";
    private final String ATLAS = "clean-crispy-ui.atlas";

    private Label label;
    private Skin skin;

    public Text(float x, float y, float scale, String text){
        this.skin = new Skin(Gdx.files.internal(DIR + JSON));
        this.skin.addRegions(new TextureAtlas(DIR + ATLAS));
        this.label = new Label(text, this.skin);
        this.label.setPosition(x,y);
        this.label.setFontScale(scale);
        this.label.setColor(0,204/256f,204/256f,1);
    }

    public void draw(SpriteBatch batch, float alpha){
        this.label.draw(batch,alpha);
    }

    public void setText(String text){
        this.label.setText(text);
    }
}
