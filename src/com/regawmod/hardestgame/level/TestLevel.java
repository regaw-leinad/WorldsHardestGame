package com.regawmod.hardestgame.level;

import com.regawmod.hardestgame.entity.BasicEnemy;
import com.regawmod.hardestgame.entity.CirclingEnemy;
import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.entity.RightAndLeftEnemy;
import com.regawmod.hardestgame.entity.UpAndDownEnemy;

public class TestLevel extends Level
{
    public TestLevel()
    {
        super("testGame1");
    }

    @Override
    public void initBoundingPolygon()
    {
        addBoundingPolygonPoint(120, 150);
        addBoundingPolygonPoint(360, 150);
        addBoundingPolygonPoint(360, 390);
        addBoundingPolygonPoint(630, 390);
        addBoundingPolygonPoint(630, 240);
        addBoundingPolygonPoint(690, 240);
        addBoundingPolygonPoint(690, 421);
        addBoundingPolygonPoint(150, 421);
        addBoundingPolygonPoint(150, 480);
        addBoundingPolygonPoint(120, 480);
    }

    @Override
    protected void initStartZonePolygon()
    {
        addStartZonePolygonPoint(121, 150);
        addStartZonePolygonPoint(360, 150);
        addStartZonePolygonPoint(360, 210);
        addStartZonePolygonPoint(121, 210);
    }

    @Override
    protected void initEndZonePolygon()
    {
    }

    @Override
    public void initEnemies()
    {
        addEnemy(new BasicEnemy(180, 160, this));
        addEnemy(new RightAndLeftEnemy(200, 405, 200, this));
        addEnemy(new UpAndDownEnemy(675, 270, 300, this));

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                addEnemy(new CirclingEnemy(220, 300, j * Math.PI / 2, 15 + 15 * i, Math.PI / 2, this));
    }

    @Override
    protected void initGoldCoins()
    {
        addGoldCoin(new GoldCoin(200, 405, this));
        addGoldCoin(new GoldCoin(675, 257, this));
    }

    @Override
    public float getPlayerStartX()
    {
        return 195f;
    }

    @Override
    public float getPlayerStartY()
    {
        return 195f;
    }

}
