package eg.edu.alexu.csd.oop.draw;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Map;

public class ICircle extends MainShape {
    @Override
    public void draw(Graphics canvas) {
        Point point = getPosition();
        Map<String, Double> pro = getProperties();
         pro.get("Radius");
        double dx = pro.get("dx");
        double dy = pro.get("dy");
        double dsx = pro.get("dsx");
        double dsy = pro.get("dsy");
        double rad = Math.sqrt(Math.pow((getPosition().x - dsx), 2) + Math.pow((getPosition().y - dsy), 2));
        double width = pro.get("SWidth");
        double Obacity = pro.get("OpacityV");
        Graphics2D g =(Graphics2D) canvas;
        canvas.setColor(getColor());
        g.setStroke(new BasicStroke((int)width));
        Ellipse2D.Double drawShape = new Ellipse2D.Double((int)(point.x-rad+dx), (int)(point.y-rad+dy),(int) ((rad)*2),(int) ((rad)*2));
        g.draw(drawShape);
        Color c = new Color(getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), (int) Obacity);
        g.setColor(c);
        g.fill(drawShape);
//        BufferStrategy bs = getBufferStrategy();
//        if (bs == null) {
//            createBufferStrategy(BUFFERS_COUNT);
//            return;
//        }
//
//
//        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics().create();
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        clear(g2d, 0);

       

 
    }
}