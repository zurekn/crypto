package main;

import io.FileRW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sun.security.util.Length;

public class TestHeader {
	// Valeur staticpour voir les précedents test
	public static int MIN_LENGTH = 17;
	public static byte[] HEADER = { -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0 };

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		// PLUS UTILE//
//		File file = new File("C:/Users/nicolas/Google Drive/Master1/Synthèse/ressources/carte qrcode.png");
//		File file2 = new File("C:/Users/nicolas/Google Drive/Master1/Synthèse/ressources/map.png");
//		FileInputStream fis = new FileInputStream(file);
//		FileInputStream fis2 = new FileInputStream(file2);
		// PLUS UTILE//

		// List of Files to read and compare
		List<File> files = new ArrayList<File>();
		files.add(new File("C:/Users/nicolas/Google Drive/Master1/Synthèse/ressources/carte qrcode.png"));
		files.add(new File("C:/Users/nicolas/Google Drive/Master1/Synthèse/ressources/map.png"));

		long minFileLength = Long.MAX_VALUE;
		// Creation d'une liste de FileInputStream depuis la liste de File
		List<FileInputStream> fisL = new ArrayList<FileInputStream>();

		for (File f : files) {
			if (minFileLength > f.length())
				minFileLength = f.length();
			fisL.add(new FileInputStream(f));
		}

		int b, b2, length = 0;
		String header = "";
		for (int i = 0; i < minFileLength && length < 1; i++) {
			b = fisL.get(0).read();
			System.out.print(i + "\t " + b);
			for (int j = 1; j < fisL.size(); j++) {
				b2 = fisL.get(j).read();
				System.out.print("\t " + b2);
				if (b != b2) {
					System.out.println("\n -------------------------Première diff à " + i + "-------------------------");
					length = i - 1;
					break;
				}

			}
			System.out.println("");
		}
		// System.out
		// .println("----------------------Deuxième méthode !!!----------------------");
		// for (int i = 0; i < file.length() && i < file2.length(); i++) {
		// b = fis.read();
		// b2 = fis2.read();
		// System.out.println(i + "\t " + Integer.toBinaryString(b) + "\t "
		// + Integer.toBinaryString(b2));
		// header += Integer.toBinaryString(b);
		// if (b != b2) {
		// System.out.println("Première diff à " + i + " ");
		// length = i - 1;
		// System.out.println("Header = " + header + ", length"
		// + header.length());
		// break;
		// }
		// }

		header = "";
		byte[] data = FileRW.readBinaryFile(files.get(0).getAbsolutePath());

		for (int i = 0; i <= length; i++) {
			header += "," + data[i];
		}
		System.out.println("pdfHeader = " + header + ", length = " + length);
	}

}
