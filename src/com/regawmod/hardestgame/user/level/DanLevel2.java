package com.regawmod.hardestgame.user.level;

import com.regawmod.hardestgame.entity.GoldCoin;
import com.regawmod.hardestgame.level.Level;
import com.regawmod.hardestgame.user.enemy.RightAndLeftEnemy;

public class DanLevel2 extends Level
{
    @Override
    public void initBoundingPolygon()
    {
        float[] points = { 38f, 10f, 749f, 10f, 749f, 102f, 567f, 102f,
                567f, 451f, 192f, 451f, 192f, 300f, 38f, 300f };
        setBoundingPolygonPoints(points);
    }

    @Override
    public void initStartZonePolygon()
    {
        float[] points = { 39f, 11f, 114f, 11f, 114f, 86f, 39f, 86f };
        setStartZonePolygonPoints(points);
    }

    @Override
    public void initEndZonePolygon()
    {
        float[] points = { 491f, 375f, 566f, 375f, 566f, 450f, 491f, 450f };
        setEndZonePolygonPoints(points);
    }

    @Override
    public void initInsidePolygons()
    {
        float[] inside1 = { 156f, 111f, 312f, 111f, 312f, 196f, 156f, 196f };
        addInsideLevelPolygonFromPoints(inside1);

        float[] inside2 = { 391f, 156f, 491f, 156f, 491f, 344f, 391f, 344f };
        addInsideLevelPolygonFromPoints(inside2);

        float[] inside3 = { 212f, 301f, 335f, 301f, 335f, 434f, 212f, 434f };
        addInsideLevelPolygonFromPoints(inside3);
    }

    // Suggested Player Start Position Code:
    @Override
    public void initPlayerStartPosition()
    {
        setPlayerStartPosition(76.5f, 48.5f);
    }

    @Override
    protected void initEnemies()
    {
        addEnemy(new RightAndLeftEnemy(300, 250, 100f, this));
    }

    @Override
    protected void initGoldCoins()
    {
        addGoldCoin(new GoldCoin(500, 300, this));
    }
}
