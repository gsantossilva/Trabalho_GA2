package Trabalho_GA2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

//	Guilherme Santos, Mauricio Pretto, Jose Grasulino

public class HuffmanCode {

	// Implementação do Algoritmo de Huffman
	// Compactação e Descompatação de um texto


	//	Metodo main

	public static void main(String[] args) throws Exception {

		//	Endereco do arquivo txt
		String endereco = "C:/ProgII/teste.txt";

		//	Le o arquivo txt
		String texto = read(endereco);

		//	Exibi o texto
		System.out.println("O texto que esta no arquivo txt -> "+texto+"\n");

		//	Faz um array de int com os caracteres de ASCII
		int[] charFreqs = new int[256];
		for (char c : texto.toCharArray())
			charFreqs[c]++;

		//	Criar a arvore Huffman
		HuffmanTree tree = buildTree(charFreqs);

		//	Codifica a palavra para Binario
		String palavra = encode(tree, texto);

		//	Codifica a palavra em ASCII
		String ascii = binarioToASCII(palavra);

		//	Endereço que deseja salvar o texto codificado
		String codificado = "C:/ProgII/codificado.txt";

		//	Grava no arquivo txt o texto codificado
		write(ascii, codificado);

		//	Aqui buga na hora de ler ASCII NO TXT

		//	Le o texto codificado
		String codigo = leASCII(codificado);	//	Aqui ele da esta com problema, na hora de ler o ASCII no TXT

		//	Decodifica ASCII para String
		decode(tree, codigo);					//	Devido ele ler errado o ASCII do TXT ele converte errado

		//	Decodifica ASCII para String
		//decode(tree, ascii);					//	Se pegar direto a String em ASCII SEM LER DO TXT ELE FUNCIONA

	}

	// Grava o texto no arquivo txt

	public static void write(String name, String endereco) throws IOException {
		File m;
		m = new File(endereco);

		try {
			FileWriter fw = new FileWriter(m, false);
			PrintWriter pw = new PrintWriter(fw);
			//pw.println("C");		//	Comeco
			String palavra = name;
			pw.println(palavra);
			//pw.print("F");		//	Fim
			pw.close();
		} catch (IOException e) {
			System.out.println("Erro ao gravar no arquivo.");
		}
	}

	//	Le o que contem no arquivo passado como parametro
	public static String read(String filename) {
		String code = "";
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;
			String[] a;

			while ((line = br.readLine()) != null) {
				code += line;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nÃ£o existe.");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}
		return code;
	}

	//	Metodo read especifico para ler ASCII
	public static String leASCII(String filename) {
		String code = "";
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;
			String[] a;

			//if(br.readLine() == "C") {	//	Comeco
			while ((line = br.readLine()) != null) {
				//if(line == "F")			//	Fim
				//br.close();
				code += line;
			}
			//}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nÃ£o existe.");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}
		return code;
	}

	//	Cria a arvore Huffman com o array de char passado
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

	//	Transforma String em binario
	public static String encode(HuffmanTree tree, String name){
		assert tree != null;

		System.out.println("SÍMBOLO\tQUANTIDADE\tHUFFMAN CÓDIGO\n");
		//	Exibi o codigo dos caracteres
		printCodes(tree, new StringBuffer());

		String encodeText = "";
		for (char c : name.toCharArray()){
			encodeText+=(getCodes(tree, new StringBuffer(),c));
		}
		System.out.println("\nTEXTO COMPACTADO\n");
		System.out.println("Binario -> "+encodeText+"\n");

		//	Retorna o texto Compactado
		return encodeText; 			
	}

	//	Converte de Binario para ASCII
	public static String binarioToASCII(String b) {
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
					//	Converte de Decimal para ASCII
					Character ch = Character.toString((char)c).charAt(0);

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
					//	Converte DECIMAL PARA ASCII
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
			salva += buffer[i];
		}
		//	Converter BINARIO PARA DECIMAL
		int c = Integer.parseInt(salva, 2);		
		//	Converte DECIMAL PARA ASCII
		Character ch = Character.toString((char)c).charAt(0);
		ASCII += ch;

		System.out.println("ASCII -> "+ASCII+"\n");
		return ASCII;
	}


	//	DECODIFICA ASCII PARA BINARIO
	public static String decode(HuffmanTree tree, String name) {
		String saida = "";
		String binario = "";
		int cont = 0;

		assert tree != null;

		char[] buffer = new char[name.length()];

		//	FAZ O LAÇO PARA GUARDAR STRING EM UM ARRAY DE CHAR 
		for(int i = 0; i < name.length()-1; i++) {
			cont = i;
			int sub = 0;
			String guarda = "";

			//	Passa a string ASCII para um array de char
			buffer[i] = name.charAt(i);

			//	Transforma ASCII em DECIMAL
			int c = buffer[i];

			//	Converte DECIMAL EM BINARIO
			saida = Integer.toBinaryString(c);

			//	VERIFICA SE TEM 8 BITS NA VARIAVEL SAIDA
			if(saida.length() != 8) {
				//	SE NAO TIVER TEM QUE COMPLETAR COM OS ZEROS QUE FORAM CORTADOS
				sub = 8 - saida.length();
				//	GUARDA OS ZEROS QUE FORAM CORTADOS
				for(int j = 0; j < sub; j++) {
					guarda += 0;
				}
				//	RECEBE O RESTO DOS BITS, FICANDO COM 8 BITS COMPLETOS PARA CONVERSÃO
				guarda += saida;
				binario += guarda;
			}
			else {
				//	SE TIVER 8 BITS GUARDA PARA CONVERSÃO NORMAL
				guarda += saida;
				binario += guarda;
			}
		}
		cont++; 

		//	CONVERTE OS BITS RESTANTES QUE NAO CHEGARAM A FECHAR 8 BITS

		buffer[cont] = name.charAt(cont);
		int c = buffer[cont];

		saida = Integer.toBinaryString(c);

		String aux = "";

		//	SALVA TODOS OS BITS EM AUX
		for(int i = 1; i < saida.length(); i++) {
			aux += saida.charAt(i);
		}

		//	GUARDA JUNTO COM OS OUTROS BITS
		binario += aux;

		System.out.println("Conversão reversa para Binário -> "+binario);

		//	FAZ A CONVERSAO DE BINARIO PARA STRING

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

		// Retorna o texto Decodificado
		return decodeText; 						
	}

	//	Método para percorrer a Árvore e mostra a tabela de compactação
	public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;

		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf)tree;

			// Imprime na tela a Lista
			System.out.println(leaf.value + "\t" + leaf.frequency + "\t\t" + prefix);

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode)tree;

			// Lado esquerdo
			prefix.append('0');
			printCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length()-1);

			// Lado direito
			prefix.append('1');
			printCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length()-1);
		}
	}


	//	Método para retornar o código compactado de uma letra (w)
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
