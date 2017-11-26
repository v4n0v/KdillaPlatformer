package com.kdilla.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.kdilla.game.level.map.LevelMap;
import com.kdilla.game.level.map.TileType;
import com.kdilla.game.level.map.TiledLevelMap;
import com.kdilla.game.screens.Level1Screen;


public class KdillaPlatformer extends Game {
	@Override
	public void create() {
		setScreen(new Level1Screen(this));
	}

//public class KdillaPlatformer extends ApplicationAdapter {



//	OrthographicCamera cam;
//
//	SpriteBatch batch;
//	Texture img;
//
//	LevelMap levelMap;
//
//	float deltaX;
//	float deltaY;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		float aspect = 16f / 9f;
////		float w = Gdx.graphics.getWidth();
////		float h = Gdx.graphics.getHeight();
//
//		float h = 300;
//		float w = (int)h* aspect;
//		cam = new OrthographicCamera();
//		cam.setToOrtho(false, w,h);
//		cam.update();
//
//		levelMap= new TiledLevelMap();
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		if (Gdx.input.isTouched()){
//			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
//			deltaX+=Gdx.input.getDeltaX();
//			deltaY+=Gdx.input.getDeltaY();
//		//	cam.update();
//		}
//
//		if (Gdx.input.justTouched()){
//			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0 ));
//			TileType type = levelMap.getTileByLocation(1, pos.x, pos.y);
//
//			if (type!=null)
//				System.out.println("You clicked " + type.getId()+ " "+ type.getName());
//
//		}
//
//		cam.position.set(levelMap.getPlayer().getX(),levelMap.getPlayer().getY()+80, 0 );
//		cam.update();
//		levelMap.update(Gdx.graphics.getDeltaTime());
//		levelMap.render(cam, batch);
//
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		levelMap.dispose();
//	}
//}

}
