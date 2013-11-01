package com.regawmod.hardestgame.entity;

import org.newdawn.slick.GameContainer;
import com.regawmod.hardestgame.state.GamePlayState;

public class BackAndForthEnemy extends Enemy
{
    public BackAndForthEnemy(float x, float y, GamePlayState game)
    {
        super(x, y, game);
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        if (this.game.collidesWithWall(this.getBody()))
            this.speed *= -1;

        this.moveX(this.speed * dt);
    }

}
