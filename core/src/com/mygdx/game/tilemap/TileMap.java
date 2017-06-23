package com.mygdx.game.tilemap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.utility.GameConstants;

public class TileMap {

    Array<Array<Tile>> tiles;

    int col;
    int row;

    private ShapeRenderer sr;

    public TileMap(int width, int height, int size){
        this.init(width, height, size, size);
    }

    public TileMap(int width, int height, int tileWidth, int tileHeight){
        this.init(width, height, tileWidth, tileHeight);
    }

    private void init(int width, int height, int tileWidth, int tileHeight){
        this.col = width / tileWidth;
        this.row = height / tileHeight;
        this.tiles = new Array();
        for(int x = 0; x < this.col; x++){
            Array<Tile> tmp = new Array();
            for(int y = 0; y < this.row; y++){
                float thisX = GameConstants.getGameWorldX() + (x * tileWidth);
                float thisY = GameConstants.getGameWorldY2() - ((y+1) * tileHeight);
                tmp.add(new Tile(new Vector2(thisX, thisY), tileWidth, tileHeight));
            }
            this.tiles.add(tmp);
        }

        this.sr = new ShapeRenderer();
        this.sr.setAutoShapeType(true);
    }

    public void draw(SpriteBatch batch){
        for(Array<Tile> a : this.tiles){
            for(Tile t : a){
                t.draw(batch);
            }
        }
    }

    public void drawTiles(){
        this.sr.setProjectionMatrix(GeometryChaos.camera.combined);
        this.sr.begin(ShapeRenderer.ShapeType.Line);
        for(Array<Tile> a : this.tiles){
            for(Tile t : a){
                t.drawTiles(this.sr);
            }
        }
        this.sr.end();
    }

    public Tile getTile(int c, int r){
        return this.tiles.get(c).get(r);
    }

    public Tile getOverTile(){
        for(Array<Tile> a : this.tiles){
            for(Tile t : a){
                if(t.getIsOver()){
                    return t;
                }
            }
        }
        return null;
    }

    public Tile getPlayerTile(){
        for(Array<Tile> a : this.tiles){
            for(Tile t : a){
                if(t.getObject() != null) {
                    if (t.getObject().getClass() == Player.class) {
                        return t;
                    }
                }
            }
        }
        return null;
    }

    public BaseGameObject getObject(int c, int r){
        return this.tiles.get(c).get(r).getObject();
    }

    public void setObject(int c, int r, BaseGameObject o){
        this.tiles.get(c).get(r).setObject(o);
    }

    public int getCol(){
        return this.col;
    }

    public int getRow(){
        return this.row;
    }

    public Array<Array<Tile>> getTiles(){
        return this.tiles;
    }
}
