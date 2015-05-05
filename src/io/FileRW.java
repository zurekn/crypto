package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class FileRW {
	public static byte[] readBinaryFile(String sourcePath) {
		File source = new File(sourcePath);
		byte[] res = new byte[(int) source.length()];
		try {
			InputStream inputStream = null;
			try {
				int totalBytesRead = 0;
				inputStream = new BufferedInputStream(new FileInputStream(
						source));
				while (totalBytesRead < res.length) {
					int bytesRemaining = res.length - totalBytesRead;
					// input.read() returns -1, 0, or more :
					int bytesRead = inputStream.read(res, totalBytesRead,
							bytesRemaining);
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;

					}
				}
			} finally {
				inputStream.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void writeBinaryFile(byte[] data, String filePath) {
		try {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(
						new FileOutputStream(filePath));
				output.write(data);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}
	}

	public static void writeTextFile(String text, String filePath) {
		try {
			OutputStream ops = new FileOutputStream(filePath);
			OutputStreamWriter opsw = new OutputStreamWriter(ops);
			BufferedWriter bw = new BufferedWriter(opsw);
			bw.write(text);
			bw.close();
			opsw.close();
			ops.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String readTextFile(String filePath) {
		String text = null;
		try {
			text = "";
			InputStream ips = new FileInputStream(
					"src/res/vigenere-germinalChiffre.txt");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			while ((line = br.readLine()) != null) {
				text += line + "\n";
			}
			br.close();
			ipsr.close();
			ips.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
	public static String superscript(String str) {
	    str = str.replaceAll("0", "⁰");
	    str = str.replaceAll("1", "¹");
	    str = str.replaceAll("2", "²");
	    str = str.replaceAll("3", "³");
	    str = str.replaceAll("4", "⁴");
	    str = str.replaceAll("5", "⁵");
	    str = str.replaceAll("6", "⁶");
	    str = str.replaceAll("7", "⁷");
	    str = str.replaceAll("8", "⁸");
	    str = str.replaceAll("9", "⁹");  
	    return str;
	}
}
