package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Enemy {

    // PROPERTIES:
    // Image
    // Hitbox
     Bitmap image;
     Rect enemyHitbox;
     int xPosition;
     int yPosition;


    public Enemy(Context context, int x, int y) {
        // 1. set up the initial position of the Enemy
        this.xPosition=x;
        this.yPosition=y;

        // 2. Set the default image - all enemies have same image
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.alien_ship2);

        // 3. Set the default hitbox - all enemies have same hitbox
        this.enemyHitbox = new Rect(
                this.xPosition,
                this.yPosition,
                this.xPosition + this.image.getWidth(),
                this.yPosition + this.image.getHeight()
   );
    }


    public  void updateEnemyPosition()
    {
        this.setxPosition(xPosition-25);
        this.enemyHitbox.left=this.xPosition;
        this.enemyHitbox.right=this.getxPosition()+this.image.getWidth();
    }

    // Getter and setters
    // Autogenerate this by doing Right Click --> Generate --> Getter&Setter

    public Bitmap getBitmap() {
        return this.image;
    }



    public Rect getHitbox() {
        return enemyHitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.enemyHitbox = hitbox;
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
