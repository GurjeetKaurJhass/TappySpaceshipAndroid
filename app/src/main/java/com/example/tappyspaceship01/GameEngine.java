package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="TAPPY-SPACESHIP";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;

    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    Player player;
    Enemy enemy;
    // ----------------------------

    // ----------------------------
    // ## GAME STATS
    // ----------------------------

    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();
        this.screenWidth = w;
        this.screenHeight = h;
        this.printScreenInfo();
        this.spawnPlayer();
        this.spawnEnemyShips();
        // @TODO: Add your sprites
        // @TODO: Any other game setup

    }


    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
        player=new Player(this.getContext(),100,100);
    }


    private void spawnEnemyShips() {
        Random random = new Random();

        enemy=new Enemy(this.getContext(),1400,100);
        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------



    public void updatePositions() {
        // @TODO: Update position of player

      this.player.updatePlayerPosition();
      this.enemy.updateEnemyPosition();

      if(this.enemy.getxPosition()<=0)
      {
          this.enemy.setxPosition(screenWidth);
      }

      if(this.player.getHitbox().intersect(this.enemy.getHitbox())==true)

      {
          //Log.d(TAG,"Player has collided with the enemy");

          this.player.setxPosition(100);
          this.player.setyPosition(600);
          this.player.getHitbox().left=this.player.getxPosition();
          this.player.getHitbox().top=this.player.getyPosition();
          this.player.getHitbox().bottom=this.player.getyPosition()+this.player.playerImage.getHeight();


      }


    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);
            canvas.drawBitmap(this.player.getBitmap(),this.player.getxPosition(),this.player.getyPosition(),paintbrush);
            canvas.drawBitmap(this.enemy.getBitmap(),this.enemy.getxPosition(),this.enemy.getyPosition(),paintbrush);
            // DRAW THE PLAYER HITBOX
            // ------------------------
            paintbrush.setColor(Color.BLUE);

            canvas.drawRect(this.player.getHitbox(),paintbrush);
            canvas.drawRect(this.enemy.getHitbox(),paintbrush);
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            //----------------------------------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {
            //Log.d(TAG, "Person tapped the screen");
          this.player.setDirection(0);

        }
        else if (userAction == MotionEvent.ACTION_UP) {
           // Log.d(TAG, "Person lifted finger");
            this.player.setDirection(1);
        }

        return true;
    }
}
