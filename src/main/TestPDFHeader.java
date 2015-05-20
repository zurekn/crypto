package main;

import io.FileRW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPDFHeader {

	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/nicolas/Downloads/Englishprojectpresentation.pdf");
		File file2 = new File("C:/Users/nicolas/Downloads/Lettre_de_motivation_alternant.pdf");
		FileInputStream fis = new FileInputStream(file);
		FileInputStream fis2 = new FileInputStream(file2);
		
		int b, b2;
		System.out.println("i\t b\t b2");
		String pdfHeader = "";
		for(int i = 0; i < file.length() && i < file2.length(); i++){
			b =  fis.read();
			b2 =  fis2.read();
			System.out.println(i+"\t "+Integer.toBinaryString(b)+"\t "+Integer.toBinaryString(b2));
			pdfHeader += Integer.toBinaryString(b);
			if(b != b2){
				System.out.println("Première diff à "+i+" ");
				System.out.println("pdfHeader = "+pdfHeader+", length"+pdfHeader.length());
			break;
			}
		}
		
		pdfHeader = "";
		byte[] data = FileRW.readBinaryFile(file.getAbsolutePath());
		for(int i = 0; i < 11; i++){
			pdfHeader += data[i];
		}
		System.out.println("pdfHeader = "+pdfHeader+", length"+pdfHeader.length());
	}

}
