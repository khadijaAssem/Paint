package eg.edu.alexu.csd.oop.draw;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class XML{
    public static void saveXML(String path, ArrayList shapes) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(shapes.size());
            Iterator<?> iterator = shapes.iterator();
            while (iterator.hasNext())
                encoder.writeObject(iterator.next());
            encoder.writeObject(shapes.get(0));
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadXML(String path, ArrayList shapes) {
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            XMLDecoder decoder = new XMLDecoder(fis);
            int size = (int) decoder.readObject();
            for(int i=0;i<size;i++){
                shapes.add(decoder.readObject());
            }
            decoder.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}