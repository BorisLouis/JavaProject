package mazeSolvingAlgorithm;


import java.util.ArrayList;

import maze.Maze;

public class Dijkstra {
		
	public static void calc(Maze myMaze) {
		if (checkMaze(myMaze.getDescription())) {
			
			boolean [][] nodeMap = myMaze.toBool(myMaze.getDescription());
			ArrayList<int[]> distances = new ArrayList<int[]>();
			
			for (int i = 0; i<nodeMap.length-1;i++) {
				
				for (int j = 0; j<nodeMap[0].length-1;j++) {
					
					if (nodeMap[nodeMap.length-2-i][1+j] == false) {//-2 and j+1 because we want the first node to be at 0,0
						
						if (i==0 & j==0) {
							
							distances.add(new int[] {i*(nodeMap.length-2)+j,0});
						}else {
						distances.add(new int[] {i*(nodeMap.length-2)+j,Integer.MAX_VALUE});
						}
						
					}else {}
				}
			}
			boolean[] sptSet = new boolean[distances.size()];
			
			int nSPTTrue = countTrue(sptSet);
			int count = 0;
			
			//loop until all nodes are considered
			while (nSPTTrue<distances.size()) {
				
				count++;
				//find closest neighbour not included in the set
				int index2Vertex = findMin(distances,sptSet);
				//include it in the set
				sptSet[index2Vertex] = true;
				
				//loop through adjacent vertex and update distance
				int[] idx2adj = findAdj(distances,index2Vertex,nodeMap.length-2,sptSet);
				int distVertex = 1;//2 step here equals 1 step in normal player maze but we'll /2 later
				for (int m = 0; m<idx2adj.length; m++) {
					if(idx2adj[m]>0) {
						if(distances.get(index2Vertex)[1]+distVertex<distances.get(idx2adj[m])[1]) {
							distances.get(idx2adj[m])[1] = distances.get(index2Vertex)[1]+distVertex;
						}
					}					
				}
				
				if(count>10000) {
					break;// break the loop during testing of algorithm
				}
				nSPTTrue = countTrue(sptSet);
			}
			
		}else {
			System.err.println("Calculation aborted, the maze contains element (e.g. Breakable, fake wall) that cannot currently be handled by the implemented Dijkstra algorithm");
		}
	}
	
	private static int[] findAdj(ArrayList<int[]> distances,int idx2Vertex,int nodeMapSize, boolean[] sptSet) {
		int[] idx = {nodeMapSize,-nodeMapSize,+1,-1};
		int[]idx2adj = {-1,-1,-1,-1};
		for (int i=0; i<idx.length;i++) {

			if (distances.get(idx2Vertex)[0]+idx[i]<idx[i]*idx[i] & distances.get(idx2Vertex)[0]+idx[i]>0) {
			
				for (int j=0; j<distances.size();j++) {
					if (distances.get(j)[0]==distances.get(idx2Vertex)[0]+idx[i]) {
						if (sptSet[j] ==false) {
						idx2adj[i] = j;
						}
					}
				}
			}
		}
		
		return idx2adj;
	}

	public static boolean checkMaze(ArrayList<ArrayList<String>> mazeDescription){
		boolean startCalc = true;
		
		for (int i=0; i<mazeDescription.size();i++) {
			if (startCalc) {
				
				for(int j=0; j<mazeDescription.get(i).size();j++) {
					if (startCalc) {
						if(mazeDescription.get(i).get(j).equals("fake")|mazeDescription.get(i).get(j).equals("breakable")|mazeDescription.get(i).get(j).equals("door")) {
							startCalc = false;
						}else {}
					}else {break;}
				}
			}else {break;}
		}
		return startCalc;
	}
	
	public static int findMin(ArrayList<int[]> distances,boolean [] spt) {
		int firstIdx = 0;
		//only consider element that are still in the set
		for (int j=0; j<spt.length; j++) {
			
			if (spt[j]==false) {
			 firstIdx = j;
			 break; //Stop once the first non-included element  is found
			}
		}
		
		int val = distances.get(firstIdx)[1];
		int idx=firstIdx;
		
			for (int i = 0; i<distances.size();i++) {
				if(spt[i]==false) {
				int val2cmp = distances.get(i)[1];
				int idx2cmp = i;
				if (val2cmp<val) {
					val = val2cmp;
					idx = idx2cmp;
				}
			}
			}
			return idx;
	}
			
	public static int countTrue(boolean [] spt) {
		int count=0;
		for (int i = 0;i < spt.length;i++) {
			if (spt[i]) {
				count++;
			}
		}
		return count;
	}
			
			
		
		
		
	
//	public static boolean [] extractNodeMap(Maze maze) {
//		
//		boolean[][] nodeMap = maze.toBool(maze.getDescription());
//		
//		//printing
//		for (boolean[] u:nodeMap)
//		{
//			String toPrint ="";
//			for(boolean v:u)
//			{
//				String tmp = "";
//				if (v==false) {
//					tmp = "0";
//				}else {
//					 tmp  ="1";
//				}
//				 toPrint =toPrint+tmp;
//			}				
//			System.out.println(toPrint+"\n");				
//		}		
//		

		
	//	return null;
//	}

}
