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
                this.moveX(getBactrackComponent());

            this.speed *= -1;
        }
    }
}
