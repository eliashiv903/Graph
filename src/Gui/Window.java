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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

public class Window extends JFrame implements ActionListener, MouseListener {
	private DGraph g0 = new DGraph();
	private ArrayList<node_data> g3 = new ArrayList<node_data>();
	private ArrayList<Integer> g4 = new ArrayList<Integer>();
	private String syso = "";
	private String syso1 = "";
	private String syso2 = "";
	private String syso3 = "";
	private String syso4 = "";
	private String w = "";
	private int src = 0;
	private int dest = 0;
	private int count = 0;
	private int countSave = 0;
	public  Window(graph p) {
		g0=new DGraph(p);
		initGUI();
		repaint();
	}
	public Window() {
		initGUI();
	}

	private void initGUI() {
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		menuBar.add(menu);
		this.setMenuBar(menuBar);

		Menu syso = new Menu();
		menuBar.add(syso);
		this.setMenuBar(menuBar);


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

		MenuItem item6 = new MenuItem("TSP");
		item6.addActionListener(this);

		MenuItem item8 = new MenuItem("endTSP");
		item8.addActionListener(this);

		MenuItem item7 = new MenuItem("clearWayAns");
		item7.addActionListener(this);

		MenuItem item10 = new MenuItem("saveGraph");
		item10.addActionListener(this);

		MenuItem item11 = new MenuItem("removeEdgeFromGraph");
		item11.addActionListener(this);

		MenuItem item12 = new MenuItem("removeNodeFromGraph");
		item12.addActionListener(this);

		MenuItem item13 = new MenuItem("dataGraphToString");
		item13.addActionListener(this);
		
		MenuItem item14 = new MenuItem("toGoBackOneClick");
		item14.addActionListener(this);
		MenuItem item15 = new MenuItem("saveGraph");
		item15.addActionListener(this);
		menu.add(item14);
		menu.add(item10);
		menu.add(item1);
		menu.add(item2);
		menu.add(item7);
		algo.add(item3);
		algo.add(item4);
		algo.add(item5);
		algo.add(item6);
		algo.add(item8);
		menu.add(item11);
		menu.add(item12);
		menu.add(item13);
		algo.add(item15);
		this.addMouseListener(this);

	}

	public void paint(Graphics g) {
		super.paint(g);

		HashMap<Integer, node_data>  dataNode= new HashMap<Integer, node_data>(g0.getVHash());
		HashMap<Integer, HashMap<Integer,edge_data>>  edgedataNode= new  HashMap<Integer, HashMap<Integer,edge_data>>(g0.getEHash());
		for ( HashMap.Entry   entry :   dataNode.entrySet() ) {
			if( edgedataNode.get(entry.getKey())!=null) 
				for ( HashMap.Entry   entry1 :  edgedataNode.get(entry.getKey()).entrySet() ) {
					Point3D px = TowardsX(g0.getNode( edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc()).getLocation(),  g0.getNode(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest()).getLocation());
					Point3D py = TowardsY(g0.getNode( edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc()).getLocation(),  g0.getNode(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest()).getLocation());
					Point3D pSet = Towards(px, py, g0.getNode( edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc()).getLocation());
					g.setColor(Color.CYAN);
					g.fillOval(pSet.ix(), pSet.iy(), 7, 7);
					g.setColor(Color.PINK);					if(edgedataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest())!=null) {
						if(edgedataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest()).get( edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc())!=null)g.setColor(Color.BLUE);
						else g.setColor(Color.PINK);}
					else	g.setColor(Color.PINK);
					g.drawLine(dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc()).getLocation().ix(), dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc()).getLocation().iy(),dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest()).getLocation().ix(), dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest()).getLocation().iy());
					double d = edgedataNode.get(entry.getKey()).get(entry1.getKey()).getWeight() * 100;
					double e = (int) d;
					String str = "" + (e / 100);
					g.setColor(Color.RED);
					g.drawString(str,(int) ((dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc()).getLocation().x() + dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest()).getLocation().x()) / 2),(int) ((dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getSrc()).getLocation().y() + dataNode.get(edgedataNode.get(entry.getKey()).get(entry1.getKey()).getDest()).getLocation().y()) / 2));
				}
		}
		g.setColor(Color.black);
		for (int i = 0; i < g3.size() - 1; i++) {
			g.drawLine(g3.get(i).getLocation().ix(), g3.get(i).getLocation().iy(), g3.get(i + 1).getLocation().ix(),	g3.get(i + 1).getLocation().iy());
		}
		g.setColor(Color.BLUE);
		for ( HashMap.Entry   p :  dataNode.entrySet() ) {
			g.fillOval(dataNode.get(p.getKey()).getLocation().ix(), dataNode.get(p.getKey()).getLocation().iy(), 7, 7);
			String str = Integer.toString((int) p.getKey());
			g.drawString(str, dataNode.get(p.getKey()).getLocation().ix(), dataNode.get(p.getKey()).getLocation().iy());
			//
			//			double px=dataNode.get(p.getKey()).getLocation().x()*100;
			//			double pxe=(int)px;
			//		double py=dataNode.get(p.getKey()).getLocation().y()*100;
			//		double pye=(int)py;
			//		String str1="("+pxe/100+","+pye/100+")" ;      ///׳׳” ׳¢׳•׳©׳”????
			//		g.drawString(str1, (int)dataNode.get(p.getKey()).getLocation().x()-30, (int)dataNode.get(p.getKey()).getLocation().y()-10  );
		}
		g.setColor(Color.BLACK);
		g.drawLine(0, 144, 2000, 144);
		g.drawString(syso, 99, 73);
		if(!syso2.equals(""))g.drawString(syso2, 99, 85);
		if(!syso1.equals(""))g.drawString(syso1, 99, 97);
		if(!syso3.equals(""))g.drawString(syso3, 99, 109);
		if(!syso4.equals(""))g.drawString(syso4, 99, 121);

	}

	private Point3D Towards(Point3D px, Point3D py, Point3D dest) {
		if (px.distance2D(dest) < py.distance2D(dest))
			return px;
		return py;
	}

	private Point3D TowardsX(Point3D p1, Point3D p2) {
		double chnge;
		boolean x = Math.abs(p1.x() - p2.x()) > 12;
		if (x)
			chnge = 7;
		else
			chnge = 0.5;
		double m = (p1.y() - p2.y()) / (p1.x() - p2.x());
		double x1 = p1.x() + chnge;
		double x2 = p1.x() - chnge;// (y-y1=m(x-x1.//y-mx+mx1=y1
		double y1 = p1.y() - (m * p1.x()) + (m * x1);
		double y2 = p1.y() - (m * p1.x()) + (m * x2);
		Point3D one = new Point3D(x1, y1);
		Point3D tow = new Point3D(x2, y2);
		if (one.distance2D(p2) < tow.distance2D(p2))
			return one;
		else
			return tow;
	}

	private Point3D TowardsY(Point3D p1, Point3D p2) {
		double chnge;
		boolean y = Math.abs(p1.y() - p2.y()) > 22;
		if (Math.abs(p1.y() - p2.y()) < 2)
			chnge = 0.25;
		if (y)
			chnge = 6;
		else
			chnge = 0.5;

		double m = (p1.y() - p2.y()) / (p1.x() - p2.x());
		double y1 = p1.y() + chnge;
		double y2 = p1.y() - chnge;// (y-y1=m(x-x1.//y-mx+mx1=y1//x1=(-y+y1-xm)/m
		double x1 = (-p1.y() + y1 - (p1.x() * m)) / m;
		double x2 = (-p1.y() + y2 - (p1.x() * m)) / m;
		Point3D one = new Point3D(x1, y1);
		Point3D tow = new Point3D(x2, y2);
		if (one.distance2D(p2) < tow.distance2D(p2))
			return one;
		else
			return tow;
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("toGoBackOneClick")) {
			Graph_Algo c = new Graph_Algo();
			c.init("return.txt");
			g0 =new DGraph(c.copy());
		}
		
		saveGraph("return.txt");
		if(!str.equals("savePic")) {
			syso1="";
			syso2="";
			syso3="";
			syso4="";
		}
		Graph_Algo a = new Graph_Algo();
		if (str.equals("savePic")) {
			a.init(g0);
			save_paint();
		}
		if (str.equals("clearAll")) {
			w = "";
			count = 0;
			src = 0;
			dest = 0;
			g3 = new ArrayList<node_data>();
			g0 = new DGraph();
			g4 = new ArrayList<Integer>();
			syso = "All clear";
			syso1="";
			syso2="";
			syso3="";
			syso4="";
		}
		if (str.equals("dataGraphToString")) {
			syso = g0.toStringDataNode();
			syso1=g0.toStringedgedataNode();
			syso2="size DataNode:"+g0.nodeSize();
			syso3="size edgedataNode:"+g0.edgeSize();
			syso4="MC:"+g0.getMC();
			System.out.println(syso);
			System.out.println(syso2);
			System.out.println(syso1);
			System.out.println(syso3);
			System.out.println(syso4);	
		}
		if (str.equals("clearWayAns")) {
			w = "";
			count = 0;
			src = 0;
			dest = 0;
			g3 = new ArrayList<node_data>();
			g4 = new ArrayList<Integer>();
			syso1="";
			syso2="";
			syso3="";
			syso4="";
			syso="the wayAns clear";

		}

		if (str.equals("isconnected")) {
			a.init(g0);
			boolean cv = a.isConnected();
			syso = "" + cv;
			System.out.println(cv);
		}
		if (str.equals("shortestPathDist")) {
			w = str;
			syso = "Choose a src and dest";
		}

		if (str.equals("path")) {
			syso = "Choose a src and dest";
			w = str;
		}
		if (str.equals("endTSP")) {
			a.init(g0);
			g3 = new ArrayList<node_data>(a.TSP(g4));
			ArrayList<Integer> g2 = new ArrayList<Integer>();
			for (int i = 0; i < g3.size(); i++)
				g2.add(g3.get(i).getKey());
			syso = "TSP targets:"+g4.toString();
			syso1=".TSP ans:" + g2.toString();
			System.out.println("TSP ans:" + g2.toString());
			g4 = new ArrayList<Integer>();
			w = "";
		}
		if (str.equals("TSP")) {
			w = str;
			syso = "Choose a point or end";
		}
		if (str.equals("saveGraph")) {
			a.init(g0);
			a.save("graph1" + countSave + ".txt");
			countSave++;
			syso = "Graph save";
		}
		if (str.equals("removeEdgeFromGraph")) {
			syso = "Choose a src and dest";
			w = str;
		}
		if (str.equals("removeNodeFromGraph")) {
			syso = "Choose a node key";
			w = str;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		saveGraph("return.txt");
		syso = "";
		syso1="";
		syso2="";
		syso3="";
		syso4="";
		if (w.equals("path") || w.equals("shortestPathDist")||w.equals("removeEdgeFromGraph")||w.equals("removeNodeFromGraph")) {
			double x = e.getX();
			double y = e.getY();
			ArrayList<node_data> g1 = new ArrayList<node_data>(g0.getV());
			for (node_data a : g1) {
				Point3D p1 = new Point3D((int) a.getLocation().x(), (int) a.getLocation().y());
				if (x < p1.x() + 15 && x > p1.x() - 15 && y < p1.y() + 15 && y > p1.y() - 15) {
					if (count == 0)src = a.getKey();
					else dest = a.getKey();
				}
			}
			count++;
			if (count == 1) {
				if(w.equals("removeNodeFromGraph")) {
					g0.removeNode(src);
					syso="remove node:"+src;
					System.out.println(syso);
					w = "";
					count = 0;
					src = 0;
					dest = 0;
				}
				else {
					syso = "in list: " + src + ", choose dest";
					System.out.println("in list: " + src + "," + dest);
				}
			} else {
				if(w.equals("removeEdgeFromGraph")) {
					g0.removeEdge(src, dest);
					syso="remove edge from :"+src+" to "+dest;
					System.out.println(syso);
				}
				else if (w.equals("shortestPathDist")) {
					Graph_Algo a = new Graph_Algo();
					a.init(g0);
					double ans = a.shortestPathDist(src, dest);
					syso = "from src:"+src+"  to dest:"+dest+"  dist=" + ans;
					System.out.println(ans);
				}
				else if (w.equals("path")) {
					Graph_Algo a = new Graph_Algo();
					a.init(g0);
					g3 = new ArrayList<node_data>(a.shortestPath(src, dest));
					ArrayList<Integer> g2 = new ArrayList<Integer>();
					for (int i = 0; i < g3.size(); i++)
						g2.add(g3.get(i).getKey());
					syso = "from src:"+src+" to dest:"+dest;
					syso1="Path ans:" + g2.toString();
					System.out.println("Path ans:" + g2);

				}
				w = "";
				count = 0;
				src = 0;
				dest = 0;
			}
		} else if (w.equals("TSP")) {
			double x = e.getX();
			double y = e.getY();
			ArrayList<node_data> g1 = new ArrayList<node_data>(g0.getV());
			for (node_data a : g1) {
				Point3D p1 = new Point3D((int) a.getLocation().x(), (int) a.getLocation().y());
				if (x < p1.x() + 15 && x > p1.x() - 15 && y < p1.y() + 15 && y > p1.y() - 15)
					g4.add(a.getKey());
			}
			syso = "in list:" + g4;
			System.out.println("in list:" + g4.get(g4.size() - 1));
		} else {
			int x = e.getX();
			int y = e.getY();
			int j = 0;
			ArrayList<node_data> g1 = new ArrayList<node_data>(g0.getV());
			if (g1.size() == 0) {
				Point3D p = new Point3D(x, y);
				int i = g0.nodeSize();
				Nodedata b = new Nodedata(p, i);
				syso = "new node:" + b.getKey();
				System.out.println("new node:" + b.getKey());
				g0.addNode(b);
				repaint();
			} else {
				for (node_data a : g1) {
					if (x < a.getLocation().x() + 15 && x > a.getLocation().x() - 15 && y < a.getLocation().y() + 15	&& y > a.getLocation().y() - 15) {
						setnode(x, y).setTag(10);
					} else {
						j++;
						if (j == g0.nodeSize()) {
							Point3D p = new Point3D(x, y);


							HashMap<Integer, node_data>  dataNode= new HashMap<Integer, node_data>(g0.getVHash());
							int i=0;
							for ( HashMap.Entry   entry : dataNode.entrySet() ) {
								if(i==dataNode.get(entry.getKey()).getKey())i++;
								else break;
							}

							Nodedata b = new Nodedata(p, i);
							syso = "new node:" + b.getKey();
							System.out.println("new node:" + b.getKey());
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
		Point3D p = new Point3D(x, y);
		ArrayList<node_data> g1 = new ArrayList<node_data>(g0.getV());
		if (g1.size() > 1) {
			if (e.getX() != g1.get(g1.size() - 1).getLocation().x()&& e.getY() != g1.get(g1.size() - 1).getLocation().y()) {
				if (setnode(x, y) != null) {
					for (node_data a : g1) {
						if (a.getTag() == 10) {
							if (setnode(x, y) == a) {
							} else {
								g0.connect(a.getKey(), setnode(x, y).getKey(), g0.getNode(setnode(x, y).getKey()).getLocation().distance2D(a.getLocation()));
								syso = "concet:" + a.getKey() + " to " + setnode(x, y).getKey();
								System.out.println("concet:" + a.getKey() + " to:" + setnode(x, y).getKey());
							}
						}
						a.setTag(0);
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
		// System.out.println("mouseExited");
	}
	public node_data setnode(int x, int y) {
		ArrayList<node_data> g1= new ArrayList<node_data>(g0.getV());
		for (node_data a: g1) {
			if (x<a.getLocation().x()+10 && x>a.getLocation().x()-10 && y<a.getLocation().y()+10 && y>a.getLocation().y()-10) {
				return a;
			}
		}
		return null;
	}
	public void save_paint() {
		try {
			BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
			this.paint(graphics2D);
			File outputfile = new File("imageGraph.jpg");
			ImageIO.write(image, "jpg", outputfile);
			System.out.println("save_paint ok");
			syso="save_paint ok";
			syso1="";
			syso2="";
			syso3="";
			syso4="";
		} catch (Exception exception) {
			System.out.println("no image");
			syso="no image";
			syso1="";
			syso2="";
			syso3="";
			syso4="";
		}
	}
	public void saveGraph(String file_name) {
        try  {    
            FileOutputStream file = new FileOutputStream(file_name); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
           out.writeObject(g0);
            out.close(); 
            file.close(); 
        }   
        catch(IOException ex)   { 
        } 
	} 
	public static void main(String[] args) {
		Window window = new Window();
		window.setVisible(true);
	}
}
