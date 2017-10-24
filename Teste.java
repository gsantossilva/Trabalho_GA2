package Trab_GB_Estruturas;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Teste {
	public static void main(String[] args) {
		int x = 128;
		int[] Ascendente= new int[x];
		int []Descendente = new int [x];
		int []random = new int[x];
		int []random_rep = new int[x];

		geraAscendente(Ascendente, x);
		geraDescendente(Descendente, x);
		geraRandom(random, x);
	}

	public static int[] geraAscendente(int array[], int size) {
		for(int i = 0; i < size; i++) {
			array[i] = i+1;
			//System.out.println(array[i]);
		}
		return array;
	}

	public static int[] geraDescendente(int array[], int size) {
		size = size -1;
		for(int i = size; i != 0; i--) {
			array[i] = i;
			//System.out.println(array[i]);
		}
		return array;
	}

	public static int[] geraRandom(int array[], int size) {
		for(int i = 0; i < size; i++) {
			array[i] = i+1;
			//System.out.println("Valor -> "+i);
		}
		shuffleArray(array);
		for(int i = 0; i < size; i++) {
			//System.out.println("Posição -> "+i +" Valor -> " +array[i]);
		}
		return array;
	}

	static void shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
}
