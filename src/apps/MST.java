package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		
		PartialTreeList ptl= new PartialTreeList();
		for(int i=0; i<graph.vertices.length;i++)
		{
			PartialTree x= new PartialTree(graph.vertices[i]);
			graph.vertices[i].parent=x.getRoot();
			Vertex.Neighbor ptr= graph.vertices[i].neighbors;
			while(ptr!=null)
			{
				PartialTree.Arc A= new PartialTree.Arc(graph.vertices[i], ptr.vertex, ptr.weight);
				x.getArcs().insert(A);
				ptr=ptr.next;
			}
			
			ptl.append(x);
		}
		return ptl;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		ArrayList<PartialTree.Arc> mst= new ArrayList<PartialTree.Arc>();
		while(ptlist.size()>1)							// Step 9: if There is more than 1 item in ptlist, go to step 3 (into loop)
		{
			PartialTree PTX=ptlist.remove();            // Step 3: Remove first PT which is PTX
			MinHeap<PartialTree.Arc> PQX=PTX.getArcs(); // Step 3: PQX is PTX Heap
			PartialTree.Arc a= PQX.deleteMin();         // Step 4: Remove the highest Priority arc in PQX which is the top a
			Vertex Vptr=a.v2;					// Step 5: Compare if vertex belongs to PTX by comparing parent
			while(Vptr.parent!=Vptr) 
			{
				Vptr=Vptr.parent;
			}
			Vertex Vptr2=PTX.getRoot();
			while(Vptr.name.equals(Vptr2.name)) // Step 5: Compares parent of vertex to root of PTX
			{
				a=PQX.deleteMin();						// Step 5: Means v2 belongs to PTX so move to next priority arc
				Vptr=a.v2.parent;
				while(Vptr.parent!=Vptr) 
				{
					Vptr=Vptr.parent;
				}
			}
			mst.add(a);									// Step 6: Component of MST so add it
			PartialTree PTY=ptlist.removeTreeContaining(a.v2); // Step 7: finds PT which v2 belongs to and removes it from L
			MinHeap<PartialTree.Arc> PQY= PTY.getArcs();       // Step 7: PQY is PTY's priority heap
			PTX.merge(PTY);								// Step 8: Combine PTX and PTY
			ptlist.append(PTX);							// Step 8: Add newly merged PTX to end of ptlist
		}
		return mst;
	}
}
