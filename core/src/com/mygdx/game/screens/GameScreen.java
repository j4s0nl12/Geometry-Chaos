package com.mygdx.game.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.main.GeometryChaos;
import com.mygdx.game.managers.GameObjectManager;
import com.mygdx.game.managers.PrefManager;
import com.mygdx.game.managers.ScreenManager;
import com.mygdx.game.objects.enemies.Dummy;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.tilemap.LevelParser;
import com.mygdx.game.tilemap.Tile;
import com.mygdx.game.tilemap.TileMap;
import com.mygdx.game.ui.MyLabel;
import com.mygdx.game.ui.MyTextButton;
import com.mygdx.game.utility.GameConstants;
import com.mygdx.game.utility.Utility;

public class GameScreen extends BaseScreen{

    private static GameObjectManager gom;

    private MyLabel pause_title;
    private MyTextButton pause;
    private MyTextButton unpause;
    private MyTextButton mainmenu;
    private MyTextButton options;

    public GameScreen(GeometryChaos gam) {
        super(gam);
        gom = new GameObjectManager();

        TileMap map = new TileMap(GameConstants.getGameWorldWidth(), GameConstants.getGameWorldHeight(), 100);
        LevelParser lp = new LevelParser();
        lp.load(map);
        //lp.load(map, "test.txt");

        for(Array<Tile> a : map.getTiles()){
            for(Tile t : a){
                if(t.getObject() != null){
                    if(t.getObject().getClass() == Player.class){
                        gom.addPlayer(t.getObject().getPosX(), t.getObject().getPosY());
                    }else{
                        gom.add(t.getObject());
                    }
                }
            }
        }

        for(Actor a : gom.getPlayerController().getActors()){
            this.stage.addActor(a);
        }

        int button_width = 275;
        int button_height = 175;
        int textButtonSize = 48;

        float width = GameConstants.getVirtualWidth();
        float middleX = width/2;
        float height = GameConstants.getVirtualHeight();

        pause_title = new MyLabel("Paused", 160, middleX, height*8/10);

        pause = new MyTextButton("||", 40, width*24/25,height*93/100, 70, 75){
            @Override
            public void click(){
                if(compareState(BaseScreen.STATE_RUN))
                    pause();
            }
        };
        unpause = new MyTextButton("Continue", textButtonSize, middleX, height*6.25f/10,
                                   button_width, button_height){
            @Override
            public void click(){
                if(compareState(BaseScreen.STATE_PAUSED))
                    resume();
            }
        };
        options = new MyTextButton("Options", textButtonSize, middleX, height*4.5f/10,
                                   button_width, button_height){
            @Override
            public void click(){
                if(compareState(BaseScreen.STATE_PAUSED))
                    game.goToScreen(ScreenManager.OPTIONSSCREEN);
            }
        };
        mainmenu = new MyTextButton("MainMenu", textButtonSize, middleX, height*2.75f/10,
                                    button_width, button_height){;
            @Override
            public void click(){
                if(compareState(BaseScreen.STATE_PAUSED))
                    game.goToScreen(ScreenManager.MAINMENUSCREEN);
            }
        };

        this.stage.addActor(pause.getActor());
        this.stage.addActor(unpause.getActor());
        this.stage.addActor(options.getActor());
        this.stage.addActor(mainmenu.getActor());
    }

    @Override
    public void render(float delta){
        super.render(delta);

        //Updates
        if(this.compareState(BaseScreen.STATE_RUN)) {
            gom.update(delta);
        }

        game.batch.begin();
        gom.draw(game.batch);
        if(this.compareState(BaseScreen.STATE_RUN)) {
            gom.getPlayerController().draw(game.batch);
            this.pause.draw(game.batch);
        }else if(this.compareState(BaseScreen.STATE_PAUSED)){
            this.pause_title.draw(game.batch);
            this.unpause.draw(game.batch);
            this.mainmenu.draw(game.batch);
            this.options.draw(game.batch);
        }
        this.displayTime(game.batch);
        game.batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        super.touchDown(screenX, screenY, pointer, button);
        //Change Cpad position
        Vector2 mPos = Utility.getUnprojectAt(screenX, screenY, 0);
        if(!PrefManager.getBoolean(PrefManager.lockCpads_str)){
            if (mPos.x < GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayerController().Lpad.isTouched()) {
                    gom.getPlayerController().Lpad.setPosition(mPos);
                    gom.getPlayerController().Lpad.fire(mPos, pointer);
                }
            } else if (mPos.x > GameConstants.getVirtualWidth() / 2) {
                if (!gom.getPlayerController().Rpad.isTouched()) {
                    gom.getPlayerController().Rpad.setPosition(mPos);
                    gom.getPlayerController().Rpad.fire(mPos, pointer);
                }
            }
        }
        return true;
    }
}
