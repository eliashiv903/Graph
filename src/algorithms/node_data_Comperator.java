package algorithms;
import java.util.Comparator;

import dataStructure.node_data;
public class node_data_Comperator implements Comparator<node_data> {




	///sort from lital Weight to big
	public node_data_Comperator() {;}
	public  int compare(node_data o1, node_data o2) {
				return Double.compare(o1.getWeight(), o2.getWeight());
	}


}