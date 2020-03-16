package eg.edu.alexu.csd.oop.draw;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class DrawingArea extends JPanel {
    private int OpacityV=255;
    private int sliderV=2;
    private Shape shape = null;
    private Shape OShape = null;
    private Color cFill,cFrame;
    private MainDrawing drawing = new MainDrawing();
    private int num=1;
    private MyMouseListener ml = new MyMouseListener();
    private String FName = "" ;
    private Map<String,Double> pro = new HashMap<>();
    private JToggleButton SButton = new JToggleButton();
    private Map<String,Shape> Items = new HashMap<>();
    private JComboBox ComboBox;
    private String packageName = "eg.edu.alexu.csd.oop.draw.";
    private JFrame frame1 = new JFrame("Test");
    private HashMap<String,Double> temp = new HashMap<>();

    public DrawingArea() {
        addMouseListener(ml);
        addMouseMotionListener(ml);
        setBackground(Color.WHITE);
        cFill = Color.BLACK;
        cFrame = Color.WHITE;
        setBounds(150, 100, 1000, 500);
    }

    void setConboBox(JComboBox SComboBox){
        ComboBox = SComboBox;
    }

    void setButton(JToggleButton btn){
        SButton = btn;
        FName = btn.getName();
    }

    void setJButton(JButton btn){
        FName = btn.getName();
    }

    void setcFill(JButton btn) {
        Color color = JColorChooser.showDialog(null,"Choose a color",cFill);
        if (color != null) {
            cFill= color;
            btn.setBackground(color);
        }
    }

    void setcFrame(JButton btn) {
        Color color = JColorChooser.showDialog(null,"Choose a color",cFrame);
        if (color != null) {
            cFrame= color;
            btn.setBackground(color);
        }
    }

    public void setOpacityV(int slider){
        OpacityV = slider;
    }

    public void setsliderV(int slider){
        sliderV = slider;
    }

    void Refresh() {
        drawing.refresh(getGraphics());
        repaint();
    }

    private void initFigure(Point startPoint){
        if (!SButton.isSelected()||FName.equals("Triangle"))
            return;
        try {
            Class exampleClass = Class.forName(packageName.concat("I".concat(FName)));
            shape = (MainShape)exampleClass.getDeclaredConstructor().newInstance();
            shape.setPosition(startPoint);
            shape.setColor(cFrame);
            shape.setFillColor(cFill);
            pro = new HashMap<>();
        }
        catch (ClassNotFoundException E) {
            System.out.println("Error in loading new class in initFigure");
        } catch (Exception E) {
            System.out.println("Error in loading new class in initFigure");;
        }
    }

    private void drawingFigure(Point endPoint){
        if (FName.equals("Triangle"))
            return;
        else{
            pro.put("dsx",(double)endPoint.x);
            pro.put("dsy",(double)endPoint.y);
        }
        pro.put("dx",0.0);
        pro.put("dy",0.0);
        pro.put("SWidth", (double) sliderV);
        pro.put("OpacityV", (double) OpacityV);
        shape.setProperties(pro);
    }

    private void addingFigure(){
        try{
            if (FName.equals("Circle")||FName.equals("Triangle")||FName.equals("Ellipse")||FName.equals("Line")||FName.equals("Square")||FName.equals("Rectangle"))
                pro.get("dsx");
        }
        catch (Exception e){
            System.out.println("Drag To Draw from addingFigure");
            return;
        }
        try {
            drawing.addShape((Shape) shape.clone());
        } catch (CloneNotSupportedException e) {
            System.out.println("Failed to clone shape in adding figure");
        }
        repaint();
    }

    void Copy(){
        Object value = ComboBox.getItemAt(ComboBox.getSelectedIndex());
        pro = new HashMap<>();
        if ((value instanceof String)) {
            try {
                shape = (Shape) Items.get(value).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public void Edit(){
        Object value = ComboBox.getItemAt(ComboBox.getSelectedIndex());
        if ((value instanceof String))
            shape = Items.get(value);
        try {
            OShape = (Shape) shape.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        pro = shape.getProperties();
        shape.setColor(cFrame);
        shape.setFillColor(cFill);
        pro.put("SWidth",(double)sliderV);
        pro.put("OpacityV",(double)OpacityV);
        drawing.updateShape(OShape,shape);
        repaint();
    }

    private void Resize(Point e){
        Object value = ComboBox.getItemAt(ComboBox.getSelectedIndex());
        if ((value instanceof String))
            shape = Items.get(value);
        pro= new HashMap<>();
        pro.putAll(shape.getProperties());
        pro.put("dsx", (double) e.x);
        pro.put("dsy", (double) e.y);
        shape.setProperties(pro);
        repaint();
    }

    public void Delete(){
        Object value = ComboBox.getItemAt(ComboBox.getSelectedIndex());
        if ((value instanceof String))
            shape = Items.get(value);
        drawing.removeShape(shape);
        Items.remove(value);
        ComboBox.removeItem(value);
        drawing.refresh(getGraphics());
        shape = null;
        num--;
        repaint();
    }

    void Save(JFrame frame,String item) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("item","xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            System.out.print("To save in path : ");
            System.out.println(path);
            drawing.save(path);
        }
    }

    void Load(JFrame frame,String item) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("item","xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            System.out.println(path);
            drawing.load(path);
            System.out.println("to load");
        }
        drawing.refresh(getGraphics());
    }

    void newFile() {
        int x =drawing.getShapes().length;
        for (int i =0 ;i<x;i++) {
            drawing.removeShape(drawing.getShapes()[0]);


        }
        drawing.refresh(getGraphics());
        repaint();

    }

    public void Undo() {
        drawing.undo();
        drawing.refresh(getGraphics());
        shape = null;
        paintComponent(getGraphics());
        repaint();
    }

    public void Redo() {
        drawing.redo();
        drawing.refresh(getGraphics());
        repaint();
    }

    private void Move(Point e){
        Object value = ComboBox.getItemAt(ComboBox.getSelectedIndex());
        if ((value instanceof String))
            shape = Items.get(value);
        if (shape.getClass().getSimpleName().equals("ILine")||shape.getClass().getSimpleName().equals("ICircle")||shape.getClass().getSimpleName().equals("ISquare")||shape.getClass().getSimpleName().equals("IEllipse")||shape.getClass().getSimpleName().equals("IRectangle")||shape.getClass().getSimpleName().equals("ITriamgle")){
            pro = new HashMap<>();
            pro.putAll(shape.getProperties());
            double dx = (e.x - shape.getPosition().x);
            double dy = (e.y - shape.getPosition().y);
            pro.put("dx", dx);
            pro.put("dy", dy);
            shape.setProperties(pro);
            repaint();
        }
//        else {
//            DialogBox();

//        }
    }

    public void addPlugin(JFrame frame){
        FName = "AddPlugin";
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jar", "jar");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            drawing.installPluginShape(path);
            FName = drawing.getSupportedShapes().get(0).getName();
            Constructor cs = null;
            try {
                cs = ClassLoader.getSystemClassLoader().loadClass(FName).getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                shape = (Shape) cs.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void DialogBox(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        JPanel inputpanel = new JPanel();
        temp.putAll(shape.getProperties());
        Set<String> setOfKeys = temp.keySet();
        ArrayList<JTextField> inputs = new ArrayList<>();
        for(int i =0;i<setOfKeys.size();i++){
            inputs.add( new JTextField((String) setOfKeys.toArray()[i],20));
            inputpanel.add(inputs.get(i));
            inputs.get(i).requestFocus();
        }
        JButton button = new JButton("Draw");

        inputpanel.add(button);
        ActionListener getpro = actionEvent ->
                getProp(temp,setOfKeys,inputs);
        shape.setProperties(temp);
        shape.setFillColor(cFill);
        shape.setColor(cFrame);
        inputpanel.setLayout(new FlowLayout());
        button.addActionListener(getpro);
        panel.add(inputpanel);
        frame1.getContentPane().add(BorderLayout.CENTER, panel);
        frame1.pack();
        frame1.setLocationByPlatform(true);
        frame1.setVisible(true);
        frame1.setResizable(false);
    }

    private void getProp(HashMap<String,Double> temp,Set<String> setOfKeys,ArrayList<JTextField> inputs ){
        for(int i=0;i<setOfKeys.size();i++)
            temp.put((String) setOfKeys.toArray()[i],(double) Integer.parseInt(inputs.get(i).getText()));
        frame1.dispose();
        addingFigure();
    }

    class MyMouseListener extends MouseInputAdapter {
        private Point startPoint;
        private int i =0;
        private boolean draggedFlag = false;
        private boolean pressedFlag = false;

        public void mousePressed(MouseEvent e) {
            pressedFlag = true;
            if (FName.equals("Triangle")){
                if (i==0) {
                    try {
                        System.out.println(packageName.concat("I".concat(FName)));
                        Class exampleClass = Class.forName(packageName.concat("I".concat(FName)));
                        shape = (MainShape)exampleClass.getDeclaredConstructor().newInstance();
                        shape.setPosition(e.getPoint());
                        pro = new HashMap<>();
                        pro.put("dx",0.0);
                        pro.put("dy",0.0);
                        pro.put("dsx",0.0);
                        pro.put("dsy",0.0);
                        pro.put("SWidth",(double)sliderV);
                        pro.put("OpacityV",(double) OpacityV);
                        shape.setColor(cFrame);
                        shape.setFillColor(cFill);
                    }
                    catch (ClassNotFoundException E) {
                        System.out.println("Class not found in Triangle");

                    } catch (Exception E) {
                        System.out.println("Error in loading class Triangle");
                    }
                }else if(i<3) {
                    if (i==1) {
                        pro.put("X2",(double)e.getX());
                        pro.put("Y2",(double)e.getY());
                    }else if (i==2) {
                        pro.put("X3",(double)e.getX());
                        pro.put("Y3",(double)e.getY());
                        shape.setProperties(pro);
                        shape.draw(getGraphics());
                        addingFigure();
                        i=-1;
                    }
                }
                i++;
            }
            else if (FName.equals("Move")||FName.equals("Resize")){
                Object value = ComboBox.getItemAt(ComboBox.getSelectedIndex());
                if ((value instanceof String)) {
                    try {
                        OShape = (Shape) Items.get(value).clone();
                    } catch (CloneNotSupportedException ex) {
                        System.out.println("Can't clone in Move or resize");
                    }
                }
                if (FName.equals("Resize")&&OShape.getClass().getSimpleName().equals("ITriangle")){

                }
                if (FName.equals("Move"))
                    Move(e.getPoint());
            }
            else if (FName.equals("Paste")){
                double dx = (e.getX() - shape.getPosition().x);
                double dy = (e.getY() - shape.getPosition().y);
//                pro.put("dx", dx);
//                pro.put("dy", dy);
//                shape.setPosition(e.getPoint());
                Move(e.getPoint());
                addingFigure();
                repaint();
            }
            else if (!(FName.equals("Circle")||FName.equals("Triangle")||FName.equals("Ellipse")||FName.equals("Line")||FName.equals("Square")||FName.equals("Rectangle"))) {
                System.out.println(FName);
                shape.setPosition(e.getPoint());
                DialogBox();
            }
            else {
                startPoint = e.getPoint();
                initFigure(startPoint);
            }
        }

        public void mouseDragged(MouseEvent e) {
            draggedFlag = true;
            if (FName.equals("Move")){
                Move(e.getPoint());
                return;
            }
            else if (FName.equals("Resize")){
                Resize(e.getPoint());
                return;
            }
            drawingFigure(e.getPoint());
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            if (FName.equals("Triangle")||FName.equals("Move")||FName.equals("Resize")||FName.equals("Delete")) {
                if (FName.equals("Move")||FName.equals("Resize")){
                    shape.setProperties(pro);
                    drawing.updateShape(OShape,shape);
                    repaint();
                }
                return;
            }
            if(draggedFlag&&pressedFlag) {
                draggedFlag = false;
                pressedFlag = false;
                addingFigure();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        if(FName.equals("AddPlugin"))
            return;
        super.paintComponent(g);
        g.setColor( Color.BLACK );
        drawing.refresh(g);
        if (shape != null ) {
            Graphics2D g2d = (Graphics2D)g;
            shape.draw(g2d);
        }
        {
            Items.clear();
            num = 0;
            ComboBox.removeAllItems();
            for (int i=0;i<drawing.getShapes().length;i++){
                try {
                    String className = drawing.getShapes()[i].getClass().getSimpleName();
                    Items.put(className+ num, (Shape) drawing.getShapes()[i].clone());
                    ComboBox.addItem(className.concat(Integer.toString(num++)));
                    ComboBox.setSelectedIndex(Items.size()-1);
                } catch (CloneNotSupportedException e) {
                    System.out.println("Can't Clone in paintComponent");
                }
            }
        }
    }
}