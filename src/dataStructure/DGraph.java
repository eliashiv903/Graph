package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class DGraph implements graph{
	public HashMap<Integer, node_data>  dataNode= new HashMap<Integer, node_data>();//20
	public HashMap<Integer, HashMap<Integer,edge_data>>  edgedataNode= new  HashMap<Integer, HashMap<Integer,edge_data>>();//20
	 public static int sizeEdge=0;
	@Override
	public node_data getNode(int key) {
		return dataNode.get(key);
	}

	
	@Override
	public edge_data getEdge(int src, int dest) {
		return edgedataNode.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		//if(dataNode.get(n.getKey())==null)throw new RuntimeException("ERR the   key  not legal: "+n.getKey());
		dataNode.put(n.getKey(), n);

	}

	@Override
	public void connect(int src, int dest, double w) {
		sizeEdge++;
		Edgedata a=new Edgedata(src, dest, w);
		if(edgedataNode.get(src)==null) {
			HashMap<Integer,edge_data> b= new HashMap<Integer,edge_data>();
			edgedataNode.put(src, b);
		}
		edgedataNode.get(src).put(dest, a);
	}

	@Override
	public Collection<node_data> getV() {
		//HashMap<Integer, node_data>  a= new HashMap<Integer, node_data>();
		// for (int i = 0; i < dataNode.size(); i++) {
		//	a.put(dataNode.get(i).getKey(),dataNode.get(i));
		//}
		return (Collection<node_data>) dataNode;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		//HashMap<Integer,edge_data> a= new HashMap<Integer,edge_data>();
		// for (int i = 0; i < edgedataNode.get(node_id).size(); i++) {
		//	a.put(edgedataNode.get(node_id).get(i).getDest(),edgedataNode.get(node_id).get(i));
		//}
		return (Collection<edge_data>) edgedataNode.get(node_id);
		
	}

	@Override
	public node_data removeNode(int key) {
		//Nodedata a=new Nodedata(data.get(key).getLocation(),data.get(key).getWeight(),data.get(key).getTag());
		//data.remove(key);
		
		for ( HashMap.Entry   entry :  dataNode.entrySet() ) {
			if(edgedataNode.get(entry)!=null)if(edgedataNode.get(entry).get(key)!=null)edgedataNode.get(entry).remove(key);
			sizeEdge--;
		}
		sizeEdge-=edgedataNode.get(key).size();
		edgedataNode.remove(key);
		
		return dataNode.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		return edgedataNode.get(src).remove(dest);
	}

	@Override
	public int nodeSize() {
		return dataNode.size();
	}

	@Override
	public int edgeSize() {
		return sizeEdge;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}
	public static void main(String[] args) {
		HashMap<Integer, Integer>  dataNode= new HashMap<Integer, Integer>();
		if(dataNode.get(22)==null)System.out.println("yes");
		else System.out.println("no");
		 
	}
}
