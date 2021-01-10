package fr.m3105.projetmode.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Random;

import org.junit.jupiter.api.Test;

class MultiThreadTranslateTest {
	
	@Test
	void testSurCraneValide() {
		Model crane1 = new Model(new File("exemples/appel.ply"));
		Model crane2 = new Model(new File("exemples/appel.ply"));
		
		Random rand = new Random();
		double[] vector = new double[] {rand.nextDouble(),rand.nextDouble(),rand.nextDouble()};
		
		crane1.translate(vector,false);
		crane2.translateMultiThread(vector);

		assertTrue(pointsEquals(crane1.points, crane2.points));
//		assertTrue(crane1.points.equals(crane1.points));
//		assertEquals(crane1.points, crane2.points);
	}
	
	
	@Test
	void testSurCraneTemps() {
		long debut;
		long fin;
		
		long resSansThread;
		long resAvecThread;
		
		Random rand = new Random();
		
		double[] vector = new double[] {rand.nextDouble(),rand.nextDouble(),rand.nextDouble()};
		
		Model crane1 = new Model(new File("exemples/crane.ply"));
		Model crane2 = new Model(new File("exemples/crane.ply"));
		
		debut = System.nanoTime();
		for(int nbDeIte = 0; nbDeIte < 1000; nbDeIte++) {
			crane1.translate(vector,false);
		}
		fin = System.nanoTime();
		resSansThread = fin - debut;
		
		debut = System.nanoTime();
		for(int nbDeIte = 0; nbDeIte < 1000; nbDeIte++) {
			crane2.translateMultiThread(vector);
		}
		fin = System.nanoTime();
		resAvecThread = fin - debut;
		System.out.println("Test MultiThreadTranslate");
		System.out.println("resSansThread =["+resSansThread+"], moyenne de temps par calcule =["+resSansThread / 1000+"]");
		System.out.println("resAvecThread =["+resAvecThread+"], moyenne de temps par calcule =["+resAvecThread / 1000+"]");
		if(resAvecThread <= resSansThread)
			System.out.println("--- MultiThread "+(resSansThread +0.0 )/ resAvecThread+" x plus rapide que sans le multi ---");
		else
			System.out.println("--- MultiThread "+(resAvecThread +0.0 )/ resSansThread +" x plus lent que sans le multi ---");
		assertTrue(true);
	}

	private boolean pointsEquals(double[][] p1,double[][] p2) {
		if(p1.length != p2.length)
			return false;
		if(p1[0].length != p2[0].length)
			return false;
		
		for(int i = 0; i < p1.length;i++) {
			for(int y = 0; y < p1[0].length; y++) {
				if(p1[i][y] != p2[i][y])
					return false;
			}
		}
		return true;
	}
}
