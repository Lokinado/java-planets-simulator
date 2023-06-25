package Objects;

import Interfaces.Collidable;
import Interfaces.Drawable;
import Tools.DrawCircle;

import java.awt.*;

public class CircleMass extends Mass implements Drawable, Collidable<CircleMass> {
    double radius;
    public CircleMass(Vector position , double mass, double radius){
        super(position, mass);
        this.radius = radius;
    }

    public CircleMass(Vector position , Vector velocity, double mass, double radius){
        super(position, velocity, mass);
        this.radius = radius;
    }

    public CircleMass(Vector position , Vector velocity, Vector acceleration, double mass, double radius){
        super(position, velocity, acceleration, mass);
        this.radius = radius;
    }

    public void Draw(Graphics2D g) {
        DrawCircle.DrawFromCenter(g, (int)this.position.x, (int)this.position.y, (int)(this.radius * 2));
    };

    public boolean IsColliding(CircleMass target) {
        //System.out.println( new Vector(this.position, target.position).Length() );
        return ( new Vector(this.position, target.position).Length() <= this.radius + target.radius );
    }
}
