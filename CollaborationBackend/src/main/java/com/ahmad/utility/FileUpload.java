package com.ahmad.utility;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	public static void uploadImage(String path,MultipartFile image,String imageName)
	{
		if(Files.exists(Paths.get(path)))
		{
			
		}
	}
	
	
}
