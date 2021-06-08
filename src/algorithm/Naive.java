package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Node;

public class Naive {
	public static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<Double> weightList = new ArrayList<Double>();
	public static ArrayList<Integer> numberList = new ArrayList<Integer>();
	public Naive() {
		// TODO Auto-generated constructor stub
	}
	public static void getPermutation(ArrayList<Integer> numberList, int start) {
		if (start >= numberList.size()) {
			ArrayList<Integer> a = (ArrayList<Integer>) numberList.clone();
			result.add(a);
		}
		else {
			for (int i= start; i< numberList.size(); i++) {
				swap(numberList, start, i);
				getPermutation(numberList, start+1);
				swap(numberList, start, i);
			}
		}
	}
	public static void getAllPath(ArrayList<Integer> numberList) {
		//Get all permutation
		getPermutation(numberList, 0); 
		//Adding starting node to every permutation
		for (int i=0; i< result.size(); i++) {
			result.get(i).add(result.get(i).get(0));
		}
	}
	public static void swap(ArrayList<Integer> numberList, int from, int to) {
		int tmp = numberList.get(from);
		numberList.set(from, numberList.get(to));
		numberList.set(to, tmp);
	}
	public static void fillWeightList() {
		for (int i=0; i< result.size(); i++) {
			double sum = 0;
			ArrayList<Integer> currentPerm = result.get(i);
			for (int j=0; j< currentPerm.size()-1; j++) {
				for (Edge edge: Graph.edgeList) {
					if ((edge.getSource().getNodeID() == currentPerm.get(j) && edge.getTarget().getNodeID() == currentPerm.get(j+1)) || (edge.getSource().getNodeID() == currentPerm.get(j+1) && edge.getTarget().getNodeID() == currentPerm.get(j)))
						sum += edge.getWeight();
					}
				}
			weightList.add(sum);
			sum = 0;
			}
		}
	public static double shortestWeight() {
		return Collections.min(weightList);
	}
	public static ArrayList<Integer> shortestPath() {
		int index = 0;
		for (int i=0; i< weightList.size(); i++) {
			if (weightList.get(i) == shortestWeight()) {
				index = i;
				break;
			}
		}
		return result.get(index);
	}
}
