package com.regawmod.hardestgame.entity.enemy.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.entity.EntityRenderer;

public class BlueEnemyRenderer implements EntityRenderer
{
    private Enemy enemy;

    public BlueEnemyRenderer(Enemy enemy)
    {
        this.enemy = enemy;
    }

    @Override
    public void renderEntity(Graphics g)
    {
        g.setColor(Color.black);
        g.fillOval(this.enemy.getX(), this.enemy.getY(), this.enemy.getWidth(), this.enemy.getHeight());
        g.setColor(Color.blue);
        g.fillOval(this.enemy.getX() + 3, this.enemy.getY() + 3, this.enemy.getWidth() - 6, this.enemy.getHeight() - 6);
    }

}
