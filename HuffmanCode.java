package Trabalho_GA2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import Book.Book;

public class HuffmanCode {
	/*
	 * Implementa��o do Algoritmo de Huffman
	 * Compacta��o e Descompata��o de um texto
	 * 
	 *  Para implementar o Algoritmo de Huffman � necess�rio conhecer as �rvores bin�rias. 
	 */
	public static void main(String[] args) throws IOException {

		String test = "imortal tricolor";
		int[] charFreqs = new int[256];
		for (char c : test.toCharArray())
			charFreqs[c]++;

		HuffmanTree tree = buildTree(charFreqs);

		decode(tree, encode(tree, test));
		//burn(encode(tree, test));
		//decode(tree, read("C:/ProgII/teste.txt"));
		//encode(tree, "imortal tricolor");

	}

	public static void burn(String name) throws IOException {
		// Grava livro nos arquivo txt
		File m;
		m = new File("C:/ProgII/teste.txt");

		try {
			FileWriter fw = new FileWriter(m, true);
			PrintWriter pw = new PrintWriter(fw);

			String line;
			//for (int i = 0; i <= name.length(); i++) {
			// if (i < k ) { 
			String palavra = name;
			pw.println(palavra);

			/*} else if (i == k) {
                 String palavra;
                 System.out.print("Digite uma palavra: ");
                 palavra = scanner.nextLine()-1;
                 pw.println(palavra);

             }*/

			//}
			pw.close();
		} catch (IOException e) {
			System.out.println("Erro ao gravar no arquivo.");
		}
	}

	public static String read(String file) {
		String filename = "C:/ProgII/teste.txt";
		String code = "";
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;
			String[] a;

			while ((line = br.readLine()) != null) {
				a = line.split(";");
				//System.out.println("Titulo : " + a[0]);
				//System.out.println("Isbn : "+ (a[1]));
				//System.out.println("Editora : "+ (a[2]));
				//System.out.println("Url : "+ (a[3]));
				//System.out.println("Preço : "+ (a[4]));
				//System.out.println("");

				code = a[0];

			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não existe.");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}
		return code;
	}

	public static HuffmanTree buildTree(int[] charFreqs) {
		// Cria uma Fila de Prioridade 
		// A Fila ser� criado pela ordem de frequ�ncia da letra no texto
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		// Criar as Folhas da �rvore para cada letra 
		for (int i = 0; i < charFreqs.length; i++){
			if (charFreqs[i] > 0)
				trees.offer(new HuffmanLeaf(charFreqs[i], (char)i)); // Inser os elementos, n� folha, na fila de prioridade
		}
		// Percorre todos os elementos da fila
		// Criando a �rvore bin�ria de baixo para cima
		while (trees.size() > 1) {
			// Pega os dois n�s com menor frequ�ncia
			HuffmanTree a = trees.poll(); // poll - Retorna o pr�ximo n� da Fila ou NULL se n�o tem mais
			HuffmanTree b = trees.poll(); // poll - Retorna o pr�ximo n� da Fila ou NULL se n�o tem mais

			// Criar os n�s da �rvore bin�ria
			trees.offer(new HuffmanNode(a, b)); 
		}
		// Retorna o primeiro n� da �rvore
		return trees.poll();
	}

	/* COMPACTAR a string 
	 *     Par�metros de Entrada: tree - Raiz da �rvore de compacta��o
	 *     						  encode - Texto original 
	 *     Par�metros de Sa�da: encodeText- Texto Compactado
	 */ 
	public static String encode(HuffmanTree tree, String name){
		assert tree != null;

		System.out.println("CODIFICANDO \n");
		System.out.println("S�MBOLO\tQUANTIDADE\tHUFFMAN C�DIGO\n");
		printCodes(tree, new StringBuffer());

		String encodeText = "";
		for (char c : name.toCharArray()){
			encodeText+=(getCodes(tree, new StringBuffer(),c));
		}
		System.out.println("\nTEXTO COMPACTADO\n");
		System.out.println(encodeText+"\n");
		return encodeText; // Retorna o texto Compactado
	}


	/* DECODIFICAR a string
	 *     Par�metros de Entrada: tree - Raiz da �rvore de compacta��o
	 *     						  encode - Texto Compactado
	 *     Par�metros de Sa�da: decodeText- Texto decodificado
	 */
	public static String decode(HuffmanTree tree, String name) {

		assert tree != null;

		System.out.println("\nTEXTO DECODIFICADO\n");

		String decodeText="";
		HuffmanNode node = (HuffmanNode)tree;
		for (char code : name.toCharArray()){
			if (code == '0'){ // Quando for igual a 0 � o Lado Esquerdo
				if (node.left instanceof HuffmanLeaf) { 
					decodeText += ((HuffmanLeaf)node.left).value; // Retorna o valor do n� folha, pelo lado Esquerdo  
					node = (HuffmanNode)tree; // Retorna para a Ra�z da �rvore
				}else{
					node = (HuffmanNode) node.left; // Continua percorrendo a �rvore pelo lado Esquerdo 
				}
			}else if (code == '1'){ // Quando for igual a 1 � o Lado Direito
				if (node.right instanceof HuffmanLeaf) {
					decodeText += ((HuffmanLeaf)node.right).value; //Retorna o valor do n� folha, pelo lado Direito
					node = (HuffmanNode)tree; // Retorna para a Ra�z da �rvore
				}else{
					node = (HuffmanNode) node.right; // Continua percorrendo a �rvore pelo lado Direito
				}
			}
		} // End for
		System.out.println(decodeText+"\n");
		return decodeText; // Retorna o texto Decodificado
	}    

	/* 
	 * M�todo para percorrer a �rvore e mostra a tabela de compacta��o
	 *     Par�metros de Entrada: tree - Raiz da �rvore de compacta��o
	 *     						  prefix - texto codificado com 0 e/ou 1
	 */
	public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;

		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf)tree;

			// Imprime na tela a Lista
			System.out.println(leaf.value + "\t" + leaf.frequency + "\t\t" + prefix);

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode)tree;

			// traverse left
			prefix.append('0');
			printCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length()-1);

			// traverse right
			prefix.append('1');
			printCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length()-1);
		}
	}

	/* 
	 * M�todo para retornar o c�digo compactado de uma letra (w)
	 *     Par�metros de Entrada: tree - Raiz da �rvore de compacta��o
	 *     						  prefix - texto codificado com 0 e/ou 1
	 *     						  w - Letra
	 *     Par�metros de Sa�da: prefix- Letra codificada
	 */
	public static String getCodes(HuffmanTree tree, StringBuffer prefix, char w) {
		assert tree != null;

		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf)tree;

			// Retorna o texto compactado da letra
			if (leaf.value == w ){
				return prefix.toString();
			}

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode)tree;

			// Percorre a esquerda
			prefix.append('0');
			String left = getCodes(node.left, prefix, w);
			prefix.deleteCharAt(prefix.length()-1);

			// Percorre a direita
			prefix.append('1');
			String right = getCodes(node.right, prefix,w);
			prefix.deleteCharAt(prefix.length()-1);

			if (left==null) return right; else return left;
		}
		return null;
	}

}