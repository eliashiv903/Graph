package algorithms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import dataStructure.DGraph;
import dataStructure.Nodedata;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private DGraph smart=new DGraph ();
	@Override
	public void init(graph g) {
		smart=(DGraph) g;

	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		for ( HashMap.Entry   entry :  smart.dataNode.entrySet() ) {
			if(smart.edgedataNode.get(entry.getKey())==null)return false;
			boolean a= concat((int)entry.getKey());
			if(!a)	return false;
			for ( HashMap.Entry   entry2 :  smart.dataNode.entrySet() ) {
				smart.dataNode.get(entry2.getKey()).setTag(0);
			}
		}
		return true;
	}

	private boolean concat(int entry) {
		//System.out.println(entry);
		smart.dataNode.get(entry).setTag(1);
		if( smart.edgedataNode.get(entry)!=null) {
		for ( HashMap.Entry   entry1 :  smart.edgedataNode.get(entry).entrySet() ) {
			if(smart.dataNode.get(smart.edgedataNode.get(entry).get(entry1.getKey()).getDest()).getTag()!=1)concat(smart.edgedataNode.get(entry).get(entry1.getKey()).getDest());
		}
		}
		for ( HashMap.Entry   entry2 :  smart.dataNode.entrySet() ) {
			//System.out.println(smart.dataNode.get(entry2.getKey()).getTag());
			if(smart.dataNode.get(entry2.getKey()).getTag()!=1)return false;
		}
		return true;
	}



	@Override
	public double shortestPathDist(int src, int dest) {
		smart.dataNode.get(src).setWeight(0);
		return shortestPathDist( src,  dest,999999999);
		
	}

	private double shortestPathDist(int src, int dest, double minWay) {
		System.out.println(src);
		smart.dataNode.get(src).setTag(1);
		if(smart.edgedataNode.get(src)==null)throw new RuntimeException("ERR the  src sholdn't be empty dest , got: "+src);
		Point3D p=smart.dataNode.get(src).getLocation();
		double whiteSrc=smart.dataNode.get(src).getWeight();
		double sumMin []=new double[2];
		sumMin[0]=999999999;
		sumMin[1]=1.1;
		//System.out.println(sumMin[0]);
		//System.out.println(smart.dataNode.get(src).getWeight());
			for ( HashMap.Entry   entry1 :  smart.edgedataNode.get(src).entrySet() ) {
			//	System.out.println(smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getWeight());
				if(smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getWeight()>smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getLocation().distance2D(p)+whiteSrc)smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).setWeight(smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getLocation().distance2D(p)+whiteSrc);
			if(sumMin[0]>smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getWeight()) {
				//System.out.println(smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getWeight());
				sumMin[0]=smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getWeight();
				//System.out.println(sumMin[0]);
				if(smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getTag()!=1)sumMin[1]=smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getKey();
			}
			if(sumMin[1]==1.1 &&smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getTag()!=1)sumMin[1]=smart.dataNode.get(smart.edgedataNode.get(src).get(entry1.getKey()).getDest()).getKey();
			}
		//System.out.println(sumMin[1]+"ll");
			if(sumMin[1]!=1.1)shortestPathDist((int)sumMin[1],dest,0);
			 return smart.dataNode.get(dest).getWeight();
	}

	


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		Graph_Algo a=new Graph_Algo();
		Nodedata b1=new Nodedata(new Point3D(0, 0),1);
		Nodedata b2=new Nodedata(new Point3D(0, 2),2);
		Nodedata b3=new Nodedata(new Point3D(0, 1),3);
		Nodedata b4=new Nodedata(new Point3D(0, 6),4);
		Nodedata b5=new Nodedata(new Point3D(0, 8),5);
		Nodedata b6=new Nodedata(new Point3D(0, 10),6);
		Nodedata b7=new Nodedata(new Point3D(0, 12),7);
		Nodedata b8=new Nodedata(new Point3D(0, 14),8);
		a.smart.addNode(b1);
		a.smart.addNode(b2);

		a.smart.addNode(b3); 
		a.smart.addNode(b4);
		a.smart.addNode(b5);
		a.smart.addNode(b6);
		a.smart.addNode(b7);
		a.smart.addNode(b8);

		double w=0;
		a.smart.connect(b1.getKey(), b2.getKey(), w);
	a.smart.connect(b1.getKey(), b3.getKey(), w);
		a.smart.connect(b2.getKey(), b3.getKey(), w);
		a.smart.connect(b3.getKey(),b4.getKey(), w); 
		a.smart.connect(b4.getKey(), b5.getKey(), w);
		a.smart.connect(b5.getKey(), b6.getKey(), w);
		a.smart.connect(b6.getKey(),b7.getKey(), w); 
		a.smart.connect(b7.getKey(), b8.getKey(), w);
		a.smart.connect(b8.getKey(), b1.getKey(), w);
System.out.println(a.shortestPathDist(b1.getKey(), b3.getKey()));
		System.out.println(a.isConnected());
	}
}
