package eg.edu.alexu.csd.oop.draw;
import java.awt.*;
import java.util.Map;
import java.awt.geom.Rectangle2D;

public class ISquare extends MainShape {
    @Override
    public void draw(Graphics canvas) {
        Point point = getPosition();
        Map<String, Double> pro = getProperties();
        double dx = pro.get("dx");
        double dy = pro.get("dy");
        double dsx = pro.get("dsx");
        double width = pro.get("SWidth");
        double len = Math.abs((double) getPosition().x - dsx);

        Graphics2D g =(Graphics2D) canvas;
        canvas.setColor(getColor());
        g.setStroke(new BasicStroke((int)width));
        Rectangle2D.Double drawShape = new Rectangle2D.Double(point.x+dx,point.y+dy, len, len);
        g.draw(drawShape);
        double Obacity = pro.get("OpacityV");
        Color c = new Color(getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), (int) Obacity);
        g.setColor(c);
        g.fill(drawShape);
        
    }
}