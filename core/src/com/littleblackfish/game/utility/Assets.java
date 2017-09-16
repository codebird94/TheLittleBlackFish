package com.littleblackfish.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by RAYAPARDAZ1 on 06/09/2017.
 */

public class Assets implements Disposable , AssetErrorListener {

    private static final String TAG = Assets.class.getName();

    public static Assets instance = new Assets();

    private AssetManager assetManager;

    public FishAssets fishAssets;

    private Assets(){
    }


    public void init(){
        this.assetManager = new AssetManager();
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);

        fishAssets = new FishAssets(atlas);
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class FishAssets{

        public TextureAtlas.AtlasRegion fish0;
        public TextureAtlas.AtlasRegion fish1;

        public FishAssets(TextureAtlas atlas){
            fish0 = atlas.findRegion(Constants.FISH_0);
            fish1 = atlas.findRegion(Constants.FISH_1);
        }

    }

    public class MazeAssets{

    }

}
