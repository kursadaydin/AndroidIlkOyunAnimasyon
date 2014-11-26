package com.example.androidilkoyunanimasyon;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.MultiTapKeyListener;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	private Camera camera;
	private Engine engine;
	Scene sahne;

	private Texture texAnim;
	private TiledTextureRegion tiledTexReg;
	private AnimatedSprite animSprite;

	@Override
	public Engine onLoadEngine() {
		// TODO Auto-generated method stub
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new FillResolutionPolicy(), camera);

		engineOptions.getTouchOptions().setRunOnUpdateThread(true);

		engine = new Engine(engineOptions);
		
		
		return engine;
	}

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		texAnim = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		tiledTexReg = TextureRegionFactory.createTiledFromAsset(texAnim, this,
				"smiles.png", 0, 0, 4, 1);
							//   column, row
		engine.getTextureManager().loadTexture(texAnim);

	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub

		engine.registerUpdateHandler(new FPSLogger());
		sahne = new Scene();
		sahne.setBackground(new ColorBackground(255, 255, 255));
		//                                  StartX,StartY,WidthX,HeightY
		Rectangle rectangle = new Rectangle(40, 10, 100, 100);
		rectangle.setColor(0, 0, 0);
		
		//                   StartX,StartY,StopX,StopY  
		Line line = new Line(100,100, 100,1000 );
		line.setColor(0, 0, 0);
		//                              Height,Width
		animSprite = new AnimatedSprite(400, 95, tiledTexReg);
		animSprite.animate(500);

		sahne.attachChild(animSprite);
		sahne.attachChild(line);
		return sahne;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

}
