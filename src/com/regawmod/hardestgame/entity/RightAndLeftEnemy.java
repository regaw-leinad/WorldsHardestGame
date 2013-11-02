package com.regawmod.hardestgame.entity;

import com.regawmod.hardestgame.level.Level;

public class RightAndLeftEnemy extends Enemy
{
    public RightAndLeftEnemy(float x, float y, float speed, Level level)
    {
        super(x, y, level, true);

        this.setSpeed(speed);
    }

    @Override
    public void update(float dt)
    {
        this.moveX(this.speed * dt);

        if (this.collidesWithWall())
        {
            while (this.collidesWithWall())
            {
                if (this.speed <= 0)
                    this.moveX(.1f);
                else
                    this.moveX(-.1f);
            }

            this.speed *= -1;
        }
    }
}
