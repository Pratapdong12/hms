package com.pratap.hms.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class ImageUtil {
	private static final Logger log = Logger.getLogger(ImageUtil.class);
	private static final String IMAGE_UPLOAD_PATH = "C:/Users/Lenovo/Desktop/stuff/imageupload/";

	private static final int DEFAULT_BUFFER_SIZE = 1024;

	public static String writeImageToFile(CommonsMultipartFile file) {
		String imageUrl = IMAGE_UPLOAD_PATH + file.getOriginalFilename();
		log.warn("wrong file path may cause exception...");
		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(imageUrl));) {
			out.write(file.getBytes());
			out.flush();
			out.close();

		} catch (IOException e) {
			log.error("Exception is: " + e);
		}
		return imageUrl;
	}

	public static void displayImage(String imageUrl, HttpServletRequest request, HttpServletResponse response) {
		File file= new File(imageUrl);
		
		BufferedInputStream in= null;
		BufferedOutputStream out= null;
		try {
			in= new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
			out= new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			byte[] buffer= new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length=in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.flush();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
			}catch(IOException e){
				e.printStackTrace(); 
			}
			
		}
	}
}

