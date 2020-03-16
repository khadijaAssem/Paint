package eg.edu.alexu.csd.oop.draw;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.io.File;
import javax.swing.ImageIcon;
public class Paint extends DrawingArea {
	private JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paint window = new Paint();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Paint() {
		initialize();
	}
	private void initialize() {
		Image backgroundImage;
//		backgroundImage = ImageIO.read(new File(fileName));
		frame = new JFrame("Paint");
		frame.setBounds(100, 100, 1182, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		DrawingArea d = new DrawingArea();
		d.setLocation(150, 107);
		frame.getContentPane().add(d);
		Canvas canvas = new Canvas();
		canvas.setBounds(147, 85, 1144, 538);
		frame.getContentPane().add(canvas);
		ButtonGroup buttonGroup = new ButtonGroup();

		JToggleButton Circle = new JToggleButton();
		Circle.setIcon(new ImageIcon("items//circle.png"));
		Circle.setName("Circle");
		Circle.setBounds(150, 42, 40, 40);
		frame.getContentPane().add(Circle);
		Circle.setToolTipText("Circle");

		JToggleButton Line = new JToggleButton();
		Line.setIcon(new ImageIcon("items/line.png"));
		Line.setName("Line");
		Line.setBounds(200, 42, 40, 40);
		frame.getContentPane().add(Line);
		Line.setToolTipText("Line");

		JToggleButton Square = new JToggleButton();
		Square.setIcon(new ImageIcon("items/square.png"));
		Square.setName("Square");
		Square.setBounds(250, 42, 40, 40);
		frame.getContentPane().add(Square);
		Square.setToolTipText("Square");

		JToggleButton Oval = new JToggleButton();
		Oval.setIcon(new ImageIcon("items/ellipse.png"));
		Oval.setName("Ellipse");
		Oval.setBounds(300, 42, 40, 40);
		frame.getContentPane().add(Oval);
		Oval.setToolTipText("Ellipse");

		JToggleButton Rectangle = new JToggleButton();
		Rectangle.setIcon(new ImageIcon("items/rectangle.png"));
		Rectangle.setName("Rectangle");
		Rectangle.setBounds(350, 42, 40, 40);
		frame.getContentPane().add(Rectangle);
		Rectangle.setToolTipText("Rectangle");

		JToggleButton Triangle = new JToggleButton();
		Triangle.setIcon(new ImageIcon("items/triangle.png"));
		Triangle.setName("Triangle");
		Triangle.setBounds(400, 42, 40, 40);
		frame.getContentPane().add(Triangle);
		Triangle.setToolTipText("Triangle");

		JToggleButton AddPlugin = new JToggleButton();
		AddPlugin.setIcon(new ImageIcon("items/plug.png"));
		AddPlugin.setName("Plug");
		AddPlugin.setBounds(450, 42, 40, 40);
		frame.getContentPane().add(AddPlugin);
		AddPlugin.setToolTipText("Plug");

		JToggleButton Refresh = new JToggleButton();
		Refresh.setIcon(new ImageIcon("items/refresh.png"));
		Refresh.setName("Refresh");
		Refresh.setBounds(1095, 33, 40, 40);
		frame.getContentPane().add(Refresh);
		Refresh.setToolTipText("Refresh");

		JToggleButton Move = new JToggleButton();
		Move.setBounds(70, 200, 40, 40);
		Move.setIcon(new ImageIcon("items/move.png"));
		Move.setName("Move");
		frame.getContentPane().add(Move);
		Move.setToolTipText("Move");

		JToggleButton Resize = new JToggleButton();
		Resize.setIcon(new ImageIcon("items/resize.png"));
		Resize.setName("Resize");
		Resize.setBounds(20, 200, 40, 40);
		frame.getContentPane().add(Resize);
		Resize.setToolTipText("Resize");


		JButton Edit = new JButton();
		Edit.setIcon(new ImageIcon("items/edit.png"));
		Edit.setName("Edit");
		Edit.setBounds(70, 250, 40, 40);
		frame.getContentPane().add(Edit);
		Edit.setToolTipText("Edit");

		JButton Delete = new JButton();
		Delete.setIcon(new ImageIcon("items/delete.png"));
		Delete.setName("Delete");
		Delete.setBounds(20, 250, 40, 40);
		frame.getContentPane().add(Delete);
		Delete.setToolTipText("Delete");

		JButton Copy = new JButton();
		Copy.setIcon(new ImageIcon("items/copy.png"));
		Copy.setName("Copy");
		Copy.setBounds(20, 404, 40, 40);
		frame.getContentPane().add(Copy);
		Copy.setToolTipText("Copy");

		JButton Paste = new JButton();
		Paste.setBounds(70, 404, 40, 40);
		Paste.setIcon(new ImageIcon("items/paste.png"));
		Paste.setName("Paste");
		frame.getContentPane().add(Paste);
		Paste.setToolTipText("Paste");

		JButton FillColor = new JButton("FillColor");
		FillColor.setName("Fill Color");
		FillColor.setBounds(589, 21, 116, 21);
		frame.getContentPane().add(FillColor);
		FillColor.setToolTipText("Choose color to fill with");

		JButton Redo = new JButton();
		Redo.setIcon(new ImageIcon("items/redo.png"));
		Redo.setName("Redo");
		Redo.setBounds(70, 124, 40, 40);
		frame.getContentPane().add(Redo);
		Redo.setToolTipText("Redo");

		JButton Undo = new JButton();
		Undo.setIcon(new ImageIcon("items/undo.png"));
		Undo.setName("Undo");
		Undo.setBounds(20, 124, 40, 40);
		frame.getContentPane().add(Undo);
		Undo.setToolTipText("Undo");

		JButton FrameColor = new JButton("FrameColor");
		FrameColor.setName("Frame Color");
		FrameColor.setBounds(589, 51, 116, 21);
		frame.getContentPane().add(FrameColor);
		FrameColor.setToolTipText("choose color for frame");

		JComboBox comboBox = new JComboBox();
		d.setConboBox(comboBox);

		comboBox.setBounds(24, 323, 98, 21);
		frame.getContentPane().add(comboBox);

		JButton btnShowFillCol = new JButton();
		btnShowFillCol.setBounds(715, 21, 18, 21);
		btnShowFillCol.setBackground(Color.black);
		frame.getContentPane().add(btnShowFillCol);

		JButton btnShowCol = new JButton();
		btnShowCol.setBounds(715, 51, 18, 21);
		btnShowCol.setBackground(Color.white);

		frame.getContentPane().add(btnShowCol);

		ActionListener listener = actionEvent ->
				d.setButton((JToggleButton)actionEvent.getSource());
		ActionListener Mo = actionEvent ->
				d.setButton(Move);
		ActionListener Re = actionEvent ->
				d.setButton(Resize);
		ActionListener Cfi = actionEvent ->
				d.setcFill(btnShowFillCol);
		ActionListener Cfr = actionEvent ->
				d.setcFrame(btnShowCol);
		ActionListener Ed = actionEvent ->
				d.Edit();
		ActionListener De = actionEvent ->
				d.Delete();
		ActionListener Co = actionEvent ->
				d.Copy();
		ActionListener Pa = actionEvent ->
				d.setJButton(Paste);
		ActionListener Un = actionEvent ->
				d.Undo();
		ActionListener Red = actionEvent ->
				d.Redo();
		ActionListener rf = actionEvent ->
				d.Refresh();
		ActionListener Pl = actionEvent ->
				d.addPlugin(frame);

		AddPlugin.addActionListener(Pl);
		Refresh.addActionListener(rf);
		Undo.addActionListener(Un);
		Redo.addActionListener(Red);
		Paste.addActionListener(Pa);
		Copy.addActionListener(Co);
		Circle.addActionListener(listener);
		Line.addActionListener(listener);
		Triangle.addActionListener(listener);
		Rectangle.addActionListener(listener);
		Oval.addActionListener(listener);
		Square.addActionListener(listener);
		Move.addActionListener(Mo);
		Resize.addActionListener(Re);
		FillColor.addActionListener(Cfi);
		FrameColor.addActionListener(Cfr);
		btnShowFillCol.addActionListener(Cfi);
		btnShowCol.addActionListener(Cfr);
		Delete.addActionListener(De);
		Edit.addActionListener(Ed);
		buttonGroup.add(Paste);
		buttonGroup.add(Copy);
		buttonGroup.add(Undo);
		buttonGroup.add(Redo);
		buttonGroup.add(Circle);
		buttonGroup.add(Line);
		buttonGroup.add(Delete);
		buttonGroup.add(Triangle);
		buttonGroup.add(Rectangle);
		buttonGroup.add(Oval);
		buttonGroup.add(Square);
		buttonGroup.add(Move);
		buttonGroup.add(Resize);
		buttonGroup.add(Edit);

		JSlider slider = new JSlider();
		slider.setBounds(749, 43, 154, 22);
		frame.getContentPane().add(slider);
		slider.setMajorTickSpacing(2);
		slider.setMinorTickSpacing(2);
		slider.setMinimum(2);
		slider.setMaximum(12);
		slider.setValue(0);
		slider.setPaintTicks(true);
		ChangeListener Sl = ChangeListener ->
				d.setsliderV(slider.getValue());
		slider.addChangeListener(Sl);
		JSlider sliderO = new JSlider();
		sliderO.setBounds(913, 43, 154, 22);
		frame.getContentPane().add(sliderO);
		sliderO.setMajorTickSpacing(42);
		sliderO.setMinorTickSpacing(42);
		sliderO.setMinimum(0);
		sliderO.setMaximum(255);
		sliderO.setValue(255);
		sliderO.setPaintTicks(true);
		ChangeListener Op = ChangeListener ->
				d.setOpacityV(sliderO.getValue());
		sliderO.addChangeListener(Op);

		JLabel lblStroke = new JLabel("Stroke width");
		lblStroke.setFont(new Font("Castellar", lblStroke.getFont().getStyle(), lblStroke.getFont().getSize() + 2));
		lblStroke.setBounds(753, 20, 136, 13);
		frame.getContentPane().add(lblStroke);

		JLabel lblOpacity = new JLabel("Opacity");
		lblOpacity.setFont(new Font("Castellar", lblOpacity.getFont().getStyle(), lblOpacity.getFont().getSize() + 2));
		lblOpacity.setBounds(919, 20, 136, 13);
		frame.getContentPane().add(lblOpacity);

		JLabel lblNewLabel = new JLabel("Shapes");
		lblNewLabel.setFont(new Font("Constantia", lblNewLabel.getFont().getStyle(), lblNewLabel.getFont().getSize() + 10));
		lblNewLabel.setBounds(179, 18, 98, 24);
		frame.getContentPane().add(lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 10, 50, 22);
		frame.getContentPane().add(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);

		JMenuItem newfile = new JMenuItem("New");
		file.add(newfile);

		JMenu save = new JMenu("Save");
		file.add(save);
		JMenuItem xmlsave = new JMenuItem("XML");
		save.add(xmlsave);
		JMenuItem jsonsave = new JMenuItem("JSON");
		save.add(jsonsave);

		JMenu load = new JMenu("Load");
		file.add(load);
		JMenuItem xmlload = new JMenuItem("XML");
		load.add(xmlload);
		JMenuItem jsonload = new JMenuItem("JSON");
		load.add(jsonload);

		ActionListener sx = actionEvent ->
				d.Save(frame,"xml");
		ActionListener sj = actionEvent ->
				d.Save(frame,"json");
		ActionListener lx = actionEvent ->
				d.Load(frame,"xml");
		ActionListener lj = actionEvent ->
				d.Load(frame,"json");
		ActionListener ex = actionEvent ->
				System.exit(0);
		ActionListener nf = actionEvent ->
				d.newFile();

		xmlsave.addActionListener(sx);
		jsonsave.addActionListener(sj);
		xmlload.addActionListener(lx);
		jsonload.addActionListener(lj);
		exit.addActionListener(ex);
		newfile.addActionListener(nf);

		JLabel lblNewLabel_1 = new JLabel("Redo&Undo");
		lblNewLabel_1.setFont(new Font("Constantia", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 2));
		lblNewLabel_1.setBounds(15, 107, 93, 13);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Edit");
		lblNewLabel_2.setFont(new Font("Constantia", lblNewLabel_2.getFont().getStyle(), lblNewLabel_2.getFont().getSize() + 2));
		lblNewLabel_2.setBounds(15, 185, 68, 13);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Copy&Paste");
		lblNewLabel_3.setFont(new Font("Constantia", lblNewLabel_3.getFont().getStyle(), lblNewLabel_3.getFont().getSize() + 2));
		lblNewLabel_3.setBounds(19, 369, 89, 13);
		frame.getContentPane().add(lblNewLabel_3);

	}
}