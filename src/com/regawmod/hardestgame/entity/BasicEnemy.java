package com.regawmod.hardestgame.entity;

import com.regawmod.hardestgame.level.Level;

public class BasicEnemy extends Enemy
{
    public BasicEnemy(float x, float y, Level level)
    {
        super(x, y, level, true);
    }

    @Override
    public void update(float dt)
    {
    }
}
