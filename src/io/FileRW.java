package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
			//ex.printStackTrace();
		}
	}
}
