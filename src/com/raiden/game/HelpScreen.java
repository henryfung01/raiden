package com.raiden.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.raiden.framework.Game;
import com.raiden.framework.Graphics;
import com.raiden.framework.Screen;

public class HelpScreen extends Screen {
	
	//TODO strings instead of image!
	private final static int TITLE_FONT_SIZE = 90;
	private final static int TITLE_X = 370;
	private final static int TITLE_Y = 225;
	private final static int TITLE_ANGLE = -27;
	
	private final static String HELP_TITLE = "Help";
	private Paint helpTitlePaint;
	
	private final static int TEXT_FONT_SIZE = 50;
	private final static String HELP_TEXT = "Defeat all the enemies and strive to stay alive until the end!\n" +
			"Touch and drag anywhere on the screen to control your plane. Your plane shoots automatically as you touch.\n" +
			"Grab powerups to become even stronger!\n" +
			"Be careful, you can only handle a small amount of damage!";
	private final static int HELP_TEXT_X = 20;
	private final static int HELP_TEXT_Y = 720;
	private Paint helpTextPaint;
	
	private Screen previousScreen;
	private boolean bgPainted = false;

	private HelpScreen(Game game) {
		super(game);
		
		Typeface face=Typeface.createFromAsset(game.getAssets(), ScreenButton.GAME_FONT);
		helpTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		helpTitlePaint.setTextAlign(Paint.Align.CENTER);
		helpTitlePaint.setTextSize(TITLE_FONT_SIZE);
		helpTitlePaint.setAntiAlias(true);
		helpTitlePaint.setTypeface(face);
		helpTitlePaint.setColor(ScreenButton.GAME_FONT_COLOR);
		helpTitlePaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLACK);
		
		helpTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		helpTextPaint.setTextSize(TEXT_FONT_SIZE);
		helpTextPaint.setAntiAlias(true);
		helpTextPaint.setTypeface(face);
		helpTextPaint.setColor(ScreenButton.GAME_FONT_COLOR);
		helpTextPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLACK);
	}
	
	public HelpScreen(Game game, Screen previousScreen) {
		this(game);
		this.previousScreen = previousScreen;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		if(!bgPainted) {
			g.drawImage(Assets.helpMenu, 0, 0);
			g.drawRotatedString(HELP_TITLE, TITLE_X, TITLE_Y, TITLE_ANGLE, helpTitlePaint);
			g.drawText(HELP_TEXT, HELP_TEXT_X, HELP_TEXT_Y, 780, helpTextPaint);
			bgPainted = true;
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		bgPainted = false;
		game.setScreen(previousScreen);
	}
	
	@Override
	public void pauseMusic() {
		Assets.menuMusic.pause();
	}

	@Override
	public void resumeMusic() {
		Assets.menuMusic.play();
	}

}
