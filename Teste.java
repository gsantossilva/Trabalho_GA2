package Trab_GB;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Teste {
	public static void main(String[] args) {
		int x = 9999;
		long acumula = 0;
		long []Ascendente= new long[x];
		long []Descendente = new long [x];
		long []random = new long[x];
		long []random_rep = new long[x];

		long[] k = geraAscendente(Ascendente, x);
		long []tempo = new long[x];
		
		for(int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long array[] = k.clone();
			bubbleSort(array);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
			System.out.println("Tempo de ordenação BubbleSort "+total);
		}
		acumula = acumula/x;
		//System.out.println("Media -> "+acumula);
		
		//CALCULAR DESVIO PADRAO
		calculaDesvio(x, tempo, acumula);
		
		//geraDescendente(Descendente, x);
		//geraRandom(random, x);
		//geraRandomRepetida(random_rep, x);
	}
	
	public static long calculaDesvio(int qtd, long[] ar, long total) {
		long media = total/qtd;
		long desvio = 0;
		long array[] = ar.clone();
		for(int i = 0; i < qtd-1; i++) {
			array[i] = array[i] - media;
		}
		for(int i = 0; i < qtd-1; i++) {
			array[i] = array[i]*array[i];
		}
		for(int i = 0; i < qtd-1; i++) {
			desvio += array[i]*array[i];
		}
		desvio = desvio/(qtd-1);
		desvio = (long) Math.sqrt(desvio);
		for(int i = 0; i < qtd-1; i++) {
			if(ar[i] < (desvio+ar[i]) && ar[i] > (desvio-ar[i])) {
				ar[i] = ar[i];
			}else {
				ar[i] = -1;
			}
		}
		int cont = 1;
		long acumula = 0;
		for(int i = 0; i < qtd-1; i++) {
			if(ar[i] != -1) {
				acumula += ar[i];
				cont+=1;
			}
		}
		desvio = acumula / cont-1;
		
		System.out.println(desvio);
		/*
		 https://www.easycalculation.com/pt/statistics/standard-deviation.php
		 */
		return desvio;
	}

	public static long[] geraAscendente(long array[], int size) {
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

	public static int[] geraRandomRepetida(int array[], int size) {
		int count = 0;
		int valor = 1;
		for(int i = 0; i < size; i++) {
			if(count < 19) {
				array[i] = valor;
				count++;
			}else {
				array[i] = valor;
				count = 0;
				valor++;
			}
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

	public static void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int j = i;
			int B = a[i];
			while ((j > 0) && (a[j - 1] > B)) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = B;
		}
	}

	public static long[] bubbleSort(long[] a) {
		int i = a.length - 1;
		while (i > 0) {
			int last = 0;
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					long temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					last = j;
				}
			}
			i = last;
		}
		return a;
	}

	public static void selectionSort(int[] a) {
		int min= 0;
		for (int i = 0;i < a.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}

	}
}
