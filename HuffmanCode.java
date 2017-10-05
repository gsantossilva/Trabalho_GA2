package Trabalho_GA2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class HuffmanCode {
	/*
	 * Implementação do Algoritmo de Huffman
	 * Compactação e Descompatação de um texto
	 * 
	 *  Para implementar o Algoritmo de Huffman é necessário conhecer as árvores binárias. 
	 */
	public static void main(String[] args) throws IOException {
		
		//	Testo que deseja codificar
		String texto = "O trabalho está quase 100%, faltam alguns detalhes como ler e gravar no arquivo txt, e arrumar o bug com com poucos caracteres. Guilherme Santos";
		
		//	Faz um array de int com os caracteres do texto
		int[] charFreqs = new int[256];
		for (char c : texto.toCharArray())
			charFreqs[c]++;
		
		//	Criar a arvore Huffman
		HuffmanTree tree = buildTree(charFreqs);
		
		//	Codifica a palavra para Binario
		String palavra = encode(tree, texto);
		
		//	Codifica a palavra em ASCII
		String ascii = binarioToASCII(palavra);
		
		//	Decodifica ASCII para String
		decode(tree, ascii);
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
				//System.out.println("PreÃ§o : "+ (a[4]));
				//System.out.println("");

				code = a[0];
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nÃ£o existe.");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}
		return code;
	}

	public static HuffmanTree buildTree(int[] charFreqs) {
		// Cria uma Fila de Prioridade 
		// A Fila será criado pela ordem de frequência da letra no texto
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		// Criar as Folhas da Árvore para cada letra 
		for (int i = 0; i < charFreqs.length; i++){
			if (charFreqs[i] > 0)
				trees.offer(new HuffmanLeaf(charFreqs[i], (char)i)); // Inser os elementos, nó folha, na fila de prioridade
		}
		// Percorre todos os elementos da fila
		// Criando a árvore binária de baixo para cima
		while (trees.size() > 1) {
			// Pega os dois nós com menor frequência
			HuffmanTree a = trees.poll(); // poll - Retorna o próximo nó da Fila ou NULL se não tem mais
			HuffmanTree b = trees.poll(); // poll - Retorna o próximo nó da Fila ou NULL se não tem mais

			// Criar os nós da árvore binária
			trees.offer(new HuffmanNode(a, b)); 
		}
		// Retorna o primeiro nó da árvore
		return trees.poll();
	}

	/* COMPACTAR a string 
	 *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
	 *     						  encode - Texto original 
	 *     Parâmetros de Saída: encodeText- Texto Compactado
	 */ 
	public static String encode(HuffmanTree tree, String name){
		assert tree != null;

		System.out.println("SÍMBOLO\tQUANTIDADE\tHUFFMAN CÓDIGO\n");
		printCodes(tree, new StringBuffer());

		String encodeText = "";
		for (char c : name.toCharArray()){
			encodeText+=(getCodes(tree, new StringBuffer(),c));
			//System.out.println("Encode Text -> "+encodeText);
		}
		System.out.println("\nTEXTO COMPACTADO\n");
		System.out.println("Binario -> "+encodeText+"\n");
		return encodeText; 			// Retorna o texto Compactado
	}

	public static String binarioToASCII(String b) {
		//		PRONTO ATÉ
		String ASCII = "";
		String salva = "";
		int contador = 0;
		int cont = 0;
		int a = 0;
		
		//	Cria um array de char para passar o codigo binario para o array
		char[] buffer = new char[b.length()];

		//	Cria um laço para passar todos bits para o array de char
		for(int i = 0; i < buffer.length; i++) {
			buffer[i] = b.charAt(i);
			//System.out.println("posicao -> "+i +" Valor -> "+buffer[i]);
		}
		
		//	Faz um laço para preencher a string salva com 8 bits para gravar em ASCII
		for(int i = 0; i < b.length(); i++) {
			contador = i;
			//	Se i for divisivel por 8 entra
			if((i % 8) == 0) {
				//	Este if é somente para a primeira gravação na string
				if(i == 8 ) {
					//	Guarda 8 bits na variavel salva
					for(int j = 0; j < i; j++) {
						salva += buffer[j];
					}
					//	Converter BINARIO PARA Decimal
					int c = Integer.parseInt(salva, 2);
					//System.out.println("C -> "+c);
					//	Converte de Decimal para ASCII
					Character ch = Character.toString((char)c).charAt(0);
					//System.out.println("Ch -> "+ch);
					
					//Convertendo código ASCII para representação binária, ERA SO PARA CONTROLE(TESTE)
					//String saida = Integer.toBinaryString(c);
					//System.out.println("BINARIO -> "+saida);//String com representação binária
					ASCII += ch;
					//	Zera a varivel salva;
					salva = "";
					//	Acumular de 8 em 8 no cont para fazer a verificação
					cont+=8;
				}
				//	Neste else if ira entrar no outros laços, gravando de 8 em 8 bits
				else if((i % 8) == 0 && i > 8) {
					//	Guarda 8 bits na variavel salva
					for(int j = cont; j < i; j++) {
						salva += buffer[j];
						//	Guarda o contador para fazer o laço com os ultimos bits, caso não feche 8 bits para serem gravados
						a = j;
					}
					//	Converter BINARIO PARA DECIMAL
					int c = Integer.parseInt(salva, 2);	
					//	Converte de Decimal para ASCII
					Character ch = Character.toString((char)c).charAt(0);
					ASCII += ch;
					salva = "";
					cont+=8;
				}
			}
		}
		//	Faz a gravação dos bits restantes
		for(int i = a+1; i <= contador; i++) {
			//	ATRIBUI UM PARA NAO PERDER OS ZEROS DA FRENTE
			if(a+1 == i)
				salva +=1;
			//System.out.println("salva "+salva);
			salva += buffer[i];
		}
		//System.out.println("Salva "+salva);
		int c = Integer.parseInt(salva, 2);					//Converter BINARIO PARA DECIMAL
		//System.out.println("C -> "+c);
		Character ch = Character.toString((char)c).charAt(0);
		//System.out.println("Decdasdasdasdasdsadimal "+ch);
		ASCII += ch;

		System.out.println("ASCII -> "+ASCII+"\n");
		return ASCII;
	}


	/* DECODIFICAR a string
	 *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
	 *     						  encode - Texto Compactado
	 *     Parâmetros de Saída: decodeText- Texto decodificado
	 */
	public static String decode(HuffmanTree tree, String name) {
		String saida = "";
		String binario = "";
		int cont = 0;

		assert tree != null;

		char[] buffer = new char[name.length()];

		//System.out.println("LENGTH "+name.length());

		for(int i = 0; i < name.length()-1; i++) {
			cont = i;
			int sub = 0;
			String guarda = "";

			//	Passa a string ASCII para um array de char
			buffer[i] = name.charAt(i);
			//System.out.println("posicao -> "+i +" Valor -> "+buffer[i]);
			//	Transforma ASCII em DECIMAL
			int c = buffer[i];
			//System.out.println("Vaii"+c);
			//	Converte DECIMAL EM BINARIO
			saida = Integer.toBinaryString(c);
			//System.out.println("Arrumar zeros -> "+saida);	

			if(saida.length() != 8) {		
				sub = 8 - saida.length();
				for(int j = 0; j < sub; j++) {
					guarda += 0;
				}
				guarda += saida;
				binario += guarda;
				//System.out.println("Foi "+binario);
			}
			else {
				//	Arrumar quando os bits estiverem corretos
				guarda += saida;
				binario += guarda;
				//System.out.println("FINAL "+binario);
			}
		}
		cont++; 
		
		//System.out.println("Contador -> "+cont);
		//System.out.println("Name length -> "+name.length());
		
		buffer[cont] = name.charAt(cont);
		int c = buffer[cont];
		//System.out.println("C "+c);
		saida = Integer.toBinaryString(c);
		//System.out.println("saidaaaa "+saida);

		String aux = "";
		for(int i = 1; i < saida.length(); i++) {
			aux += saida.charAt(i);
			//System.out.println("auxiliar "+aux);
		}

		binario += aux;
	
		System.out.println("Conversão reversa para Binário -> "+binario);
		
		//	ASCII TO BINARIO

		//String saida = Integer.toBinaryString(name);//Convertendo código ASCII para representação binária
		//System.out.println("BINARIO -> "+saida);//String com representação binária

		name = binario;
		String decodeText="";
		HuffmanNode node = (HuffmanNode)tree;
		for (char code : name.toCharArray()){
			if (code == '0'){ // Quando for igual a 0 é o Lado Esquerdo
				if (node.left instanceof HuffmanLeaf) { 
					decodeText += ((HuffmanLeaf)node.left).value; // Retorna o valor do nó folha, pelo lado Esquerdo  
					node = (HuffmanNode)tree; // Retorna para a Raíz da árvore
				}else{
					node = (HuffmanNode) node.left; // Continua percorrendo a árvore pelo lado Esquerdo 
				}
			}else if (code == '1'){ // Quando for igual a 1 é o Lado Direito
				if (node.right instanceof HuffmanLeaf) {
					decodeText += ((HuffmanLeaf)node.right).value; //Retorna o valor do nó folha, pelo lado Direito
					node = (HuffmanNode)tree; // Retorna para a Raíz da árvore
				}else{
					node = (HuffmanNode) node.right; // Continua percorrendo a árvore pelo lado Direito
				}
			}
		} // End for
		System.out.println("\nTexto decodificado -> "+decodeText+"\n");
		return decodeText; 						// Retorna o texto Decodificado
	}    

	/* 
	 * Método para percorrer a Árvore e mostra a tabela de compactação
	 *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
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
	 * Método para retornar o código compactado de uma letra (w)
	 *     Parâmetros de Entrada: tree - Raiz da Árvore de compactação
	 *     						  prefix - texto codificado com 0 e/ou 1
	 *     						  w - Letra
	 *     Parâmetros de Saída: prefix- Letra codificada
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
