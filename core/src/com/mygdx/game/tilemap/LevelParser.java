package com.mygdx.game.tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.objects.BaseGameObject;
import com.mygdx.game.objects.enemies.Dummy;
import com.mygdx.game.objects.enemies.SuperDummy;
import com.mygdx.game.objects.player.Player;
import com.mygdx.game.utility.Utility;

import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LevelParser {

    private final String DIR = "Levels/";
    private final File PATH = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "Levels");

    public static final int EMPTY = 0;
    public static final int PLAYER = 1;
    public static final int DUMMY = 2;
    public static final int SUPERDUMMY = 3;

    public final int FREE = 0;
    public final int LOADING = 1;
    public final int SAVING = 2;

    private int state;

    public LevelParser(){
        state = FREE;
    }

    public TileMap load(TileMap map, String filename){
        state = LOADING;

        if(filename != null){
            FileHandle file = Gdx.files.internal(DIR + filename);
            if(file.exists()) {
                String text = file.readString();
                String lines[] = text.split("\\r?\\n");
                Array<Array<Integer>> nums = new Array();
                for (int x = 0; x < lines.length; x++) {
                    String line[] = lines[x].split(" ");
                    Array<Integer> tmp = new Array();
                    for (int y = 0; y < line.length; y++) {
                        tmp.add(Integer.parseInt(line[y]));
                    }
                    nums.add(tmp);
                }

                for (int r = 0; r < map.getRow(); r++) {
                    for (int c = 0; c < map.getCol(); c++) {
                        Tile t = map.getTile(c, r);
                        int num = nums.get(r).get(c);
                        BaseGameObject tmp = this.getObject(t, num);
                        map.setObject(c, r, tmp);
                    }
                }
            }
        }
        state = FREE;
        return map;
    }

    public TileMap load(TileMap map){
        state = LOADING;

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(PATH);
        int returnVal = fc.showOpenDialog(new JFrame());
        String filename = null;
        if(returnVal == JFileChooser.APPROVE_OPTION){
            filename = fc.getSelectedFile().getName();
        }

        return this.load(map, filename);
    }


    public void save(TileMap map){
        state = SAVING;

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(PATH);
        int returnVal = fc.showSaveDialog(new JFrame());
        String filename = null;
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile().getName();
        }

        if(filename != null) {
            FileHandle file = Gdx.files.local(DIR + filename);
            String s = "";
            int c_length = map.getCol();
            int r_length = map.getRow();
            for (int r = 0; r < r_length; r++) {
                for (int c = 0; c < c_length; c++) {
                    BaseGameObject o = map.getObject(c, r);
                    int idx = this.getIndex(o);
                    assert idx != -1;
                    s += Integer.toString(idx);
                    if (c < c_length - 1) {
                        s += " ";
                    } else if (c == c_length - 1) {
                        s += "\n";
                    }
                }
            }
            file.writeString(s, false);
        }
        state = FREE;
    }

    private int getIndex(BaseGameObject o){
        int tmp = -1;
        if(o == null){
            return EMPTY;
        }else{
            String name = o.getClass().getSimpleName();
            if(name.equals("Player")){
                tmp = PLAYER;
            }else if(name.equals("Dummy")){
                tmp = DUMMY;
            }else if(name.equals("SuperDummy")){
                tmp = SUPERDUMMY;
            }else{
                Utility.print("LevelParser","Error: Unknown object [" + name + "].");
            }
            return tmp;
        }
    }

    public BaseGameObject getObject(Tile t, int index){
        BaseGameObject o = null;
        switch(index){
            case EMPTY:
                break;
            case PLAYER:
                o = new Player(t.getCenterPos().cpy(), new Vector2());
                break;
            case DUMMY:
                o = new Dummy(t.getCenterPos().cpy(), new Vector2());
                break;
            case SUPERDUMMY:
                o = new SuperDummy(t.getCenterPos().cpy(), new Vector2());
                break;
        }
        return o;
    }

    public int getState(){
        return state;
    }
}
