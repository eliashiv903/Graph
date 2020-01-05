package dataStructure;
import java.io.*;
public class Edgedata  implements edge_data, Serializable{

	private int keySrc;
	private int keyDest;
	private double weight;
	private String info="";
	private int tag=0;
	public Edgedata(edge_data p1){
		this.keySrc=p1.getSrc();
		this.keyDest=p1.getDest();
		this.weight=p1.getWeight();
		this.info=p1.getInfo();
		this.tag=p1.getTag();
	}
	public Edgedata(int keySrc,int keyDest,double weight){
		this.keySrc=keySrc;
		this.keyDest=keyDest;
		this.weight=weight;
	}
	@Override
	public int getSrc() {
		return keySrc;
	}

	@Override
	public int getDest() {
		return keyDest;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		info=s;
	}

	@Override
	public int getTag() {
		return tag;
	}
	public void setWeight(double weight) {
		if(weight>=0)
			this.weight=weight;
	}

	@Override
	public void setTag(int t) {
		tag=t;
	}

}
