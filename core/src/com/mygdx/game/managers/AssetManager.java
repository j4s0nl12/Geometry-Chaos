package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    private final String FONT = "Fonts/neon pixel/neon_pixel-7.ttf";

    private final String NEON_SKIN = "Skins/neon/skin/";
    private final String NEON_JSON = "neon-ui.json";
    private final String NEON_ATLAS = "neon-ui.atlas";

    private FileHandle fontFile;
    private static FreeTypeFontGenerator gen;
    private static FreeTypeFontParameter par;

    private static Skin neonSkin;

    public AssetManager(){
        //Fonts
        fontFile = Gdx.files.internal(FONT);
        gen = new FreeTypeFontGenerator(fontFile);
        par = new FreeTypeFontParameter();

        //Skins
        neonSkin = new Skin(Gdx.files.internal(NEON_SKIN + NEON_JSON));
        neonSkin.addRegions(new TextureAtlas(NEON_SKIN + NEON_ATLAS));
    }

    public static BitmapFont generateFont(int size){
        par.size = size;
        return gen.generateFont(par);
    }

    public static Skin getNeonSkin(){
        return neonSkin;
    }

    public void dispose(){
        this.gen.dispose();
    }
}
