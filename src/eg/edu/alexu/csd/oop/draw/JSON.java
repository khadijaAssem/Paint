package eg.edu.alexu.csd.oop.draw;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;


public class JSON {
     public static void saveJson(String path, ArrayList<Shape> shapes) {
        File file = new File(path);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write("{");
            int j = 1;
            for (Shape currentShape : shapes) {
                Point p =currentShape.getPosition();
                Map<String, Double> map = (Map<String, Double>) currentShape.getProperties();
                try {
                    fr.append("\n");
                    fr.write("\"" + currentShape.getClass().getSimpleName() + "\"" + ":{");
                    fr.append("\n");
                    fr.write("\"id\"" + ":" + "\"" + currentShape.getClass().getName() + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"PX\"" + ":" + "\"" + currentShape.getPosition().x + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"PY\"" + ":" + "\"" + currentShape.getPosition().y + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"CR\"" + ":" + "\"" + currentShape.getColor().getRed() + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"CG\"" + ":" + "\"" + currentShape.getColor().getGreen() + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"CB\"" + ":" + "\"" + currentShape.getColor().getBlue() + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"CFR\"" + ":" + "\"" + currentShape.getFillColor().getRed() + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"CFG\"" + ":" + "\"" + currentShape.getFillColor().getGreen() + "\"" + ",");
                    fr.append("\n");
                    fr.write("\"CFB\"" + ":" + "\"" + currentShape.getFillColor().getBlue() + "\"" + ",");
                    fr.append("\n");
                }
                catch (Exception e){
                    System.out.println("Null");
                }
                int i = 1;
                if (map != null) {
                    for (Entry<String, Double> entry : ((java.util.Map<String, Double>) map).entrySet()) {
                        if (i < ((java.util.Map<String, Double>) map).size()) {
                            fr.write("\"" + entry.getKey() + "\"" + ": ");
                            fr.write(entry.getValue() + ",");
                        } else {
                            fr.write("\"" + entry.getKey() + "\"" + ": ");
                            fr.write("" + entry.getValue() + " ");
                        }
                        i++;
                        fr.append("\n");
                    }
                }
                if (j < shapes.size()) {
                    fr.write("},");
                } else {
                    fr.write("}");
                }
                j++;
            }
            fr.append("\n");
            fr.write("}");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            fr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void loadJson(String path, ArrayList<Shape> shapes)throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
        InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        shapes.clear();
        System.out.println("in load main");
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int x1,x2,y1,y2,z1,z2;
        while ((st = br.readLine()) != null) {
            System.out.println("while loop");
            if (st.contains(":{")) {
                System.out.println("if stat");
                st = br.readLine();
                String[] id = st.split(":");
                String className = id[1].substring(1, id[1].length() - 2);
                Class<?> exampleClass = Class.forName(className);
                Shape ob = (Shape)exampleClass.getDeclaredConstructor().newInstance();
                st = br.readLine();
                Point cop= new Point();
                try {
                    id = st.split(":");
                    cop.x = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    st = br.readLine();
                    id = st.split(":");
                    cop.y = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    st = br.readLine();
                    id = st.split(":");
                    x1 = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    st = br.readLine();
                    id = st.split(":");
                    y1 = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    st = br.readLine();
                    id = st.split(":");
                    z1 = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    st = br.readLine();
                    id = st.split(":");
                    x2 = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    st = br.readLine();
                    id = st.split(":");
                    y2 = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    st = br.readLine();
                    id = st.split(":");
                    z2 = Integer.parseInt(id[1].substring(1, id[1].length() - 2));
                    ob.setPosition(cop);
                    ob.setColor(new Color(x1, y1, z1));
                    ob.setFillColor(new Color(x2, y2, z2));
                }
                catch (Exception e){
                    System.out.println("Error Json");
                }
                Map<String, Double> properties = new HashMap<>();
                while ((st = br.readLine()) != null && !st.contains("}")) {
                    String[] propertiesArr = st.split(":");
                    properties.put(propertiesArr[0].substring(1, propertiesArr[0].length() - 1),
                            Double.parseDouble(propertiesArr[1].substring(1, propertiesArr[1].length() - 1)));
                }
//              if (className.contains("RoundRectangle")) {
//                  for(Class<? extends Shape> classes : returnedClasses) {
//                      if(classes.getName().contains("RoundRectangle")){
//                          Constructor<?> ctor = classes.getConstructor();
//                          Shape ob = (Shape) ctor.newInstance();
//                          ob.setProperties(properties);
//                          shapes.add(ob);
//                      }
//                  }
//              } else {
                ob.setProperties(properties);
                try {
                    shapes.add((Shape) ob.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
//              }
            }
        }
    }

}