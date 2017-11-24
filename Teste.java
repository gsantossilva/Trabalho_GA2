import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Teste {
	public static void main(String[] args) {

		/*
		 * O valor de x determina qual o tamanho do array
		 */

		int x = 128;
		long acumula = 0;

		/*
		 * Os quatro tipos de array
		 */

		long []Ascendente= new long[x];
		long []Descendente = new long [x];
		long []random = new long[x];
		long []random_rep = new long[x];

		System.out.println("n -> "+ x);

		/*
		 * Preenche o array conforme os tipos
		 */

		long[] k = geraAscendente(Ascendente, x);
		//long[] k = geraDescendente(Descendente, x);
		//long[] k = geraRandom(random, x);
		//long[] k = geraRandomRepetida(random_rep, x);

		long []tempo = new long[x];

		//	BUBBLE

		for(int i = 0; i < 10; i++) {
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

		for(int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long insertionSort[] = k.clone();
			insertionSort(insertionSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Insert -> "+calculaDesvio(x, tempo, acumula));

		//	SELECTION

		for(int i = 0; i < 10; i++) {
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

		for(int i = 0; i < 10; i++) {
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

		for(int i = 0; i < 10; i++) {
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

		for(int i = 0; i < 10; i++) {
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

		for(int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long quickSort[] = k.clone();
			quickSort(quickSort);
			long finish = System.nanoTime();
			long total = finish - start;
			tempo[i] = total;
			acumula += total;
		}
		System.out.println("Quick -> "+calculaDesvio(x, tempo, acumula));

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

	/*
	 * Faz o calculo do desvio padrão
	 */

	public static long calculaDesvio(int qtd, long[] ar, long total) {
		//	Faz a média
		long media = total/qtd;
		long desvio = 0;
		long array[] = ar.clone();

		for(int i = 0; i < qtd; i++) {
			//	Para encontrar a variância, subtraia a média de cada um dos valores
			array[i] = array[i] - media;
		}
		for(int i = 0; i < qtd; i++) {
			//	Eleva ao quadrado
			array[i] = array[i]*array[i];
		}
		for(int i = 0; i < qtd; i++) {
			//	Acumulta todos valores na varievel desvio
			desvio += array[i];
		}

		//	Divida a soma dos quadrados por (n-1) 
		desvio = desvio/(qtd-1);

		//	Encontra a raiz quadrada da variância, 
		desvio = (long) Math.sqrt(desvio);

		for(int i = 0; i < qtd; i++) {
			//	Verifica se esta entre o desvio padrão maximo e minimo
			if(ar[i] > (desvio+ar[i]) && ar[i] < (desvio-ar[i])) 
				ar[i] = -1;
		}

		int cont = 0;
		long acumula = 0;
		for(int i = 0; i < qtd; i++) {
			//	Pega somente os valores que estão entre o padrão maximo e minimo
			if(ar[i] != -1) {
				//	Acumula todos o valores
				acumula += ar[i];
				//	Contador para dividir pelos valores validos
				cont++;
			}
		}

		//	Faz a divisão do total pelo numero de valores validos
		desvio = acumula / cont;

		return desvio;
	}

	//	Gera valores do 1 até o size +1
	public static long[] geraAscendente(long array[], int size) {
		for(int i = 0; i < size; i++) {
			array[i] = i+1;
			System.out.println(array[i]);
		}
		return array;
	}

	//	Gera valores do size -1 até 0 
	public static long[] geraDescendente(long array[], int size) {
		size = size -1;
		for(int i = size; i != 0; i--) {
			array[i] = i;
			//System.out.println(array[i]);
		}
		return array;
	}

	//	Gera valores unicos aleatórios
	public static long[] geraRandom(long array[], int size) {
		//	Preenche valores unicos no vetor
		for(int i = 0; i < size; i++) {
			array[i] = i+1;
		}
		//	Embaralha todos os dados do vetor
		shuffleArray(array);
		return array;
	}

	//	Gera valores aleatórios repetidos
	public static long[] geraRandomRepetida(long array[], int size) {
		int count = 0;
		int valor = 1;
		//	Preenche com 20 valores repetidos
		for(int i = 0; i < size; i++) {
			if(count < 19) {
				array[i] = valor;
				count++;
				//	Incrementa 1 no valor do vetor após 20 inserções iguais
			}else {
				array[i] = valor;
				count = 0;
				valor++;
			}
		}
		//	Embaralha todos os dados do vetor
		shuffleArray(array);
		return array;
	}


	//	Metodo que faz a troca de posições do array
	static void shuffleArray(long[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			long a = ar[index];
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
}
