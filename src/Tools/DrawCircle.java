package Tools;
import java.awt.*;

public class DrawCircle {

    private DrawCircle() {}

    public static void DrawFromCenter(Graphics g, int x, int y, int diameter){
        g.drawOval( x - diameter/2, y - diameter/2,  diameter, diameter);
    }
}
