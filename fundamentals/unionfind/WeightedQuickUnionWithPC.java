package fundamentals.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class WeightedQuickUnionWithPC {
	private int[] id;
	private int[] w;
	private int count;
	private int cost;
	
	public WeightedQuickUnionWithPC(int N) {
		count = N;
		id = new int[N];
		for (int i=0;i<N;i++) {
			id[i] = i;
		}
		w = new int[N];
		for (int i=0; i<N; i++) {
			w[i] = 1;
		}
	}
	
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int find(int p) {
		while(id[p]!=p) {
			id[p] = id[id[p]];
			p = id[p];
			cost += 3;
		}
		return p;
	}
	
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (w[pRoot]<w[qRoot]) {
			id[pRoot] = qRoot;
			w[qRoot] += w[pRoot];
			
		}else {
			id[qRoot] = pRoot;
			w[pRoot] += w[qRoot];
		}
		cost++;
//		//the more balance weighted way
//		if (w[pRoot]<w[qRoot]) {
//			id[pRoot] = qRoot;
//			
//		}else if (w[pRoot] > w[qRoot]){
//			id[qRoot] = pRoot;
//		}else {
//			//w[pRoot] = w[qRoot];then plus one tree-height
//			id[qRoot] = pRoot;
//			w[pRoot] += 1;
//		}
		
		count--;
	}
	
	@Override
	public String toString() {
//		String s = "[";
//		for (int i=0; i<id.length; i++) {
//			if (i == 0)
//				s += id[i];
//			else
//				s += "," + id[i];
//		}
		return "cost:"+cost;
	}
	
	public void printRootAndWeight() {
		ArrayList<Integer> roots = new ArrayList<Integer>();
		for (int i = 0; i<id.length; i++) {
			if(id[i] == i)
				roots.add(i);
		}
		for(int i:roots) {
			System.out.println("root id: " + id[i] + ", weight w[]:" + w[i]);
		}
	}
	
	public int count() {
		return count;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("data/largeUF.txt");
		Scanner sc = new Scanner(file);
		int N = sc.nextInt();
		//count for display
//		int dspCount = 0;
		Stopwatch sw = new Stopwatch();
		WeightedQuickUnionWithPC uf = new WeightedQuickUnionWithPC(N);
		while (sc.hasNextLine()) {
			int p = sc.nextInt();
			int q = sc.nextInt();
//			dspCount++;
//			if (dspCount%100000 == 0)
//				System.out.println("handled:"+dspCount);
			if (uf.isConnected(p, q))
				continue;
			uf.union(p, q);
//			StdOut.println(p + " " + q);
		}
		double time = sw.elapsedTime();
		StdOut.println(uf.count() + " components");
		System.out.println(uf+",time:"+time);
		sc.close();
//		StdOut.println(uf);
	}
	
}
