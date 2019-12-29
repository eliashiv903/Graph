package Gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Edgedata;
import dataStructure.Nodedata;
import dataStructure.edge_data;
import dataStructure.graph;
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

		//create();
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);

		MenuBar menuBar1 = new MenuBar();
		Menu algo = new Menu("algo");
		menuBar.add(algo);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("save");
		item1.addActionListener(this);

		MenuItem item2 = new MenuItem("clear");
		item2.addActionListener(this);

		MenuItem item3 = new MenuItem("isconnected");
		item3.addActionListener(this);

		MenuItem item4 = new MenuItem("shortestPathDist");
		item4.addActionListener(this);

		MenuItem item5 = new MenuItem("path");
		item5.addActionListener(this);

		MenuItem item6 = new MenuItem("TSP");
		item6.addActionListener(this);

		menu.add(item1);
		menu.add(item2);
		algo.add(item3);
		algo.add(item4);
		algo.add(item5);
		algo.add(item6);
		this.addMouseListener(this);
	}
	public void paint(Graphics g) {
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
				g.drawString(str,(int)((g1.get(p.getSrc()).getLocation().x()+g1.get(p.getDest()).getLocation().x())/3),(int)((g1.get(p.getSrc()).getLocation().y()+g1.get(p.getDest()).getLocation().y())/2));
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();

		if(str.equals("save"))
		{
			Graph_Algo a = new Graph_Algo();
			a.init(g0);
			save_paint();	
		}
		if(str.equals("clear"))
		{
			g0.getV1().clear();
			g0.ed.clear();
		}

		if(str.equals("isconnected"))
		{
			Graph_Algo a = new Graph_Algo();
			a.init(g0);
			System.out.println(a.isConnected());
		}
		if(str.equals("shortestPathDist"))
		{
			System.out.println("in");
			Graph_Algo a = new Graph_Algo();
			a.init(g0);
			System.out.println(a.shortestPathDist(0,4));
		}
		if(str.equals("path"))
		{
			//code below
		}
		if(str.equals("TSP"))
		{
			Graph_Algo a = new Graph_Algo();
			a.init(g0);
			ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
			ArrayList<Integer> g2 = new ArrayList<Integer>();
			for (int i = 0; i < g1.size(); i++) {	
				g2.add(g1.get(i).getKey());	
			}	

			System.out.println(a.TSP(g2));

			/**code to complete
			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 */
		}

		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int j=0;
		ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
		if (g1.size()==0){
			Point3D p = new Point3D(x,y);
			int i= g0.nodeSize();
			Nodedata b=new Nodedata(p,i);
			g0.addNode(b);
			repaint();		
		}	
		else {
			for(node_data a: g1) {
				if (x<a.getLocation().x()+15 && x>a.getLocation().x()-15 && y<a.getLocation().y()+15 && y>a.getLocation().y()-15) { 	
					g0.setnode(x,y).setWeight(1);
					System.out.println(g0.setnode(x,y).getWeight());		
				}
				else {	
					j++;			
					if(j==g0.nodeSize()) {
						Point3D p = new Point3D(x,y);
						int i= g0.nodeSize();
						Nodedata b=new Nodedata(p,i);
						g0.addNode(b);
						repaint();
						break;
					}
				}
			}
		}
		System.out.println("mousePressed");
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point3D p = new Point3D(x,y);
		ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
		if(g1.size()>1) {
			if(e.getX()!= g1.get(g1.size()-1).getLocation().x() &&  e.getY()!= g1.get(g1.size()-1).getLocation().y()) {
				if(g0.setnode(x,y)!=null) {
					for(node_data a: g1) {
						if(a.getWeight()==1) {
							if(g0.setnode(x,y)==a) {}
							else{g0.connect(a.getKey(),g0.setnode(x,y).getKey(),(p.distance2D(a.getLocation())));}
						}
						a.setWeight(0);	
					}	
				}
				repaint();		
			}
		}
		System.out.println("mouseReleased");

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("mouseEntered");
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("mouseExited");
	}		
	public void save_paint() {
		try
		{
			BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
			this.paint(graphics2D);
			ImageIO.write(image,"jpeg", new File("C:\\Users\\אלימלך\\Pictures\\Saved Pictures.savegraph.jpeg"));
			System.out.println("save_paint ok");
		}
		catch(Exception exception)
		{System.out.println("no image");
		}
	}
	public void create() {
		//to do actions
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
	}
}
