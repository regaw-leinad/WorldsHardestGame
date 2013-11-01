package com.regawmod.hardestgame.level;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import com.regawmod.entity.Entity;
import com.regawmod.hardestgame.entity.Enemy;

public abstract class Level extends Entity
{
    private List<Enemy> enemies;

    public Level(GameContainer gc)
    {
        super(new Rectangle(0, 0, gc.getWidth(), gc.getHeight()));

        enemies = new ArrayList<Enemy>();
    }

    public abstract void initLevel(GameContainer gc);

    public void addEnemy(Enemy e)
    {
        this.enemies.add(e);
    }

}
