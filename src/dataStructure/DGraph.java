package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class DGraph implements graph{
	private HashMap<Integer, node_data>  dataNode= new HashMap<Integer, node_data>();//20
	private HashMap<Integer, HashMap<Integer,edge_data>>  edgedataNode= new  HashMap<Integer, HashMap<Integer,edge_data>>();//20
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
		 ArrayList<node_data> c= new ArrayList<node_data>();
		 int i=0;
		 for ( HashMap.Entry   entry :  dataNode.entrySet() ) {
			c.add(i,dataNode.get(entry.getKey()));
			i++;
		 }
		return c;
	}
	public HashMap<Integer, node_data> getVHash() {
		 HashMap<Integer, node_data>  a= new HashMap<Integer, node_data>();//20


		 for ( HashMap.Entry   node_id :  dataNode.entrySet() ) {
			// System.out.println(node_id.getKey());
			 Nodedata s=new Nodedata(this.dataNode.get(node_id.getKey()));
			 
			a.put((Integer)node_id.getKey(), s);
			 //System.out.println(a.get((Integer)node_id.getKey()).getKey());
		 }
		 
		return a;
	}
	@Override
	public Collection<edge_data> getE(int node_id) {
		 ArrayList<edge_data> c= new ArrayList<edge_data>();
		 
		 int i=0;
		 for ( HashMap.Entry   entry :this.edgedataNode.get( node_id).entrySet() ) {
			c.add(i, this.edgedataNode.get(node_id).get(entry.getKey()));
			i++;
		 }
		
		return c;
		
	}
	public HashMap<Integer, HashMap<Integer,edge_data>>  getE() {
		return edgedataNode;
	}
	public HashMap<Integer, HashMap<Integer,edge_data>>  getE1() {
		 int i=0;
			HashMap<Integer, HashMap<Integer,edge_data>>  a= new  HashMap<Integer, HashMap<Integer,edge_data>>();//20
			 HashMap<Integer,edge_data> c= new HashMap<Integer,edge_data>();
			 for ( HashMap.Entry   node_id : edgedataNode.entrySet() ) {
				 
				 for ( HashMap.Entry   entry :edgedataNode.get(node_id.getKey()).entrySet() ) {
				// System.out.println(entry.getKey());
				c= new HashMap<Integer,edge_data>();
				Edgedata s= new Edgedata(this.edgedataNode.get(node_id.getKey()).get(entry.getKey()));
				 c.put(this.edgedataNode.get(node_id.getKey()).get(entry.getKey()).getDest(),s);
			 }
			a.put((Integer) node_id.getKey(), c);
			 }
			
			return a;
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
