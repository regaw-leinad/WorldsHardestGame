package com.regawmod.hardestgame.level;

import com.regawmod.hardestgame.entity.CirclingEnemy;
import com.regawmod.hardestgame.entity.RightAndLeftEnemy;
import com.regawmod.hardestgame.entity.UpAndDownEnemy;

public class TestLevel extends Level
{
    @Override
    public void initBoundingPolygon()
    {
        addPolygonPoint(120, 150);
        addPolygonPoint(360, 150);
        addPolygonPoint(360, 390);
        addPolygonPoint(630, 390);
        addPolygonPoint(630, 240);
        addPolygonPoint(690, 240);
        addPolygonPoint(690, 420);
        addPolygonPoint(150, 420);
        addPolygonPoint(150, 480);
        addPolygonPoint(120, 480);
    }

    @Override
    public void initEnemies()
    {
        //        addEnemy(new Enemy(160, 160, this));
        addEnemy(new RightAndLeftEnemy(200, 405, this));
        addEnemy(new UpAndDownEnemy(660, 270, this));

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 6; j++)
                addEnemy(new CirclingEnemy(220, 300, j * Math.PI / 3, 15 + 15 * i, Math.PI / 2, this));

        //        addEnemy(new Enemy(500, 400, this));
    }

    @Override
    protected void initGoldCoins()
    {
    }

    @Override
    public float getPlayerStartX()
    {
        return 180f;
    }

    @Override
    public float getPlayerStartY()
    {
        return 180f;
    }
}
