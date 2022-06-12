package com.example.demo.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {
	
	public final String UPLOAD_DIR="D:\\CodePlayground\\React\\mymovieplan\\src\\images";
//	public final String UPLOAD_DIR=new ClassPathResource("static/img/").getFile().getAbsolutePath();
	
	public FileUpload()throws IOException {
		
	}
	public boolean uploadFile(MultipartFile multipartfile) {
		boolean f=false;
		
		try {
			
			Files.copy(multipartfile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+multipartfile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			
			
			
			f=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return f;
	}
	

}
