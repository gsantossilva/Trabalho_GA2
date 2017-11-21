package Trab_GB_Estruturas;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Teste {
	public static void main(String[] args) {
		int x = 32768;
		long acumula = 0;

		long []Ascendente= new long[x];
		long []Descendente = new long [x];
		long []random = new long[x];
		long []random_rep = new long[x];

		//geraDescendente(Descendente, x);
		//geraRandom(random, x);
		//geraRandomRepetida(random_rep, x);

		System.out.println("n -> "+ x);

		long[] k = geraAscendente(Ascendente, x);
		long []tempo = new long[x];

		//	BUBBLE

		/*
		
		for(int i = 0; i < x; i++) {
			long start = System.nanoTime();
			long array[] = k.clone();
			bubbleSort(array);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Bubble -> "+calculaDesvio(x, tempo, acumula));

		//	INSERT

		for(int i = 0; i < x; i++) {
			long start = System.nanoTime();
			long insertionSort[] = k.clone();
			insertionSort(insertionSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Insert -> "+calculaDesvio(x, tempo, acumula));
		
		*/

		//	SELECTION

		for(int i = 0; i < x; i++) {
			long start = System.nanoTime();
			long selectionSort[] = k.clone();
			selectionSort(selectionSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Selection -> "+calculaDesvio(x, tempo, acumula));

		//	HEAP

		/*
		
		for(int i = 0; i < x; i++) {
			long start = System.nanoTime();
			long heapSort[] = k.clone();
			heapSort(heapSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Heap -> "+calculaDesvio(x, tempo, acumula));

		//	SHELL

		for(int i = 0; i < x; i++) {
			long start = System.nanoTime();
			long shellSort[] = k.clone();
			shellSort(shellSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Shell -> "+calculaDesvio(x, tempo, acumula));

		//	MERGE

		for(int i = 0; i < x; i++) {
			long start = System.nanoTime();
			long mergeSort[] = k.clone();
			mergeSort(mergeSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Merge -> "+calculaDesvio(x, tempo, acumula));

		//	QUICK

		for(int i = 0; i < x; i++) {
			long start = System.nanoTime();
			long quickSort[] = k.clone();
			quickSort(quickSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Quick -> "+calculaDesvio(x, tempo, acumula));

	*/

	}

	private static void quickSort(long[] quickSort) {
		// TODO Auto-generated method stub

	}

	private static void mergeSort(long[] mergeSort) {
		// TODO Auto-generated method stub

	}

	private static void shellSort(long[] shellSort) {
		// TODO Auto-generated method stub

	}

	private static void heapSort(long[] selectionSort) {
		// TODO Auto-generated method stub

	}

	public static long calculaDesvio(int qtd, long[] ar, long total) {
		long media = total/qtd;
		long desvio = 0;
		long array[] = ar.clone();

		for(int i = 0; i < qtd; i++) {
			//System.out.println("Array -> "+array[i]);
			array[i] = array[i] - media;
			//System.out.println("Array -> "+array[i]+"\n");
		}
		for(int i = 0; i < qtd; i++) {
			//System.out.println("Simple -> "+array[i]);
			array[i] = array[i]*array[i];
			//System.out.println("O dobro -> "+array[i]+"\n");
		}
		for(int i = 0; i < qtd; i++) {
			desvio += array[i];
		}

		desvio = desvio/(qtd-1);

		//System.out.println("Desvio -> "+desvio);

		desvio = (long) Math.sqrt(desvio);

		for(int i = 0; i < qtd; i++) {
			if(ar[i] > (desvio+ar[i]) && ar[i] < (desvio-ar[i])) 
				ar[i] = -1;
		}

		int cont = 0;
		long acumula = 0;
		for(int i = 0; i < qtd; i++) {
			if(ar[i] != -1) {
				acumula += ar[i];
				cont++;
			}
		}
		desvio = acumula / cont;

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

	public static void insertionSort(long[] a) {
		for (int i = 1; i < a.length; i++) {
			int j = i;
			long B = a[i];
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

	public static void selectionSort(long[] a) {
		int min= 0;
		for (int i = 0;i < a.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
				long temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}
	}

	/*public static <T extends Comparable<T>> void shellSort(T[] a) {
		int h = 1;
		while (h * 3 + 1 < a.length) h = 3 * h + 1;
		while (h > 0) {
			for (int i = h; i < a.length; i++) {
				T tmp = a[i];
				int j;
				for (j = i; j >= h && a[j - h].compareTo(tmp) > 0; j -= h) {
					a[j] = a[j - h];
				}
				a[j] = tmp;
			}
			h /= 3;
		}
	} */

}
