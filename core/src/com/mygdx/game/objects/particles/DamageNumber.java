package com.mygdx.game.objects.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ui.FreeText;

public class DamageNumber extends BaseParticle{

    private FreeText text;
    private float alpha;
    private float spd = 1f;

    public DamageNumber(Vector2 pos, long ttl, int dmg){
        super(pos, new Vector2(0,1), ttl);
        this.init(pos, dmg);
    }

    public DamageNumber(Vector2 pos, Vector2 vel, long ttl, int dmg) {
        super(pos, vel, ttl);
        this.init(pos, dmg);
    }

    public void init(Vector2 pos, int dmg){
        this.setVel(this.getVel().scl(this.spd));
        this.text = new FreeText(pos.x, pos.y, 30, "-" + Integer.toString(dmg));
        this.alpha = 1.0f;
    }

    @Override
    public void update(float delta){
        super.update(delta);
        this.text.setX(this.getPosX());
        this.text.setY(this.getPosY());
        this.alpha -= 1f * delta;
        if(this.alpha <= 0)
            this.destroy();
    }

    @Override
    public void draw(SpriteBatch batch){
        this.text.draw(batch, this.alpha);
    }
}
