package com.regawmod.hardestgame.level;

import com.regawmod.hardestgame.entity.GoldCoin;

public class DanLevel extends Level
{

    public DanLevel()
    {
    }

    @Override
    public void initBoundingPolygon()
    {
        addBoundingPolygonPoint(54, 461);
        addBoundingPolygonPoint(352, 461);
        addBoundingPolygonPoint(352, 392);
        addBoundingPolygonPoint(68, 392);
        addBoundingPolygonPoint(68, 109);
        addBoundingPolygonPoint(103, 109);
        addBoundingPolygonPoint(103, 257);
        addBoundingPolygonPoint(216, 257);
        addBoundingPolygonPoint(216, 212);
        addBoundingPolygonPoint(186, 212);
        addBoundingPolygonPoint(186, 238);
        addBoundingPolygonPoint(132, 238);
        addBoundingPolygonPoint(132, 30);
        addBoundingPolygonPoint(187, 30);
        addBoundingPolygonPoint(187, 154);
        addBoundingPolygonPoint(216, 154);
        addBoundingPolygonPoint(216, 108);
        addBoundingPolygonPoint(604, 108);
        addBoundingPolygonPoint(604, 257);
        addBoundingPolygonPoint(725, 257);
        addBoundingPolygonPoint(725, 393);
        addBoundingPolygonPoint(396, 393);
        addBoundingPolygonPoint(396, 461);
        addBoundingPolygonPoint(775, 461);
        addBoundingPolygonPoint(775, 491);
        addBoundingPolygonPoint(54, 491);
    }

    @Override
    protected void initStartZonePolygon()
    {
        addStartZonePolygonPoint(69, 110);
        addStartZonePolygonPoint(103, 110);
        addStartZonePolygonPoint(103, 140);
        addStartZonePolygonPoint(69, 140);
    }

    @Override
    protected void initEndZonePolygon()
    {
        addEndZonePolygonPoint(69, 110);
        addEndZonePolygonPoint(103, 110);
        addEndZonePolygonPoint(103, 140);
        addEndZonePolygonPoint(69, 140);
    }

    @Override
    protected void initPlayerStartPosition()
    {
        setPlayerStartPosition(85, 125);
    }

    @Override
    protected void initEnemies()
    {
    }

    @Override
    protected void initGoldCoins()
    {
        addGoldCoin(new GoldCoin(66, 476, this));
    }

    @Override
    public String getName()
    {
        return "Dan Level";
    }

}
