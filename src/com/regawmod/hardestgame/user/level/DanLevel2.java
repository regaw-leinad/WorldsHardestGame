package com.regawmod.hardestgame.user.level;

import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.user.enemy.BasicEnemy;

public class DanLevel2 extends Level
{
    public DanLevel2()
    {
    }

    @Override
    public void initBoundingPolygon()
    {
        addBoundingPolygonPoint(87, 79);
        addBoundingPolygonPoint(729, 79);
        addBoundingPolygonPoint(729, 483);
        addBoundingPolygonPoint(87, 483);
    }

    @Override
    protected void initStartZonePolygon()
    {
        addStartZonePolygonPoint(88, 80);
        addStartZonePolygonPoint(130, 80);
        addStartZonePolygonPoint(130, 140);
        addStartZonePolygonPoint(88, 140);
    }

    @Override
    protected void initEndZonePolygon()
    {
        addEndZonePolygonPoint(179, 178);
        addEndZonePolygonPoint(210, 178);
        addEndZonePolygonPoint(210, 218);
        addEndZonePolygonPoint(179, 218);
    }

    @Override
    protected void initEnemies()
    {
        addEnemy(new BasicEnemy(400, 300, this));
    }

    @Override
    protected void initGoldCoins()
    {
        addGoldCoin(new GoldCoin(500, 300, this));
    }

    @Override
    protected void initPlayerStartPosition()
    {
        setPlayerStartPosition(110, 110);
    }

    @Override
    protected void initInsidePolygons()
    {
    }
}
