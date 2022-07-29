import java.util.Scanner;

public class Encoder {

	private static String tabela = "?ã zyxwvutsrqpMLONPRonmlkjihgfe+dcb=a-%@&8!ç(*0).$#9764523:1úAECDB}FHGIKJSQTVUXWZYâõô_>;{<[^~/]";
	private static int deslc = 9;

	// Recebe um string e retorna o correspondente codificado
	public static String Encode(String str) {
		boolean found = false;
		String encoded = "";

		for (char s : str.toCharArray()) {
			found = false;
			for (int i = 0; i < tabela.length(); i++) {
				if (tabela.charAt(i) == s) {
					found = true;
					int pos = Math.floorMod((i+deslc), tabela.length());
					encoded += tabela.charAt(pos);
				}
			}
			if (!found) encoded += s;
		}

		encoded = new StringBuilder(encoded).toString();
		String encodedReversa = new StringBuilder(encoded).reverse().toString();

		encoded = (encoded.length() % 2 == 0) ? encodedReversa.substring(encoded.length()/2) + encoded.substring(encoded.length()/2) : encodedReversa.substring((encoded.length()/2)+1) + encoded.substring(encoded.length()/2);

		return encoded;
	}

	// Recebe um string codificado e retorna o correspondente decodificado
	public static String Decode(String str) {
		boolean found = false;
		String decoded = new StringBuilder(str.substring(0, str.length()/2)).reverse().toString();
		decoded += str.substring(str.length()/2);
		char[] s = decoded.toCharArray();

		for (int indexSub=0; indexSub<decoded.length(); indexSub++) {
			found = false;
			for (int i = 0; i < tabela.length(); i++) {
				if (tabela.charAt(i) == s[indexSub]) {
					found = true;
					int pos = Math.floorMod((i-deslc), tabela.length());
					s[indexSub] = tabela.charAt(pos);
				}
			}
			if(!found) decoded += s[indexSub];
		}

		decoded = new String(s);
		return decoded;
	}

	public static String getStringFromUser() {
		return new Scanner(System.in).nextLine();
	}

	public static void main(String[] args) {
		// checks for arguments
		if (args.length < 1){
			System.out.println("No arguments... see usage in the readme file");
			System.exit(0);
		} else if (args.length > 1) {
			System.out.println("Too many arguments... see usage in the readme file");
			System.exit(0);
		}

		char option = args[0].trim().charAt(0);

		if (option == 'e') {
			String s = getStringFromUser();
			String enc = Encode(s);
			System.out.println(enc);
		} else if (option == 'd') {
			String s = getStringFromUser();
			String dec = Decode(s);
			System.out.println(dec);
		} else {
			System.out.println("Wrong option... see usage in the readme file");
		}
	}
}
