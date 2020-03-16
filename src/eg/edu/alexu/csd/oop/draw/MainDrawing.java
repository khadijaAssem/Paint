package eg.edu.alexu.csd.oop.draw;

import javax.swing.JPanel;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.jar.JarFile;

public class MainDrawing extends JPanel implements DrawingEngine {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private Deque<Operation> undo = new LinkedList<Operation>();
    private Deque<Operation> redo = new LinkedList<Operation>();
    private List<Class<? extends Shape>> returnedClasses = new ArrayList<>();

    @Override
    public void refresh(Graphics canvas) {
        for (int i =0;i<shapes.size();i++) {
            shapes.get(i).draw(canvas);
        }
    }
    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println (shape);
        repaint();
        Operation currentOperation = new Operation("Add",shape,null,shapes.size()-1);
        if(undo.size()>=20)
            undo.removeLast();
        undo.push(currentOperation);
        redo.clear();
    }
    @Override
    public void removeShape(Shape shape) {
        int deletedIndex=found(shape);
        shapes.remove(deletedIndex);
        System.out.println("Found");
        Operation currentOperation = new Operation("Remove",shape,null,deletedIndex);
        if(undo.size()>=20)
            undo.removeLast();
        undo.push(currentOperation);
        redo.clear();

    }
    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        int index = found(oldShape);
        System.out.println(index);
        shapes.remove(shapes.get(index));
        shapes.add(index, newShape);
        Operation currentOperation = new Operation("Update",oldShape,newShape,index);
        if(undo.size()>=20)
            undo.removeLast();
        undo.push(currentOperation);
        redo.clear();
    }
    @Override
    public Shape[] getShapes() {
        Shape[] shp = new Shape[shapes.size()];
        for(int i=0;i<shapes.size();i++){
            shp[i] = shapes.get(i);
        }
        return shp;
    }
    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        String jarPath = "C:\\Users\\hp\\Desktop\\College\\OOPAssignments\\Paint\\Paint\\src\\eg\\edu\\alexu\\csd\\oop\\draw";
        installPluginShape(jarPath);
        return returnedClasses;
    }
    String ClassName;
    @Override
    public void installPluginShape(String jarPath){
        jarPath = "C:\\Users\\hp\\Desktop\\College\\OOPAssignments\\Paint\\Paint\\src\\eg\\edu\\alexu\\csd\\oop\\draw";
        File pluginDirectory=new File(jarPath);
        if(!pluginDirectory.exists())pluginDirectory.mkdir();
        File[] files=pluginDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if(files!=null && files.length>0) {
            ArrayList<String> classes = new ArrayList<>();
            ArrayList<URL> urls = new ArrayList<>(files.length);
            for (File file : files) {
                JarFile jar = null;
                try {
                    jar = new JarFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jar.stream().forEach(jarEntry -> {
                    if (jarEntry.getName().endsWith(".class")) {
                        classes.add(jarEntry.getName());
                        ClassName = jarEntry.getName();
                    }
                });
                URL url = null;
                try {
                    url = file.toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                urls.add(url);
            }
            classes.forEach(className -> {
                try {
                    String name = ClassName.replaceAll("/", "\\.").replace(".class", "");
                    Constructor cs = ClassLoader.getSystemClassLoader().loadClass(name).getConstructor();
                    this.returnedClasses.add((Class<? extends Shape>) ClassLoader.getSystemClassLoader().loadClass(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
    @Override
    public void undo() {
        if (undo.isEmpty()) {
            return;
        }
        System.out.println("to undo");
        System.out.println(undo);

        final Operation lastOperation = this.undo.pop();
        if (lastOperation.getOperation() == "Add") {
            int index = lastOperation.getIndex();
            this.shapes.remove(found(lastOperation.getOldShape()));
            redo.push(new Operation("Remove", lastOperation.getOldShape(), null,index));
        } else if (lastOperation.getOperation() == "Remove") {
            this.shapes.add(lastOperation.getIndex(),lastOperation.getOldShape());
            redo.push(new Operation("Add", lastOperation.getOldShape(), null,lastOperation.getIndex()));
        } else if (lastOperation.getOperation() == "Update") {
            int indexOfOldShape = (found(lastOperation.getNewShape()));
            this.shapes.remove(found(lastOperation.getNewShape()));
            this.shapes.add(indexOfOldShape, lastOperation.getOldShape());
            redo.push(new Operation("Update", lastOperation.getNewShape(), lastOperation.getOldShape(), -1));
        }

    }
    @Override
    public void redo() {
        System.out.println("to redo");
        if (redo.isEmpty()) {
            return;
        }
        final Operation lastOperation = this.redo.pop();
        if (lastOperation.getOperation() == "Add") {
            final int index = lastOperation.getIndex();
            this.shapes.add(lastOperation.getOldShape());
            undo.push(new Operation("Remove", lastOperation.getOldShape(), null,index));
        } else if (lastOperation.getOperation() == "Remove") {
            this.shapes.add(lastOperation.getIndex(),lastOperation.getOldShape());
            undo.push(new Operation("Add", lastOperation.getOldShape(), null, lastOperation.getIndex()));
        } else if (lastOperation.getOperation() == "Update") {
            final int indexOfOldShape = this.shapes.indexOf(lastOperation.getNewShape());
            this.shapes.remove(lastOperation.getNewShape());
            this.shapes.add(indexOfOldShape, lastOperation.getOldShape());
            undo.push(new Operation("Update", lastOperation.getNewShape(),lastOperation.getOldShape(), -1));
        }
    }
    @Override
    public void save(String path) {
        if (path.toUpperCase().endsWith("XML"))
            XML.saveXML(path, shapes);
        else if (path.toUpperCase().endsWith("JSON"))
            JSON.saveJson(path,shapes);
    }
    @Override
    public void load(String path) {
        shapes.clear();
        System.out.println("Path " + path);
        if (path.toUpperCase().endsWith("XML"))
            XML.loadXML(path, shapes);
        else if (path.toUpperCase().endsWith("JSON")) {
            try {
                JSON.loadJson(path, shapes);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    private int found(Shape sFor){
        if (found01(sFor)!=-1)
            return found01(sFor);
        if (sFor == null)
            return -1;
        for (int i = 0; i < shapes.size(); i++)
            if (sFor.getProperties().equals(shapes.get(i).getProperties()) && sFor.getPosition().equals(shapes.get(i).getPosition()) && sFor.getFillColor().equals(shapes.get(i).getFillColor()) && sFor.getColor().equals(shapes.get(i).getColor()) && sFor.getClass().getName().equals(shapes.get(i).getClass().getName()))
                return i;
        return -1;
    }
    private int found01(Shape sFor){
        return shapes.indexOf(sFor);
    }
}