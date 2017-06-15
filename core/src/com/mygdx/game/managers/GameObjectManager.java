package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.QuadTree;
import com.mygdx.game.utility.Utility;

import java.util.Iterator;

public class GameObjectManager {

    private static Array<BaseGameObject> olist;
    private QuadTree quad;
    private PlayerController player;

    public GameObjectManager(){
        olist = new Array();
        quad = new QuadTree(0, 0, 0, GameConstants.getVirtualWidth(), GameConstants.getVirtualHeight());
    }

    public void update(float delta){
        Iterator<BaseGameObject> it = olist.iterator();
        while(it.hasNext()){
            BaseGameObject tmp = it.next();
            if(tmp.getToDestory()){
                it.remove();
            }
        }

        player.update(delta);

        for(BaseGameObject o : olist){
            o.update(delta);
        }

        //quad.update(olist);
        quad.update(collideableList());
    }

    public void draw(SpriteBatch batch){
        olist.sort();
        for(int i = 0; i < olist.size; i++){
            olist.get(i).draw(batch);
        }
    }

    public static void add(BaseGameObject o){
        olist.add(o);
    }

    public void addPlayer(float x, float y){
        if(this.player == null) {
            add(new Player(new Vector2(x, y), new Vector2()));
            this.player = new PlayerController((Player) olist.get(olist.size - 1));
        }else{
            Utility.print("GOM","Error: Player already initialized!");
        }
    }

    public PlayerController getPlayerController(){
        return this.player;
    }

    private Array<BaseGameObject> collideableList(){
        Array<BaseGameObject> tmp = new Array();
        for(BaseGameObject o : this.olist){
            if(o.canCollide()){
                tmp.add(o);
            }
        }
        return tmp;
    }
}
