package sorting.assignment;

import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		Map<String, String> m = new HashMap<>();
		m.put("1", "a");
		m.put("2", "b");
		m.put("5", "e");
		m.put("3", "adf");
		System.out.println(m.toString());
		System.out.println(m.get("3"));
	}
}
