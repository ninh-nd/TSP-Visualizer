package algorithm;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.MyNode;

public class MST {
	private ArrayList<MyNode> nodes = new ArrayList<MyNode>();
	private ArrayList<Edge> minimumspanningtree = new ArrayList<Edge>();
	
	private double mstWeight = 0;
    private double mstTourLength = 0;
    
    public MST (List<MyNode> list){
    	for(MyNode node : list) {
    		this.nodes.add(node);
    	}
    }
    
    public ArrayList<Edge> computeMST() {
    	
    	MyNode start = nodes.get(0);
    	start.setShortestEdge(start);
    	
    	//keep track
    	ArrayList<MyNode> remains = new ArrayList<MyNode>();
    	
    	//set priorities
    	for (MyNode n: nodes) {
    		double startPrio = n.getDistanceTo(start);
    		n.setShortestEdge(start, startPrio);
    		remains.add(n);
    	}
    	
    	MyNode current = start;
    	remains.remove(start);
    	
    	while (remains.size() > 0)
    	{
    		MyNode next = findLowestPriority(remains);
    		minimumspanningtree.add(next.getShortestEdge());
    		
    		MyNode startNext = next.getShortestEdge().getTarget();
    		startNext.addChild(next);
    		
    		mstWeight += next.getPriority();
    		
    		current = next;
    		
    		remains.remove(next);
    		
    		for (MyNode s : remains) {
    			
    			double potential = s.getDistanceTo(next);
    			
    			if (potential < s.getPriority()) {
    				s.setShortestEdge(next, potential);
    			}
    		}
    	}
    	return minimumspanningtree;
    }
    
    public ArrayList<Edge> walkMST(){
    	ArrayList<MyNode> nodeFound = new ArrayList<MyNode>();
    	
    	nodeFound.add(nodes.get(0));
    	
    	DFStraversal(nodeFound, nodes.get(0));
    	
    	removeDuplicates(nodeFound);
    	
    	return makeTour(nodeFound);
    }
    
    private void DFStraversal(ArrayList<MyNode> nodeFound, MyNode root) {
    	root.setVisited(true);
    	for(MyNode adjacent : root.getChildren()) {
    		if(!adjacent.visited()) {
    			nodeFound.add(adjacent);
    			DFStraversal(nodeFound, adjacent);
    		}
    	}
    }
    
    private void removeDuplicates(ArrayList<MyNode> ori) {
    	ArrayList<MyNode> uniq = new ArrayList<MyNode>();
    	
    	for(int i=0; i < ori.size(); i++)
    	{
    		MyNode cur = ori.get(i);
    		if (uniq.contains(cur)) {
    			ori.remove(i);
    		}
    		else {
    			uniq.add(cur);
    		}
    	}
    }
    
    private ArrayList<Edge> makeTour(ArrayList<MyNode> path){
    	ArrayList<Edge> tour = new ArrayList<Edge>();
    	for(int i=1; i < path.size(); i++) {
    		MyNode first = path.get(i-1);
    		MyNode second = path.get(i);
    		tour.add(new Edge(first, second));
    		mstTourLength =+ first.getDistanceTo(second);
    	}
    	return tour;
    }
    
    public double getWeightMST() {
    	return this.mstWeight;
    }
    
    public double getTourLength() {
    	return this.mstTourLength;
    }
    
    public ArrayList<MyNode> getNodes(){
    	return this.nodes;
    }
    
    private MyNode findLowestPriority(ArrayList<MyNode> remains) {
    	MyNode lowestPriority = remains.get(0);
    	
    	for (MyNode next : remains) {
    		if(next.getPriority() < lowestPriority.getPriority()) {
    			lowestPriority = next;
    		}
    	}
    	return nodes.get(nodes.indexOf(lowestPriority));
    }
    
    
}
