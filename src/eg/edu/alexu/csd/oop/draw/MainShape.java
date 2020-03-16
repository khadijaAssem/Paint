package eg.edu.alexu.csd.oop.draw;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public abstract class MainShape implements Shape,Cloneable {
    private java.awt.Point point = null;
    private Color clr;
    private Color FClr;
    private Map<String, Double> prop = new HashMap<>();

    @Override
    public void setPosition(Point position) {
        point = position;
    }

    @Override
    public Point getPosition() {
        return point;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        prop = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        prop.put("ForTests",0.0);
        return prop;
    }

    @Override
    public void setColor(Color color) {
        clr = color;
    }

    @Override
    public Color getColor() {
        return clr;
    }

    @Override
    public void setFillColor(Color color) {
        FClr = color;
    }

    @Override
    public Color getFillColor() {
        return FClr;
    }

    @Override
    public void draw(Graphics canvas) {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
