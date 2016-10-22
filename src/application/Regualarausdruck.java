package application;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class Regualarausdruck {

	
	
	@Test
	public void endofLine() {
		String pattern = "(.*\\n)|(.*#10)";
		if ("hsjakjd sacsac sakd\n".matches(pattern)) {
			
		} else {

			fail();
			System.out.println("I am here");
		}
	}
	
	@Test
	public void graph1(){
		String graph = "a    -> b;";
		String regex = ".*\\s->\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
			fail();
		}
	}
	
	@Test
	public void graph2(){
		String graph = "a    -- b;";
		String regex = ".*\\s--\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
			fail();
		}
	}
	
	@Test
	public void graphundirectedwithEdgeLength(){
		BufferedReader reader = null;
		String graph = "Paderborn -- Hamburg : 228;";
		String regex = "(.*)\\s*(--)\\s*(.*)\\s*(:)\\s*([0-9]*);";
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph3.gka"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(regex);
				System.out.println(matches);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	
	@Test
	public void graph4(){
		String graph = "v3 -- s : 2;";
		String regex = ".*\\s--\\s.*\\s:\\s[0-9];";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches"); 
			fail();  
		}
	}
	
	@Test
	public void graph5(){
		String graph = "a - b;";
		String regex = ".*\\s-\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
		}
	}
	
	@Test
	public void graph6(){
		String graph = "a - b;";
		String regex = ".*\\s-\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
		}
	}
	
	@Test
	public void graph7(){
		String graph = "a - b;";
		String regex = ".*\\s-\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
		}
	}
	
	@Test
	public void graph8(){
		String graph = "a - b;";
		String regex = ".*\\s-\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
		}
	}
	
	@Test
	public void graph9(){
		String graph = "a - b;";
		String regex = ".*\\s-\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
		}
	}
	
	@Test
	public void graph10(){
		String graph = "a - b;";
		String regex = ".*\\s-\\s.*;";
		if(graph.matches(regex)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
		}
	}
	

	}

