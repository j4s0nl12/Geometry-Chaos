package com.mygdx.game.objects.particles;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.managers.AssetManager;

import java.awt.Color;

public class RainbowParticle extends BaseParticle{

    private float spd = 1f;
    private float alpha;

    public RainbowParticle(Vector2 pos, Vector2 vel, long ttl) {
        super(pos, vel, ttl);
        this.setVel(this.getVel().scl(this.spd));
        this.setSprite(AssetManager.getBulletSprite(), .15f);
        this.alpha = 1f;

        java.awt.Color java_color = Color.getHSBColor(MathUtils.random(1f),1,1);
        java_color = java_color.brighter().brighter().brighter().brighter();
        float r = (float)(java_color.getRed()/255);
        float g = (float)(java_color.getGreen()/255);
        float b = (float)(java_color.getBlue()/255);
        float a = (float)(java_color.getAlpha()/255);
        com.badlogic.gdx.graphics.Color gdx_color = new com.badlogic.gdx.graphics.Color(r,g,b,a);
        this.setSpriteColor(gdx_color);
    }

    @Override
    public void update(float delta){
        super.update(delta);
        this.setAlpha(this.alpha);
        this.alpha -= 1f * delta;
        if(this.alpha <= 0)
            this.destroy();
    }
}
