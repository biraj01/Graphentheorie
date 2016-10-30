package tests;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class Regualarausdruck {


	private static final String directed = "(.*)\\s*(->)\\s*([^:\\s]*);";
	private static final String undirected = "(.*)\\s*(--)\\s*([^:\\s]*);";
	private static final String directedWeightedGraph = "(.*)\\s*(->)\\s*([^:]*)\\s*(:?)\\s*(\\(.+\\)|[0-9]+);";
	private static final String undirectedWeightedGraph = "(.*)\\s*(--)\\s*([^:]*)\\s*(:?)\\s*([0-9]*);";
	
	@Test
	public void graph1(){
		String graph = "a    -> b;";
		if(graph.matches(directed)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
			fail();
		}
	}
	
	@Test
	public void graph2(){
		String graph = "a    -- b;";
		if(graph.matches(undirected)){
			System.out.println("matches ");
		}else{
			System.out.println("doesnot matches");
			fail();
		}
	}
	
	@Test
	public void graphundirectedwithEdgeLength(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph3.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(undirectedWeightedGraph);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	
	@Test
	public void graph4(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph4.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(undirectedWeightedGraph);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	
	
	@Test
	public void graph5(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph5.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(undirectedWeightedGraph);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	
	
	@Test
	public void graph6(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph6.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(directedWeightedGraph);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	
	@Test
	public void graph7(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph7.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(undirectedWeightedGraph);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	
	@Test
	public void graph8(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph8.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(undirectedWeightedGraph);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	
	@Test
	public void graph9(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph3.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(undirected);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	@Test
	public void graph10(){
		BufferedReader reader = null;
		boolean matches = false;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Biraj\\workspace\\GKA_Praktikummitjavafx\\asserts\\graph10.gka"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while((line = reader.readLine()) != null){
				matches = line.matches(undirectedWeightedGraph);
				System.out.println(matches);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			if(matches == false){
				fail();
		}
	}
	

	}

