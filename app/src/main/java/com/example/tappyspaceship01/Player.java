package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Set;

public class Player {

    // PROPERTIES
  Bitmap playerImage;

  Rect playerHitbox;
  int xPosition;
  //initial position player is not moving
  int direction=-1;
  int yPosition;


    public Player(Context context, int x, int y) {

        //set initial position of the player.
        this.xPosition = x;
        this.yPosition = y;

        // 2. Set the default image - all players have same image
        this.playerImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.player_ship);

        // 3. Set the default hitbox - all enemies have same hitbox
        this.playerHitbox = new Rect(
                this.xPosition,
                this.yPosition,
                this.xPosition + this.playerImage.getWidth(),
                this.yPosition+ this.playerImage.getHeight());

    }


     public void updatePlayerPosition()
     {

         if(this.direction==0)
        {
            this.yPosition=this.yPosition-15;
        }
        else if(this.direction==1)
        {
            this.yPosition=this.yPosition+15;
        }

         this.playerHitbox.top=this.yPosition;
         this.playerHitbox.bottom=this.yPosition+this.playerImage.getHeight();

    }

    // GETTER AND SETTER METHODS
    public Bitmap getBitmap() {
        return this.playerImage;
    }

    public void setBitmap(Bitmap image) {
        this.playerImage = image;
    }

    public Rect getHitbox() {
        return playerHitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.playerHitbox = hitbox;
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

    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }
}
