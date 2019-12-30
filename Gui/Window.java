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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Tester.all;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Edgedata;
import dataStructure.Nodedata;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class Window extends JFrame implements ActionListener, MouseListener {
	DGraph g0= new DGraph();
	
	ArrayList<node_data> g3= new ArrayList<node_data>();
	ArrayList<Integer> g4= new ArrayList<Integer>();
	String w="";
	int src=0;
	int dest=0;
	int count=0;
	int countSave=0;
	public Window(){
		initGUI();
	}
	private void initGUI() {
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuBar menuBar = new MenuBar();
		
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);

		MenuBar menuBar1 = new MenuBar();
		Menu algo = new Menu("algo");
		menuBar.add(algo);
		this.setMenuBar(menuBar);

		MenuItem item1 = new MenuItem("savePic");
		item1.addActionListener(this);

		MenuItem item2 = new MenuItem("clearAll");
		item2.addActionListener(this);

		MenuItem item3 = new MenuItem("isconnected");
		item3.addActionListener(this);

		MenuItem item4 = new MenuItem("shortestPathDist");
		item4.addActionListener(this);

		MenuItem item5 = new MenuItem("path");
		item5.addActionListener(this);
		
		MenuItem item9 = new MenuItem("endPath");
		item9.addActionListener(this);


		MenuItem item6 = new MenuItem("TSP");
		item6.addActionListener(this);
		
		MenuItem item8 = new MenuItem("endTSP");
		item8.addActionListener(this);

		MenuItem item7 = new MenuItem("clearWay");
		item7.addActionListener(this);
		
		MenuItem item10 = new MenuItem("saveGraph");
		item10.addActionListener(this);

		menu.add(item1);
		menu.add(item2);
		menu.add(item7);
		menu.add(item10);
		algo.add(item3);
		algo.add(item4);
		algo.add(item5);
		algo.add(item6);
		algo.add(item8);
		algo.add(item9);
		this.addMouseListener(this);
		
	}
	public void paint(Graphics g) {
			super.paint(g);
			ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
			g.setColor(Color.BLUE);
			for (node_data p: g1) {
				g.fillOval((int) p.getLocation().x(), (int) p.getLocation().y(), 7,7);	
				String str= Integer.toString(p.getKey());      
				g.drawString(str, (int)p.getLocation().x(), (int)p.getLocation().y()  );
			}
			for(Edgedata p : g0.geta()) {
				g.setColor(Color.ORANGE);
				g.fillOval((int) g0.getNode(p.getSrc()).getLocation().x(), (int) g0.getNode(p.getSrc()).getLocation().y(), 9,9);	
				if(g0.getEdge(p.getDest(), p.getSrc())!=null)g.setColor(Color.green);
				else g.setColor(Color.RED);
				g.drawLine((int)g1.get(p.getSrc()).getLocation().x(),(int)g1.get(p.getSrc()).getLocation().y(),(int)g1.get(p.getDest()).getLocation().x(),(int)g1.get(p.getDest()).getLocation().y());		
				g.setColor(Color.RED);
				double d=p.getWeight()*100;
				double e=(int)d;
				String str= ""+(e/100);
				g.setColor(Color.RED);
				g.drawString(str,(int)((g1.get(p.getSrc()).getLocation().x()+g1.get(p.getDest()).getLocation().x())/2),(int)((g1.get(p.getSrc()).getLocation().y()+g1.get(p.getDest()).getLocation().y())/2));
			}
		
		g.setColor(Color.black);
		for (int i = 0; i <g3.size()-1; i++) {
			g.drawLine(g3.get(i).getLocation().ix(),g3.get(i).getLocation().iy(),g3.get(i+1).getLocation().ix(),g3.get(i+1).getLocation().iy());		
		}
	}
	@Override

	public void actionPerformed(ActionEvent e) 
	{
		String str = e.getActionCommand();
		Graph_Algo a = new Graph_Algo();
		if(str.equals("savePic"))	{
			a.init(g0);
			save_paint();
		}
		if(str.equals("clearAll")){
			w="";
			count=0;
			src=0;
			dest=0;
			g3=new ArrayList<node_data>();
			g0=new DGraph();
			g4 = new ArrayList<Integer>();
		}
		if(str.equals("clearWay")){
			w="";
			count=0;
			src=0;
			dest=0;
			g3=new ArrayList<node_data>();
			g4 = new ArrayList<Integer>();
		}

		if(str.equals("isconnected")){
			a.init(g0);
			System.out.println(a.isConnected());
		}
		if(str.equals("shortestPathDist"))	{
			a.init(g0);
			System.out.println(a.shortestPathDist(0,4));
		}
		if(str.equals("path")){
			w=str;
		}
		if(str.equals("endPath")) {
			a.init(g0);
			g3= new ArrayList<node_data>(a.shortestPath(src, dest));
			ArrayList<Integer> g2 = new ArrayList<Integer>();
			for (int i = 0; i < g3.size(); i++) 		g2.add(g3.get(i).getKey());	
			System.out.println(g2);
			w="";
			count=0;
			src=0;
			dest=0;
		}
		if(str.equals("endTSP")) {	
		a.init(g0);
		g3= new ArrayList<node_data>(a.TSP(g4));
		ArrayList<Integer>  g2 = new ArrayList<Integer>();
		for (int i = 0; i < g3.size(); i++) 	g2.add(g3.get(i).getKey());	
		System.out.println(g2);
		g4 = new ArrayList<Integer>();
			w="";
		}
		if(str.equals("TSP")){
		w=str;
		}
		if(str.equals("saveGraph")) {
			a.init(g0);
			a.save("graph"+countSave+".txt");
			countSave++;
		}
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("mouseClicked");	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(w.equals("path")) {
			double x = e.getX();
			double y = e.getY();
				ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
				for(node_data a: g1) {
					Point3D p1 = new Point3D((int)a.getLocation().x(),(int)a.getLocation().y());
					if (x<p1.x()+15 && x>p1.x()-15 && y<p1.y()+15 && y>p1.y()-15) {
						if(count==0)src=a.getKey();
						else dest=a.getKey();
					}
				}
				count++;
		if(count>1)	System.out.println("in list: "+src+","+dest);
		}
		if(w.equals("TSP")) {
			double x = e.getX();
			double y = e.getY();
				ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
				for(node_data a: g1) {
					Point3D p1 = new Point3D((int)a.getLocation().x(),(int)a.getLocation().y());
					if (x<p1.x()+15 && x>p1.x()-15 && y<p1.y()+15 && y>p1.y()-15) g4.add(a.getKey());
				}
			System.out.println("in list:"+g4.get(g4.size()-1));
		}
		else {
		int x = e.getX();
		int y = e.getY();
		int j=0;
		ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
		if (g1.size()==0){
			Point3D p = new Point3D(x,y);
			int i= g0.nodeSize();
			Nodedata b=new Nodedata(p,i);
			System.out.println("new node:"+b.getKey());
			g0.addNode(b);
			repaint();		
		}	
		else {
			for(node_data a: g1) {
				if (x<a.getLocation().x()+15 && x>a.getLocation().x()-15 && y<a.getLocation().y()+15 && y>a.getLocation().y()-15) { 	
					g0.setnode(x,y).setWeight(1);	
				}
				else {	
					j++;			
					if(j==g0.nodeSize()) {
						Point3D p = new Point3D(x,y);
						int i= g0.nodeSize();
						Nodedata b=new Nodedata(p,i);
						System.out.println("new node:"+b.getKey());
						g0.addNode(b);
						repaint();
						break;
					}
				}
			}
		}
		}
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
							else{
								g0.connect(a.getKey(),g0.setnode(x,y).getKey(),g0.getNode(g0.setnode(x,y).getKey()).getLocation().distance2D(a.getLocation()));
								System.out.println("concet:"+a.getKey()+" to:"+g0.setnode(x,y).getKey());
							}
						}
						a.setWeight(999999999);	
					}	
				}
				repaint();		
			}
		}
		}

	@Override
	public void mouseEntered(MouseEvent e) {
	
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
}
