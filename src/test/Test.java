package test;

import model.dao.PartidasDAO;

public class Test {
	public static void main(String[] args) {
		PartidasDAO partidas = new PartidasDAO();
		
		System.out.println(partidas.read());
	}
}
