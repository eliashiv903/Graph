package Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.LinkedList;
import javax.swing.JFrame;
import dataStructure.DGraph;
import dataStructure.Edgedata;
import dataStructure.Nodedata;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

public class Window extends JFrame implements ActionListener, MouseListener
{
	DGraph g0= new DGraph();

	public Window()
	{
		initGUI();
	}

	private void initGUI() 
	{
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("Item 1");
		item1.addActionListener(this);

		MenuItem item2 = new MenuItem("Item 2");
		item2.addActionListener(this);

		menu.add(item1);
		menu.add(item2);

		this.addMouseListener(this);
	}

	public void paint(Graphics g)
	{
		super.paint(g);


		ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
		for (node_data p: g1) 
		{
			g.setColor(Color.BLUE);
			g.fillOval((int) p.getLocation().x(), (int) p.getLocation().y(), 7,7);	
			String str= Integer.toString(p.getKey());      
			g.drawString(str, (int)p.getLocation().x(), (int)p.getLocation().y()  );
			
		}
	
		for(Edgedata p : g0.geta()) {
		g.setColor(Color.RED);
		g.drawLine((int)g1.get(p.getSrc()).getLocation().x(),(int)g1.get(p.getSrc()).getLocation().y(),(int)g1.get(p.getDest()).getLocation().x(),(int)g1.get(p.getDest()).getLocation().y());		
		String str= Integer.toString((int) p.getWeight());
		g.drawString(str,(int)((g1.get(p.getSrc()).getLocation().x()+g1.get(p.getDest()).getLocation().x())/2),(int)((g1.get(p.getSrc()).getLocation().y()+g1.get(p.getDest()).getLocation().y())/2));
		}
	}




	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();

		if(str.equals("Item 1"))
		{

			Nodedata b1=new Nodedata(new Point3D(100, 100),0);
			Nodedata b2=new Nodedata(new Point3D(250, 200),1);
			Nodedata b3=new Nodedata(new Point3D(120, 280),2);
			Nodedata b4=new Nodedata(new Point3D(350, 400),3);
			g0.addNode(b1);
			g0.addNode(b2);
			g0.addNode(b3);
			g0.addNode(b4);
			g0.connect(0,1,0);
			g0.connect(0,2,2);
			g0.connect(0,3,4);
			
			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point3D p = new Point3D(x,y);
		points.add(p);
		repaint();
		System.out.println("mousePressed");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}		
	}

