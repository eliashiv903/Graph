package dataStructure;

import utils.Point3D;

public class Nodedata implements node_data {
private Point3D point;
private Integer key=null;
private double weight=999999999;
private int tag;
private String info;

public  Nodedata( Nodedata p) {
	this.point=p.point;
	this.weight=p.weight;
	this.tag=p.tag;
	this.info=p.info;
	this.key=p.key;
	
}
public  Nodedata( Point3D point,Integer key) {
	this.key=key;
	this.point=point;
}

public void setKey(Integer key) {
	this.key=key;
}
	@Override
	public int getKey() {
		
		return key;
	}

	@Override
	public Point3D getLocation() {
		// TODO Auto-generated method stub
		return point;
	}

	@Override
	public void setLocation(Point3D p) {
		point=p;
		
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
	public void setWeight(double w) {
		weight=w;
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(String s) {
		info=s;
		
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return tag;
	}

	@Override
	public void setTag(int t) {
		tag=t;
		
	}

}
