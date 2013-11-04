package com.regawmod.hardestgame.level;

import com.regawmod.hardestgame.entity.CirclingEnemy;
import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.entity.UpAndDownEnemy;

public class BrandonLevel extends Level
{
    public static final String IMAGE_NAME = "brandonLevel";

    public BrandonLevel()
    {
        super(IMAGE_NAME);
    }

    @Override
    protected void initBoundingPolygon()
    {
        addBoundingPolygonPoint(119, 30);
        addBoundingPolygonPoint(360, 30);
        addBoundingPolygonPoint(360, 209);
        addBoundingPolygonPoint(629, 209);
        addBoundingPolygonPoint(629, 89);
        addBoundingPolygonPoint(750, 89);
        addBoundingPolygonPoint(750, 450);
        addBoundingPolygonPoint(119, 450);
    }

    @Override
    protected void initStartZonePolygon()
    {
        addStartZonePolygonPoint(120, 31);
        addStartZonePolygonPoint(360, 31);
        addStartZonePolygonPoint(360, 90);
        addStartZonePolygonPoint(120, 90);
    }

    @Override
    protected void initEndZonePolygon()
    {
        addEndZonePolygonPoint(630, 90);
        addEndZonePolygonPoint(750, 90);
        addEndZonePolygonPoint(750, 150);
        addEndZonePolygonPoint(630, 150);
    }

    @Override
    protected void initPlayerStartPosition()
    {
        setPlayerStartPosition(240, 60);
    }

    @Override
    protected void initEnemies()
    {
        addEnemies(CirclingEnemy.getCircleGroupEnemy(this, 240f, 240, 4, 4, 50f, 20f, Math.PI / 2));

        addEnemy(new UpAndDownEnemy(480, 250, 200, this));
        addEnemy(new UpAndDownEnemy(420, 415, -200, this));
    }

    @Override
    protected void initGoldCoins()
    {
        for (int i = 0; i < 18; i++)
            addGoldCoin(new GoldCoin(450, 220 + 12 * i, this));

        addGoldCoin(new GoldCoin(240, 240, this));
    }
}
