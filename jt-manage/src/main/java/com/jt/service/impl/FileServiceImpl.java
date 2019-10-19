package com.jt.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.ImageVo;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService{

	@Value("${image.localDirPath}")
	private String localDirPath;
	@Value("${image.urlDirPath}")
	private String urlDirPath;
	
	@Override
	public ImageVo uploadFile(MultipartFile image) throws Exception {
		//1.判断是否为图片文件
		String filename = image.getOriginalFilename().toLowerCase();
		String reg = "^.+\\.(jpg|jpeg|png|gif)$";
		if(!filename.matches(reg)) {
			return new ImageVo();
		}
		BufferedImage bImg = ImageIO.read(image.getInputStream());
		int width = bImg.getWidth();
		int height = bImg.getHeight();
		if(width == 0 || height == 0) {
			return new ImageVo();
		}
		//2.以时间为单位划分文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		String path = sdf.format(new Date());
		String dirPath = localDirPath + path;
		File dir = new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//3.创建图片名称
		String uuid = UUID.randomUUID().toString();
		String fileType = filename.substring(filename.lastIndexOf("."));
		String realPath = dirPath+uuid+fileType;
		File file = new File(realPath);
		image.transferTo(file);
		
		String url = urlDirPath + path + uuid + fileType;
		ImageVo imgVo = new ImageVo(url,width,height);
		return imgVo;
	}

}
