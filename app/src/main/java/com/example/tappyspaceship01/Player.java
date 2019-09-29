package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {

    // PROPERTIES
  Bitmap playerImage;
  Rect hitbox;
  int xPosition;
  int direction;
  int yPosition;

    public Player(Context context, int x, int y) {

        //set initial position of the player.
        this.xPosition = x;
        this.yPosition = y;

        // 2. Set the default image - all players have same image
        this.playerImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.player_ship);

        // 3. Set the default hitbox - all enemies have same hitbox
//        this.hitbox = new Rect(
//                this.xPosition,
//                this.yPosition,
//                this.xPosition + this.playerImage.getWidth(),
//                this.playerImage.getHeight());
    }


    // GETTER AND SETTER METHODS
    public Bitmap getBitmap() {
        return this.playerImage;
    }

    public void setBitmap(Bitmap image) {
        this.playerImage = image;
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
    }

    public int getxPosition() {
        return this.xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return this.yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

}
