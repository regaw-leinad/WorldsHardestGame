package com.regawmod.hardestgame.entity;

import com.regawmod.hardestgame.level.Level;

public class AwareEnemy extends Enemy
{
    private boolean inPosition;
    private double totalDistance;
    private double theta;
    private float startX;
    private float startY;

    public AwareEnemy(float x, float y, Level level)
    {
        super(x, y, level);

        this.inPosition = true;
        setSpeed(100f);

        this.startX = x;
        this.startY = y;
    }

    @Override
    protected void update(float dt)
    {
        if (!inPosition)
        {
            setSpeed(100f);

            if (this.totalDistance <= 0)
                inPosition = true;
            else
                moveEnemy(dt);
        }
        else
        {
            this.moveY(this.speed * dt);

            if (this.collidesWithWall() || this.collidesWithZone())
            {
                while (this.collidesWithWall() || this.collidesWithZone())
                    this.moveY(this.getBactrackComponent(dt));

                this.turnAround();
            }
        }
    }

    private void moveEnemy(float dt)
    {
        this.moveX((float)(Math.cos(theta) * this.speed * dt));
        this.moveY((float)(Math.sin(theta) * this.speed * dt));

        totalDistance -= this.speed * dt;
    }

    @Override
    public void onCoinCollected(float coinX, float coinY)
    {
        this.inPosition = false;

        this.theta = Math.atan2(coinY - this.getCenterY(), coinX - this.getCenterX());
        totalDistance = Math.hypot(coinX - this.getCenterX(), coinY - this.getCenterY());
    }

    @Override
    public void onPlayerDeath()
    {
        onCoinCollected(this.startX, this.startY);
    }
}
