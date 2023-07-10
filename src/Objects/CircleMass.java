package Objects;

import Interfaces.Collidable;
import Interfaces.Drawable;
import Tools.DrawCircle;
import Tools.Globals;

import java.awt.*;

public class CircleMass extends Mass implements Drawable, Collidable {
    double radius;
    private Path trail;
    public CircleMass(Vector position , double mass, double radius){
        super(position, mass);
        this.radius = radius;
        this.trail = new Path();
    }

    public CircleMass(Vector position , Vector velocity, double mass, double radius){
        super(position, velocity, mass);
        this.radius = radius;
        this.trail = new Path();
    }

    public CircleMass(Vector position , Vector velocity, Vector acceleration, double mass, double radius){
        super(position, velocity, acceleration, mass);
        this.radius = radius;
        this.trail = new Path();
    }

    public void Draw(Graphics2D g) {
        this.trail.addVertex(this.position);
        DrawCircle.DrawFromCenter(g, (int)this.position.x, (int)this.position.y, (int)(this.radius * 2));

        if(Globals.isVelocityVectorVisible){

            Vector velocityValue = new Vector(0,0);
            velocityValue.Set(this.velocity);
            velocityValue.Normalize();
            velocityValue.Multiply(Globals.vectorDrawScale);

            velocityValue.Draw(g, this.position, Color.GREEN);
        }

        if(Globals.isAccelerationVectorVisible){

            Vector accelerationValue = new Vector(0,0);
            accelerationValue.Set(this.acceleration);
            accelerationValue.Normalize();
            accelerationValue.Multiply(Globals.vectorDrawScale);

            accelerationValue.Draw(g, this.position, Color.GREEN);

            accelerationValue.Draw(g, this.position, Color.RED);
        }


        trail.Draw(g);

    }

    public boolean IsColliding(Mass target) {
        //here you need to define collision mechanics between this class and every other class

        if( target instanceof CircleMass ){
            Vector centers = new Vector(this.position, target.position);
            double distance = centers.Length();
            return distance < (((CircleMass) target).radius + this.radius);
        }

        return false;
    }
}
