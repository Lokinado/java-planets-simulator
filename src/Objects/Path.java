package Objects;

import Interfaces.Drawable;
import Tools.Globals;

import java.awt.*;
import java.util.ArrayList;

public class Path implements Drawable {
    private class Vertex{
        public Vector position;
        private int drawCounter;

        public Vertex(Vector position){
            this.position = new Vector(0,0);
            this.position.Set(position);
            this.drawCounter = 0;
        }
    }

    private ArrayList<Vertex> pathVertices;

    public Path(){
        this.pathVertices = new ArrayList<Vertex>();
    }

    @Override
    public void Draw(Graphics2D g) {
        int loopLength = this.pathVertices.size() - 1;
        for( int i = 0 ; i < loopLength ; i++ ){
            if(Globals.isPathVisible){
                Stroke k = new BasicStroke(1);
                g.setStroke(k);

                Vector trailPart = new Vector( this.pathVertices.get(i).position , this.pathVertices.get(i+1).position );
                trailPart.Draw(g,this.pathVertices.get(i).position, Color.decode("#C0C0C0"));

                k = new BasicStroke(3);
                g.setStroke(k);
            }
            this.pathVertices.get(i).drawCounter += 1;
        }
        if( this.pathVertices.get(0).drawCounter > 800 ){
            this.pathVertices.remove(0);
        }
    }

    public void addVertex(Vector position){
        this.pathVertices.add(new Vertex(position));
    }
}
