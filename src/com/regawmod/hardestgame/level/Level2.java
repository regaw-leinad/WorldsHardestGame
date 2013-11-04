package com.regawmod.hardestgame.level;

import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.entity.UpAndDownEnemy;

public class Level2 extends Level
{
    public Level2()
    {
        super("level2");
    }

    @Override
    protected void initBoundingPolygon()
    {
        addBoundingPolygonPoint(90, 30);
        addBoundingPolygonPoint(270, 30);
        addBoundingPolygonPoint(270, 420);
        addBoundingPolygonPoint(90, 420);
    }

    @Override
    protected void initStartZonePolygon()
    {
        addStartZonePolygonPoint(90, 30);
        addStartZonePolygonPoint(270, 30);
        addStartZonePolygonPoint(270, 90);
        addStartZonePolygonPoint(90, 90);
    }

    @Override
    protected void initEndZonePolygon()
    {
        addEndZonePolygonPoint(90, 360);
        addEndZonePolygonPoint(270, 360);
        addEndZonePolygonPoint(270, 420);
        addEndZonePolygonPoint(90, 420);
    }

    @Override
    protected void initEnemies()
    {
        addEnemy(new UpAndDownEnemy(230, 300, 150, this));
    }

    @Override
    protected void initGoldCoins()
    {
        addGoldCoin(new GoldCoin(180, 300, this));
    }

    @Override
    protected void initPlayerStartPosition()
    {
        setPlayerStartPosition(180, 60);
    }

}
