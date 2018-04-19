package fundamentals.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class UF {
	private int[] id;
	private int count;
	
	public UF(int N) {
		count = N;
		id = new int[N];
		for (int i=0; i<N; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	
//	//the quick find algorithm
//	public int find(int p) {
//		return id[p];
//	}
//	
//	public void union(int p, int q) {
//		int pID = find(p);
//		int qID = find(q);
//		
//		if (pID == qID)
//			return;
//		for(int i=0; i<id.length; i++) {
//			if(id[i]==pID)
//				id[i] = qID;
//		}
//		count --;
//	}
	
	//quick union algorithms
	public int find(int p) {
		while(p != id[p])
			p = id[p];
		return p;
	}
	
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if (pRoot == qRoot)
			return;
		
		id[pRoot] = qRoot;
		count--;
	}
	
	@Override
	public String toString() {
		String s = "[";
		for (int i=0; i<id.length; i++) {
			if (i == 0)
				s += id[i];
			else
				s += "," + id[i];
		}
		return s+"]";
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("data//largeUF.txt");
		Scanner sc = new Scanner(file);
		int N = sc.nextInt();
		//count for display
		int dspCount = 0;
		Stopwatch sw = new Stopwatch();
		UF uf = new UF(N);
		while (sc.hasNextLine()) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			if (uf.isConnected(p, q))
				continue;
			uf.union(p, q);
			dspCount++;
			if (dspCount%100000 == 0)
				System.out.println("handled:"+dspCount);
			//StdOut.println(p + " " + q);
		}
		double time = sw.elapsedTime();
		StdOut.println(uf.count() + " components");
		System.out.println("time used:" + time);
		sc.close();
//		StdOut.println(uf);
	}
	
	
}
