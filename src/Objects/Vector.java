package Objects;
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

    public void Add(Vector lhs){
        this.x += lhs.x;
        this.y += lhs.y;
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
}
