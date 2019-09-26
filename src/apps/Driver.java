package apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import structures.Graph;
import structures.Vertex;

public class Driver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file= "graph3.txt";
		Graph g2= new Graph(file);
		PartialTreeList ptl= MST.initialize(g2);
		Iterator<PartialTree> iter = ptl.iterator();
		   while (iter.hasNext()) {
		       PartialTree pt = iter.next();
		      System.out.println(pt.toString());
		   }
		   ArrayList<PartialTree.Arc> mst=MST.execute(ptl);
//		   System.out.println();
//		   String name="E";
//		   Vertex v= new Vertex(name);
//		   ptl.removeTreeContaining(v);
//		   Iterator<PartialTree> iter2 = ptl.iterator();
//		   while (iter2.hasNext()) 
//		   {
//		       PartialTree pt = iter2.next();
//		       System.out.println(pt.toString());
//		   }
		   System.out.println(mst.toString());
	}

}
