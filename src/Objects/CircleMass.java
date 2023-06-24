package Objects;

import Interfaces.Drawable;
import Tools.DrawCircle;

import java.awt.*;

public class CircleMass extends Mass implements Drawable {

    public CircleMass(Vector position , double mass){
        super(position, mass);
    }

    public CircleMass(Vector position , Vector velocity, double mass){
        super(position, velocity, mass);
    }

    public CircleMass(Vector position , Vector velocity, Vector acceleration , double mass){
        super(position, velocity, acceleration, mass);
    }

    public void Draw(Graphics2D g) {
        DrawCircle.DrawFromCenter(g, (int)this.position.x, (int)this.position.y, 50);
    };
}
