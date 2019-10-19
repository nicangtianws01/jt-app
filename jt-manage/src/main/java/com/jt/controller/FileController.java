package com.jt.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.ImageVo;

@RestController
public class FileController {
	@Autowired
	private FileService fileService;
	@RequestMapping("/file")
	public String file(MultipartFile fileImage) throws Exception {
		String filename = fileImage.getOriginalFilename();
		File dir = new File("");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String path = "E:/file/"+filename;
		File file = new File(path);
		fileImage.transferTo(file);
		return "上传成功";
	}
	@RequestMapping("/pic/upload")
	public ImageVo imageUpload(MultipartFile uploadFile) throws Throwable {
		return fileService.uploadFile(uploadFile);
	}
}
