package aufgabe1;

import java.util.regex.Pattern;

public class TestRegualrasudruck {

	public static void main(String[] args) {
		String graph = "a -> b;";
		String regex = ".*\\s->\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
		}

	}

}
