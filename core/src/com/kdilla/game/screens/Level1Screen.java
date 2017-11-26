package com.kdilla.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.kdilla.game.engine.BaseScreen;
import com.kdilla.game.engine.utils.Camera;
import com.kdilla.game.level.map.LevelMap;
import com.kdilla.game.level.map.TiledLevelMap;

/**
 * Created by avetc on 25.11.2017.
 */

public class Level1Screen extends BaseScreen {
    Camera cam;
    LevelMap levelMap;

    float deltaX;
    float deltaY;
   //float aspect = Gdx.graphics.getHeight()/Gdx.graphics.getWidth() ;
    float aspect = 16f/9f ;
    float h = 300f;
//    float h = 300f;
    float w = (int) h * aspect;
    public Level1Screen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        System.out.println("show");
        batch = new SpriteBatch();

        cam = new Camera( w, h);
    //    cam.setToOrtho(false, w, h);
        cam.update();
        levelMap = new TiledLevelMap();
        levelMap.setCamera(cam);
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize");

        super.resize(width, height);
    }

    float halfHeight =  h/2;
    float halfWidth =  w/2;
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        if (Gdx.input.isTouched()) {
//            cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
//            deltaX += Gdx.input.getDeltaX();
//            deltaY += Gdx.input.getDeltaY();
//            //	cam.update();
//        }

//        if (Gdx.input.justTouched()) {
//            Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
////            Vector2 ps2 = new Vector2();
////            ps2.x = pos.x; ps2.y = pos.y;
//            TileType type = levelMap.getTileByLocation(1, pos.x, pos.y);
//
//            if (type != null)
//                System.out.println("You clicked " + type.getId() + " " + type.getName());
//
//        }


        levelMap.render(cam, batch);

        update(delta);
    }

    private void update(float delta) {
        levelMap.update(Gdx.graphics.getDeltaTime());

//
//        System.out.println((cam.position.x-halfWidth) + ", "+ levelMap.getLeftPixel() );
//        if (cam.position.x-halfWidth > levelMap.getLeftPixel()
//                || cam.position.x+halfWidth < levelMap.getRightPixel() ||
//                cam.position.y+halfHeight < levelMap.getTopPixel() ||
//                cam.position.y-halfHeight > levelMap.getBottomPixel()
//                )

        if (cam.getLeft()<=levelMap.getLeftPixel()
                &&levelMap.getPlayer().getX()-cam.getHalfWidth()<=levelMap.getLeftPixel()) {
            cam.setLeft(levelMap.getLeftPixel());
            cam.position.y=levelMap.getPlayer().getY()+80;
        }else if (cam.getRight()>=levelMap.getRightPixel()
                && levelMap.getPlayer().getX()+cam.getHalfWidth()>=levelMap.getRightPixel()) {
            cam.setRight(levelMap.getRightPixel());
            cam.position.y=levelMap.getPlayer().getY()+80;
        }else
           cam.position.set(levelMap.getPlayer().getX(), levelMap.getPlayer().getY() + 80, 0);
//        else if (cam.position.x-halfWidth < levelMap.getLeftPixel()) {
//            cam.position.set(levelMap.getLeftPixel()+halfWidth, levelMap.getPlayer().getY() + 80, 0);
//        }else if ( cam.position.x+halfWidth > levelMap.getRightPixel()) {
//            cam.position.set(levelMap.getRightPixel()-halfWidth, levelMap.getPlayer().getY() + 80, 0);
//        }
        cam.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        levelMap.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
//        switch (state) {
//            case PLAYING:
        System.out.println("touch");
//                break;
//            case GAME_OVER:
//                buttonNewGame.touchUp(touch, pointer);
//                break;
//            default:
//                throw new RuntimeException("Unknown state = " + state);
//        }
    }
}
