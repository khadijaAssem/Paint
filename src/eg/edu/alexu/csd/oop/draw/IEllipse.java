package eg.edu.alexu.csd.oop.draw;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
public class IEllipse extends MainShape{
    @Override
    public void draw(Graphics canvas) {
        Point point = getPosition();
        Map<String, Double> pro = getProperties();
        double dx = pro.get("dx");
        double dy = pro.get("dy");
        double dsx = pro.get("dsx");
        double dsy = pro.get("dsy");
        double width = pro.get("SWidth");
        double len = Math.abs((double)getPosition().x-dsx);
        double wid = Math.abs((double)getPosition().y-dsy);
        Graphics2D g =(Graphics2D) canvas;
        canvas.setColor(getColor());
        g.setStroke(new BasicStroke((int)width));
        Ellipse2D.Double drawShape = new Ellipse2D.Double(point.x+(int)dx+(int)width*2,point.y+(int)dy+(int)width*2,(int)len+(int)width*2,(int)wid+(int)width*2);
        g.draw(drawShape);
        double Obacity = pro.get("OpacityV");
        Color c = new Color(getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), (int) Obacity);
        g.setColor(c);
        g.fill(drawShape);

        
//        double alpha = 170; // 50% transparent
//        Color myColour1 = new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), (int)alpha);
//        g2.fillOval(point.x+(int)dx+(int)width*2,point.y+(int)dy+(int)width*2,(int)len+(int)width,(int)wid+(int)width);
//        Color myColour = new Color(getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), (int)alpha);
 //        g.fillOval(point.x+(int)dx,point.y+(int)dy,(int)len,(int)wid);
//        canvas.drawOval(point.x+(int)dx,point.y+(int)dy,(int)len,(int)wid);
    }
}