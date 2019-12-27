package dataStructure;

import utils.Point3D;

public class Edgedata implements edge_data{
	int src, dest;
	double weight;
	String info;
	int tag;
	
	public  Nodedata( ) {
}
	public Edgedata(int src2, int dest2, double w) {
		 src2= src;
		 dest2= dest;
		 w= weight;
	}
	@Override
	public int getSrc() { 	
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		
		 return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
		
	}

	@Override
	public int getTag() {
		
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}

}
