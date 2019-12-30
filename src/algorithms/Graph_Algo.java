package algorithms;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.io.*; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import dataStructure.DGraph;
import dataStructure.Nodedata;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms  {
	private DGraph smart=new DGraph ();
	@Override
	public void init(graph g) {
		smart=(DGraph) g;
	}
	
	@Override
	public void init(String file_name) {
        try   {    
            FileInputStream file = new FileInputStream(file_name); 
            ObjectInputStream in = new ObjectInputStream(file); 
            this.smart = (DGraph)in.readObject(); 
            in.close(); 
            file.close(); 
            System.out.println("Object has been deserialized"); 
        } 
        catch(IOException ex)  { 
            System.out.println("IOException is caught"); 
        }  
        catch(ClassNotFoundException ex)   { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
	}

	@Override
	public void save(String file_name) {
	        try  {    
	            FileOutputStream file = new FileOutputStream(file_name); 
	            ObjectOutputStream out = new ObjectOutputStream(file); 
	           out.writeObject(smart);
	            out.close(); 
	            file.close(); 
	            System.out.println("Object has been serialized"); 
	        }   
	        catch(IOException ex)   { 
	            System.out.println("IOException is caught"); 
	        } 
		} 
	

	@Override
	public boolean isConnected() {
		HashMap<Integer, node_data>  dataNode= new HashMap<Integer, node_data>(smart.getVHash());
		HashMap<Integer, HashMap<Integer,edge_data>>  edgedataNode= new  HashMap<Integer, HashMap<Integer,edge_data>>(smart.getE());
		for ( HashMap.Entry   entry :  dataNode.entrySet() ) {
			if(edgedataNode.get(entry.getKey())==null)return false;
			boolean a= concat((int)entry.getKey(),dataNode,edgedataNode);
			if(!a)	return false;
			for ( HashMap.Entry   entry2 :  dataNode.entrySet() ) 		dataNode.get(entry2.getKey()).setTag(0);
		}
		return true;
	}

	private boolean concat(int entry, HashMap<Integer, node_data> dataNode, HashMap<Integer, HashMap<Integer, edge_data>> edgedataNode) {
		dataNode.get(entry).setTag(1);
		if( edgedataNode.get(entry)!=null) {
			for ( HashMap.Entry   entry1 :  edgedataNode.get(entry).entrySet() ) {
				if(dataNode.get(edgedataNode.get(entry).get(entry1.getKey()).getDest()).getTag()!=1)concat(edgedataNode.get(entry).get(entry1.getKey()).getDest(),dataNode,edgedataNode);
			}
		}
		for ( HashMap.Entry   entry2 :  dataNode.entrySet() ) 	if(dataNode.get(entry2.getKey()).getTag()!=1)return false;
		return true;
	}

	

	


	@Override
	public double shortestPathDist(int src, int dest) {
		HashMap<Integer, node_data>  dataNode= new HashMap<Integer, node_data>(smart.getVHash());
		HashMap<Integer, HashMap<Integer,edge_data>>  edgedataNode= new  HashMap<Integer, HashMap<Integer,edge_data>>(smart.getE());
		dataNode.get(src).setWeight(0);
		double ans= shortestPathDist1( src,  dest,dataNode,edgedataNode);
		return ans;
	}

	private double shortestPathDist1(int src, int dest, HashMap<Integer, node_data> dataNode, HashMap<Integer, HashMap<Integer, edge_data>> edgedataNode) {
		dataNode.get(src).setTag(1);
		Point3D p=dataNode.get(src).getLocation();
		double whiteSrc=dataNode.get(src).getWeight();
		ArrayList<node_data> s=new ArrayList<node_data>();
		if(edgedataNode.get(src)!=null)
			for ( HashMap.Entry   entry1 :  edgedataNode.get(src).entrySet() ) {
				if(dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).getWeight()>dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).getLocation().distance2D(p)+whiteSrc)dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).setWeight(dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).getLocation().distance2D(p)+whiteSrc);
				s.add(dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()));
			}
		Collections.sort( s, new  node_data_Comperator());
		for (int i = 0; i < s.size(); i++) {
			if(dataNode.get(s.get(i).getKey()).getTag()!=1)	shortestPathDist1(s.get(i).getKey(),dest,dataNode,edgedataNode);
		}
		
		return dataNode.get(dest).getWeight();
	}

	public List<node_data> shortestPath(int src, int dest) {
		HashMap<Integer, node_data>  dataNode= new HashMap<Integer, node_data>(smart.getVHash());
		HashMap<Integer, HashMap<Integer,edge_data>>  edgedataNode= new  HashMap<Integer, HashMap<Integer,edge_data>>(smart.getE());
		dataNode.get(src).setWeight(0);
		ArrayList<Integer> ans= shortestPathDist3( src,  dest,new HashMap<Integer, ArrayList<Integer>>(),-1,0,dataNode,edgedataNode);
		List<node_data> ans1=new ArrayList<node_data>();
		if (ans==null)return null;
		for (int i = 0; i < ans.size(); i++) ans1.add(dataNode.get(ans.get(i)));

		return ans1;
	}

	private ArrayList<Integer>  shortestPathDist3(int src, int dest,HashMap<Integer, ArrayList<Integer>> go, int srcBeind,int return1, HashMap<Integer, node_data> dataNode, HashMap<Integer, HashMap<Integer, edge_data>> edgedataNode) {
		dataNode.get(src).setTag(1);
		Point3D p=dataNode.get(src).getLocation();
		double whiteSrc=dataNode.get(src).getWeight();
		if(srcBeind==-1){
			ArrayList<Integer> way1=new ArrayList<Integer>();
			way1.add(src);
			go.put(src, way1);
		}
		ArrayList<node_data> s=new ArrayList<node_data>();
		if(edgedataNode.get(src)!=null)for ( HashMap.Entry   entry1 :  edgedataNode.get(src).entrySet() ) {
			if(dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).getWeight()>dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).getLocation().distance2D(p)+whiteSrc) {
				go.put(edgedataNode.get(src).get(entry1.getKey()).getDest(),  new ArrayList<Integer>(go.get(src)));
				go.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).add(edgedataNode.get(src).get(entry1.getKey()).getDest());
				dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).setWeight(dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()).getLocation().distance2D(p)+whiteSrc);
			}
			s.add(dataNode.get(edgedataNode.get(src).get(entry1.getKey()).getDest()));
		}
		Collections.sort( s, new  node_data_Comperator());
		for (int i = 0; i < s.size(); i++) {
			if(dataNode.get(s.get(i).getKey()).getTag()!=1) shortestPathDist3(s.get(i).getKey(),dest,go,src,return1,dataNode,edgedataNode);
		}
		return go.get(dest);
	}
	public List<node_data> TSP(List<Integer> targets) {
		 Graph_Algo a=new  Graph_Algo ();
		 for (int i = 0; i < targets.size(); i++) a.smart.addNode(smart.getNode(targets.get(i)));
	//if(!a.isConnected())return null;
		return sort(targets);
	}

	public List<node_data> sort(List<Integer> targets) {
		List<node_data> ans1=new ArrayList<node_data>();
		List<node_data> ans=new ArrayList<node_data>();
		for (int i = 0; i < targets.size(); i++) ans.add(smart.getNode(targets.get(i)));
		node_data w=new Nodedata();
		while(ans.size()!=0) {
			w=new Nodedata(ans.get(0));
			ans.remove(0);
			if(ans.size()>0)litel(ans,w);
			//Collections.sort( ans, new  node_data1_Comperator(this,w));
			for (int i = 0; i < ans.size(); i++) {
				if(ans.size()>0&&shortestPath(w.getKey(), ans.get(i).getKey())!=null) {
					if(ans.size()>0)ans1.addAll(shortestPath(w.getKey(), ans.get(i).getKey()));
					if(ans.size()!=1 &&ans.size()!=0 )ans1.remove(ans1.size()-1);
					break;
				}
				else return new ArrayList<node_data>();//???chnge to null
			}
		}
		return ans1;
	}

	private void litel(List<node_data> ans, node_data w) {
		double a=0;
		double lital=999999999.9;
		int j=0;
		Nodedata b=new Nodedata();
		for (int i = 0; i < ans.size(); i++) {
			double c=shortestPathDist(w.getKey(),ans.get(i).getKey());
			if(c<lital) {
				lital=c;
				b=new Nodedata(ans.get(i));
				j=i;
			}
		}
		ans.remove(j);
		if(ans.size()>0) {	
			ans.add(ans.get(0));
			ans.remove(0);
		}
		ans.add(0, b);
	}
	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		String s=",,,dddd,,,,,,,";
		while(s.charAt(s.length()-1)==' ' ||s.charAt(s.length()-1)==',')s=s.substring(0,s.length()-1);
		System.out.println(s);
		List<Integer> t=new ArrayList<Integer>();
		t.add(1);
		t.add(2);
		t.add(3);
		t.add(4);
		t.remove(0);
		//t.add(0, 8);
		System.out.print("[");
		for (int i = 0; i < t.size(); i++) {
			System.out.print(t.get(i)+",");
		}
		System.out.print("]");
		System.out.println();
		Graph_Algo a=new Graph_Algo();
		graph q2=new DGraph();
		Nodedata b1=new Nodedata(new Point3D(-1, 1),1);
		Nodedata b2=new Nodedata(new Point3D(-1, -1),2);
		Nodedata b3=new Nodedata(new Point3D(0, 0),3);
		Nodedata b4=new Nodedata(new Point3D(1, 1),4);
		Nodedata b5=new Nodedata(new Point3D(1, -1),5);
		Nodedata b6=new Nodedata(new Point3D(3,3),6);
		Nodedata b7=new Nodedata(new Point3D(3, -3),7);
		Nodedata b8=new Nodedata(new Point3D(-3, -3),8);
		Nodedata b9=new Nodedata(new Point3D(-3, 3),9);
		Nodedata b10=new Nodedata(new Point3D(-3, 3.2),10);
		Nodedata b11=new Nodedata(new Point3D(3,3.1),11);
		Nodedata b12=new Nodedata(new Point3D(3, -3.2),12);
		Nodedata b13=new Nodedata(new Point3D(3, -3.21),13);
		Nodedata b14=new Nodedata(new Point3D(-3, 3.4),14);
		Nodedata b15=new Nodedata(new Point3D(0, 0.5),15);
		
		q2.addNode(b1);
		q2.addNode(b2);

		q2.addNode(b3); 
		q2.addNode(b4);
		q2.addNode(b5);
		q2.addNode(b6);
		q2.addNode(b7);
		q2.addNode(b8);
		q2.addNode(b9);
		q2.addNode(b10);
		q2.addNode(b11);
		q2.addNode(b12);
		q2.addNode(b13);
		q2.addNode(b14);
		q2.addNode(b15);
		ArrayList<Integer> ans=new ArrayList<Integer>();
		//System.out.println(ans.size());
		double w=999999999;
		//a.smart.connect(b1.getKey(), b2.getKey(), w);
		q2.connect(b1.getKey(), b2.getKey(), w);
		q2.connect(b2.getKey(), b3.getKey(), w);
		q2.connect(b3.getKey(), b4.getKey(), w);
		//a.smart.connect(b7.getKey(), b2.getKey(), w);
		q2.connect(b4.getKey(), b5.getKey(), w);
		q2.connect(b5.getKey(), b6.getKey(), w);
		q2.connect(b6.getKey(), b7.getKey(), w);
		q2.connect(b7.getKey(), b8.getKey(), w);
		q2.connect(b8.getKey(),b9.getKey(), w); 
		q2.connect(b9.getKey(),b1.getKey(), w); 
		q2.connect(b2.getKey(),b6.getKey(), w); 
		q2.connect(b2.getKey(), b4.getKey(), w);
		q2.connect(b2.getKey(),b1.getKey(), w); 
		q2.connect(b9.getKey(),b1.getKey(), w); 
		q2.connect(b2.getKey(),b7.getKey(), w);
		q2.connect(b9.getKey(),b10.getKey(), w); 
		q2.connect(b10.getKey(), b11.getKey(), w);
		q2.connect(b11.getKey(),b12.getKey(), w); 
		q2.connect(b12.getKey(),b14.getKey(), w); 
		q2.connect(b13.getKey(),b12.getKey(), w); 
		q2.connect(b13.getKey(),b1.getKey(), w); 
		q2.connect(b12.getKey(),b13.getKey(), w); 
		q2.connect(b14.getKey(),b13.getKey(), w); 
		q2.connect(b14.getKey(),b15.getKey(), w); 
		q2.connect(b3.getKey(),b15.getKey(), w);
		q2.connect(b15.getKey(),b1.getKey(), w);
		q2.connect(b4.getKey(),b15.getKey(), w);
		q2.connect(b14.getKey(),b1.getKey(), w); 
		q2.connect(b14.getKey(),b14.getKey(), w); 
		//System.out.println(a.isConnected());
a.init(q2);
		System.out.println(a.shortestPathDist(3, 15)+"no?");


		List<Integer>  e=new ArrayList<Integer> ();
		e.add(3);
		//e.add(4);
		e.add(4);
		e.add(15);
		//e.remove(1);
		System.out.println(e.size()+"size");
		System.out.println(e.get(1)+"?????");
		//e.add(3);
		//e.add(5);
		//e.add(6);
		//e.add(8);

		//a.TSP(e);
		
		 System.out.println(a.shortestPathDist(b9.getKey(), b1.getKey())+"hbbh");
		  System.out.println(a.smart.getEdge(9, 10).getWeight());
		  System.out.println(a.shortestPathDist(b1.getKey(), b14.getKey())+"hbbh");
		  System.out.println(a.shortestPathDist(b9.getKey(), b10.getKey())+"hbbh");
		  System.out.println(a.shortestPathDist(b10.getKey(), b11.getKey())+"hbbh");
		  System.out.println(a.shortestPathDist(b11.getKey(), b12.getKey())+"hbbh");
		  System.out.println(a.shortestPathDist(b12.getKey(), b13.getKey())+"hbbh");
		  System.out.println(a.shortestPathDist(b13.getKey(), b14.getKey())+"hbbh");
		 a.save("rr.txt");

		List<node_data>  x=new ArrayList<node_data> ();

		x=a.TSP(e);
		System.out.println(x.size());
		//x=a.shortestPath(b9.getKey(), b14.getKey());
		for (int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i).getKey()+"hxh");

		}
		System.out.println(a.isConnected()+"");
		/*e.add(9);
		//e.add(4);
		e.add(6);
		e.add(2);
		//e.add(4);
		//e.add(5);
		//e.add(6);
		//e.add(8);
		x=a.TSP(e);
		for (int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i).getKey()+"hh");

		}
	}*/
	}
}
