package com.regawmod.hardestgame.entity;

import org.newdawn.slick.GameContainer;
import com.regawmod.hardestgame.level.Level;

public class RightAndLeftEnemy extends Enemy
{
    public RightAndLeftEnemy(float x, float y, Level level)
    {
        super(x, y, level);

        this.setSpeed(300);
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        this.moveX(this.speed * dt);

        if (this.collidedWithWall())
        {

            while (this.collidedWithWall())
            {
                if (this.speed <= 0)
                    this.moveX(.1f);
                else
                    this.moveX(-.1f);
            }

            this.speed *= -1;
        }
    }

    private boolean collidedWithWall()
    {
        return this.level.collidesWithWall(this);
    }
}
