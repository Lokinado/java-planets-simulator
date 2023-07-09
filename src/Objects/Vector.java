package Objects;
import Tools.Globals;

import java.awt.*;
import java.lang.Math;
import java.security.PublicKey;

public class Vector{
    public double x;
    public double y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector(Vector origin, Vector target){
        this.x = target.x - origin.x;
        this.y = target.y - origin.y;
    }

    public void Add(Vector rhs){
        this.x += rhs.x;
        this.y += rhs.y;
    }

    public void Multiply(double factor){
        this.x *= factor;
        this.y *= factor;
    }

    public double Length(){
        return Math.sqrt( this.x * this.x + this.y * this.y );
    }

    public void Normalize(){
        double length = this.Length();
        this.x /= length;
        this.y /= length;
    }

    public double Product( Vector rhs ){
        return this.x*rhs.x + this.y*rhs.y;
    }

    public void Set(Vector rhs){
        this.x = rhs.x;
        this.y = rhs.y;
    }

    public void Draw(Graphics2D g,Vector position, Color c){
        g.setColor(c);
        Vector endPoint = new Vector(this.x,this.y);
        endPoint.Normalize();
        endPoint.Multiply(Globals.vectorDrawScale);
        endPoint.Add(position);
        g.drawLine((int)position.x,(int)position.y,(int)endPoint.x,(int)endPoint.y);
        g.setColor(Color.BLACK);
    }
}
