package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;


public class FreeText {

    private final String DIR = "Fonts/neon pixel/";
    private final String FONT = "neon_pixel-7.ttf";

    private BitmapFont font;
    private GlyphLayout layout;

    private float x;
    private float y;
    private String text;

    public FreeText(float x, float y, int size, String text){
        this.x = x;
        this.y = y;
        this.text = text;

        FileHandle fontFile = Gdx.files.internal(DIR + FONT);
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontParameter par = new FreeTypeFontParameter();
        par.size = size;

        this.font = gen.generateFont(par);
        gen.dispose();

        this.layout = new GlyphLayout();
        this.layout.setText(font, this.text);
        this.font.setColor(0,204/256f, 204/256f, 1);
    }

    public void draw(SpriteBatch batch){
        this.font.draw(batch, this.text, this.x - this.layout.width, this.y, this.layout.width, Align.center, false);
    }

    public void setText(String text){
        this.text = text;
        this.layout.setText(this.font, this.text);
    }

    public void dispose(){
        this.font.dispose();
    }
}
