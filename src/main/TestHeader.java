package main;

import io.FileRW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestHeader {

	public static byte[] PDF_HEADER = {37,80,68,70,45,49,46,52,10,37};
	
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/nicolas/Downloads/encryptedEnglishprojectpresentation.pdf");
		File file2 = new File("C:/Users/nicolas/Downloads/Lettre_de_motivation_alternant.pdf");
		FileInputStream fis = new FileInputStream(file);
		FileInputStream fis2 = new FileInputStream(file2);
		
		int b, b2;
		System.out.println("i\t b\t b2");
		int length = 0;
		String pdfHeader = "";
		for(int i = 0; i < file.length() && i < file2.length(); i++){
			b =  fis.read();
			b2 =  fis2.read();
			System.out.println(i+"\t "+Integer.toBinaryString(b)+"\t "+Integer.toBinaryString(b2));
			pdfHeader += Integer.toBinaryString(b);
			if(b != b2){
				System.out.println("Première diff à "+i+" ");
				length = i-1;
				System.out.println("Header = "+pdfHeader+", length"+pdfHeader.length());
			break;
			}
		}
		
		pdfHeader = "";
		byte[] data = FileRW.readBinaryFile(file.getAbsolutePath());
		
		for(int i = 0; i <= length; i++){
			pdfHeader += ","+data[i];
		}
		System.out.println("pdfHeader = "+pdfHeader+", length"+pdfHeader.length());
	}

}
