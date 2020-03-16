package eg.edu.alexu.csd.oop.draw;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class ITriangle extends MainShape {
    @Override
    public void draw(Graphics canvas) {
        Point point = getPosition();
        Map<String, Double> pro = getProperties();
        double x2 = pro.get("X2");
        double x3 = pro.get("X3");
        double y2 = pro.get("Y2");
        double y3 = pro.get("Y3");
        double dx = pro.get("dx");
        double dy = pro.get("dy");
        double dsx = pro.get("dsx");
        double dsy = pro.get("dsy");
        double width = pro.get("SWidth");
        
        int[] x = new int[3];
        x[0] = point.x+(int)dx;
        x[1] = (int)x2+(int)dx;
        x[2] = (int)x3+(int)dx+(int)dsx;
        int[] y = new int[3];
        y[0] = point.y+(int)dy;
        y[1] = (int) y2+(int)dy;
        y[2] = (int) y3+(int)dy+(int)dsy;
        
        Graphics2D g =(Graphics2D) canvas;
        canvas.setColor(getColor());
        g.setStroke(new BasicStroke((int)width));
        g.drawPolygon(x, y, 3);
        double Obacity = pro.get("OpacityV");
        Color c = new Color(getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), (int) Obacity);
        g.setColor(c);
        g.fillPolygon(x, y, 3);
        
//        Graphics2D g =(Graphics2D) canvas;
//        canvas.setColor(getColor());
//        g.setStroke(new BasicStroke((int)width));
//        Rectangle2D.Double drawShape = new Rectangle2D.Double(point.x,point.y, len, len);
//        g.draw(drawShape);
//        g.setColor(getFillColor());
//        g.fill(drawShape);
        
//        Polygon2D.Double drawShape = new Rectangle2D.Double(point.x,point.y, len, len);
//        g.draw(drawShape);
//        
//        g.fill(drawShape);
//        canvas.drawPolygon(x,y,3);
    }
}
//public void draw(Graphics canvas) {
//	int x2 = getProperties().get("secondX").intValue();
//	int y2 = getProperties().get("secondY").intValue();
//	int x3 = getProperties().get("thirdX").intValue();
//	int y3 = getProperties().get("thirdY").intValue();
//	Graphics2D g = (Graphics2D) canvas;
//	g.setStroke(new BasicStroke(2));
//	int[] xPoints = {(int) super.getPosition().getX(), x2, x3 };
//	int[] yPoints = {(int) super.getPosition().getY(), y2, y3 };
//	g.setColor(getColor());
//	g.drawPolygon(xPoints, yPoints, 3);
//	if (getFillColor() == null) {
//	    g.setColor(new Color(0, 0, 0, 1));
//	    g.fillPolygon(xPoints, yPoints, 3);
//	} else {
//	    g.setColor(getFillColor());
//	    g.fillPolygon(xPoints, yPoints, 3);
//	}
//    }