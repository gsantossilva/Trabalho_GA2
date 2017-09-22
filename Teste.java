package Trab_GrauA_2;

public class Teste {
	public static void main(String[] args) {
		String s = BinaryStdIn.readString();
		   char[] input = s.toCharArray();

		   // cálculo da tabela de códigos st[]
		   // discutido mais adiante

		   for (int i = 0; i < input.length; i++) {
		      String code = st[input[i]];
		      for (int j = 0; j < code.length(); j++)
		      if (code.charAt(j) == '1')
		           BinaryStdOut.write(true);
		      else BinaryStdOut.write(false);
		   }
		   BinaryStdOut.close();
	}
}
