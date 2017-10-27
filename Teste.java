import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Teste {
	public static void main(String[] args) {
		int x = 128;
		int []Ascendente= new int[x];
		int []Descendente = new int [x];
		int []random = new int[x];
		int []random_rep = new int[x];

		long start = System.currentTimeMillis();
		bubbleSort(geraAscendente(Ascendente, x));
		long finish = System.currentTimeMillis();
		long total = finish - start;
		//System.out.println("Tempo de ordenação BubbleSort "+finish);
		//System.out.println("start "+start);
		
		geraDescendente(Descendente, x);
		geraRandom(random, x);
		geraRandomRepetida(random_rep, x);
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

	public static void bubbleSort(int[] a) {
		int i = a.length - 1;
		while (i > 0) {
			int last = 0;
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					last = j;
				}
			}
			i = last;
		}
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
