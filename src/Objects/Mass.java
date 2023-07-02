package Objects;

import Interfaces.Collidable;
import Interfaces.Drawable;

import java.awt.*;

public abstract class Mass {

    public double mass;
    public Vector position;
    public Vector velocity;
    public Vector acceleration;

    public Mass(Vector position , double mass){
        this.mass = mass;
        this.position = position;
        this.velocity = new Vector(0,0);
        this.acceleration = new Vector(0,0);
    }

    public Mass(Vector position , Vector velocity, double mass){
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector(0,0);
    }

    public Mass(Vector position , Vector velocity, Vector acceleration , double mass){
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
}
