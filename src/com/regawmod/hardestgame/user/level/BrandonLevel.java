package com.regawmod.hardestgame.user.level;

import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.user.enemy.WaveEnemy;

public class BrandonLevel extends Level
{
    public BrandonLevel()
    {
        super();
    }

    @Override
    protected void initBoundingPolygon()
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
        setPlayerStartPosition(240, 75);
    }

    @Override
    protected void initEnemies()
    {
        //addEnemies(CirclingEnemy.getCircleGroupEnemy(this, 240f, 240, 4, 4, 50f, 70f, -Math.PI / 2));
        //addEnemy(new AwareEnemy(400, 300, this));
        //        addEnemy(new UpAndDownEnemy(480, 250, 200, this));
        //        addEnemy(new UpAndDownEnemy(420, 415, -200, this));
        //addEnemy(new CoinWatcherEnemy(480, 250, this));
        addEnemies(WaveEnemy.getWaveEnemyGroup(360, 629, 329, 50, this));
        //        addEnemy(new WaveEnemy(200f, 400f, 60f, 100f, this));
    }

    @Override
    protected void initGoldCoins()
    {
        //        for (int i = 0; i < 18; i++)
        //            addGoldCoin(new GoldCoin(450, 220 + 12 * i, this));

        addGoldCoin(new GoldCoin(240, 240, this));
    }

    @Override
    protected void initInsidePolygons()
    {
    }
}
