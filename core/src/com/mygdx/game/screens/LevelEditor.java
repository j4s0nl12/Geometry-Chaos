package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.AssetManager;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.tilemap.LevelParser;
import com.mygdx.game.tilemap.Tile;
import com.mygdx.game.tilemap.TileMap;
import com.mygdx.game.ui.MyLabel;
import com.mygdx.game.ui.MyTextButton;
import com.mygdx.game.ui.ObjectScrollPane;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class LevelEditor extends BaseScreen{

    private TileMap tilemap;

    private LevelParser lp;

    private MyTextButton newButton;
    private MyTextButton load;
    private MyTextButton save;
    private MyTextButton back;

    private ObjectScrollPane sp;

    private MyLabel selected;
    private Sprite selectedSprite;
    
    public LevelEditor(GeometryChaos gam) {
        super(gam);
        this.tilemap = new TileMap(GameConstants.getGameWorldWidth(), GameConstants.getGameWorldHeight(), 100);

        this.lp = new LevelParser();

        float width = 100;
        float height = 75;
        int size = 35;
        float offset = .9f;

        this.selected = new MyLabel("Selected:", 35, GameConstants.getGameWorldX()/2 + 5,
                                    GameConstants.getGameWorldY2() - 35f/2);

        //Buttons
        this.newButton = new MyTextButton("New", size, GameConstants.getGameWorldX2() + width * offset,
                                          GameConstants.getGameWorldY2(), width, height){
            @Override
            public void click(){
                tilemap = new TileMap(GameConstants.getGameWorldWidth(), GameConstants.getGameWorldHeight(), 100);
            }
        };
        this.load = new MyTextButton("Load", size, GameConstants.getGameWorldX2() + width * offset,
                                     GameConstants.getGameWorldY2() - height * 1.2f,width, height){
            @Override
            public void click(){
                Utility.print(TAG, "Attempting to load file...");
                lp.load(tilemap);
                Utility.print(TAG, "Load successful!");
            }
        };
        this.save = new MyTextButton("Save", size, GameConstants.getGameWorldX2() + width * offset,
                                     GameConstants.getGameWorldY2() - height * 2.4f,width, height){
            @Override
            public void click(){
                lp.save(tilemap);
                Utility.print(TAG, "Level saved!");
            }
        };
        this.back = new MyTextButton("Back", size, GameConstants.getGameWorldX2() + width * offset,
                                     GameConstants.getGameWorldY(), width, height){
            @Override
            public void click(){
                game.goBack();
            }
        };

        //ScrollPane
        this.sp = new ObjectScrollPane();

        this.stage.addActor(this.sp.getScrollPane());
        this.stage.addActor(this.newButton.getActor());
        this.stage.addActor(this.load.getActor());
        this.stage.addActor(this.save.getActor());
        this.stage.addActor(this.back.getActor());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        Tile tmp = this.tilemap.getOverTile();
        if(tmp != null && this.sp.getSelectedIdx() != -1){
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                if(this.sp.getSelectedIdx() == LevelParser.PLAYER){
                    Tile tmp2 = this.tilemap.getPlayerTile();
                    if(tmp2 != null)
                        tmp2.setObject(null);
                }
                tmp.setObject(this.lp.getObject(tmp,this.sp.getSelectedIdx()));
            }
        }

        this.tilemap.drawTiles();

        game.batch.begin();
        this.tilemap.draw(game.batch);
        this.sp.draw(game.batch);
        this.selected.draw(game.batch);
        this.setSelectedSprite(this.sp.getSelectedIdx());
        if(this.sp.getSelectedIdx() != -1) {
            this.selectedSprite.draw(game.batch);
        }
        this.newButton.draw(game.batch);
        this.load.draw(game.batch);
        this.save.draw(game.batch);
        this.back.draw(game.batch);
        this.displayTime(game.batch);
        game.batch.end();
    }

    private void setSelectedSprite(int s){
        switch(s){
            case -1:
                break;
            case LevelParser.EMPTY:
                break;
            case LevelParser.PLAYER:
                this.selectedSprite = AssetManager.getPlayerSprite();
                break;
            case LevelParser.DUMMY:
                this.selectedSprite = AssetManager.getDummySprite();
                break;
            case LevelParser.SUPERDUMMY:
                this.selectedSprite = AssetManager.getSuperDummySprite();
                break;
            default:
                Utility.print(this.TAG, "Error: Unknown index '" + this.sp.getSelectedIdx() + "'.");
        }
        if(s != -1) {
            float x = GameConstants.getGameWorldX()/2;
            float y = GameConstants.getGameWorldY2() - 100;
            this.selectedSprite.setOrigin(x, y);
            this.selectedSprite.setPosition(x - this.selectedSprite.getWidth()/2, y - this.selectedSprite.getHeight()/2);
        }
    }
}
