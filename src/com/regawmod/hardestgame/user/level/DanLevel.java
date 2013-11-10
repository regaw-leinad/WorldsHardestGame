package com.regawmod.hardestgame.user.level;

import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.user.enemy.BasicEnemy;
import com.regawmod.hardestgame.user.enemy.CirclingEnemy;
import com.regawmod.hardestgame.user.enemy.RightAndLeftEnemy;
import com.regawmod.hardestgame.user.enemy.UpAndDownEnemy;

public class DanLevel extends Level
{

    public DanLevel()
    {
    }

    @Override
    public void initBoundingPolygon()
    {
        //        
    }

    @Override
    protected void initStartZonePolygon()
    {
    }

    @Override
    protected void initEndZonePolygon()
    {
    }

    @Override
    protected void initPlayerStartPosition()
    {
        setPlayerStartPosition(85, 125);
    }

    @Override
    protected void initEnemies()
    {
        addEnemy(new BasicEnemy(400, 300, this));
        addEnemy(new UpAndDownEnemy(400, 300, 100f, this));
        addEnemy(new RightAndLeftEnemy(400, 300, 100f, this));
        //addEnemy(new CirclingEnemy(400, 300, 0, 50, Math.PI / 2, this));
        addEnemies(CirclingEnemy.getCircleGroupEnemy(this, 400, 300, 4, 4, 30, 16, Math.PI / 2, Math.PI / 12));

    }

    @Override
    protected void initGoldCoins()
    {
        addGoldCoin(new GoldCoin(66, 476, this));
    }

    @Override
    protected void initInsidePolygons()
    {
    }
}
