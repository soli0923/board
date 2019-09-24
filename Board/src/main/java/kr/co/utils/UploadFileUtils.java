package kr.co.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {
	
	public static ResponseEntity<String> deletefile(String uploadPath, 
													String filename){
		
		ResponseEntity<String> entity = null;
		
		try {
			filename = filename.replace('/', File.separatorChar);
			
			String formatName = filename.substring(filename.lastIndexOf(".")+1);
			boolean isImg = UploadFileUtils.checkFormat(formatName);
			if(isImg) {
				String newfilename = filename.replace("s_", "");
				File ori_img = new File(uploadPath+newfilename);
				ori_img.delete();
			}
			
			File generalAndThumb = new File(uploadPath+filename);
			generalAndThumb.delete();
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), 
												HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	public static String makeIcon(String uploadPath, 
								String datePath,
								String savedName) throws Exception {
		
		File file = new File(uploadPath+datePath, savedName);
		
		BufferedImage sourceImg = ImageIO.read(file);
		
		BufferedImage destImg = Scalr.resize(sourceImg, 
											Scalr.Method.AUTOMATIC, 
											Scalr.Mode.FIT_EXACT,
											100);
		
		String thumbnailName = uploadPath+datePath+File.separator+"s_"+savedName;
		
		String formatName = savedName.substring(savedName.lastIndexOf(".")+1);
		File newFile = new File(thumbnailName);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		String bp = thumbnailName.substring(uploadPath.length());
		
		String bp_suffix = bp.substring(bp.lastIndexOf(".")+1);
		bp=bp.replace(bp_suffix, bp_suffix.toLowerCase());
		
		String ap = bp.replace(File.separatorChar, '/');
		
		return ap;
	}
	
	
	
	
	

	public static String uploadFile(String uploadPath, 
						MultipartFile file) throws Exception {
		String ori_name = file.getOriginalFilename();
		UUID uid=UUID.randomUUID();
		String savedName=uid.toString()+"_"+ori_name;
		String datePath=calPath(uploadPath);
		File target=new File(uploadPath+datePath, savedName);
		if(ori_name == null || ori_name.equals("")) {
			return "";
		}
		FileCopyUtils.copy(file.getBytes(), target);
		
		String formatName=ori_name.substring(ori_name.lastIndexOf(".")+1);
		
		
		if(checkFormat(formatName)) {
			String ap=makeIcon(uploadPath, datePath, savedName);
			return ap;
		}else {
			String bp = datePath+File.separator+savedName;
			String ap = bp.replace(File.separatorChar, '/');
			return ap;
		}
	}
	
	
	

	public static boolean checkFormat(String formatName) {
		List<String> list = new ArrayList<String>();
		list.add("jpg");
		list.add("jpeg");
		list.add("png");
		list.add("gif");
		list.add("jpg".toUpperCase());
		list.add("jpeg".toUpperCase());
		list.add("png".toUpperCase());
		list.add("gif".toUpperCase());
		
		return list.contains(formatName);
	}






	private static String calPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		String monthPath = yearPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}

	private static void makeDir(String uploadPath, String...arr) {
		if(new File(uploadPath+arr[arr.length-1]).exists()){
			   return;
		}
		
		for (String path : arr) {
			File dirPath = new File(uploadPath + path);
			
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}


	public static ResponseEntity<byte[]> displayfile(String uploadPath,
													String filename) {
		ResponseEntity<byte[]> entity = null;
		
		InputStream in =null;
		try {
			in = new FileInputStream(uploadPath+filename);
			HttpHeaders headers = new HttpHeaders();
			
			
			setContent(headers, filename);
			
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
												headers,
												HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				if(in != null) in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return entity;
	}


	private static void setContent(HttpHeaders headers, String filename) throws Exception {
		String formatName = filename.substring(filename.lastIndexOf(".")+1);
		
		if(formatName.equalsIgnoreCase("png")) {
			headers.setContentType(MediaType.IMAGE_PNG);
		}else if(formatName.equalsIgnoreCase("jpg")) {
			headers.setContentType(MediaType.IMAGE_JPEG);
		}else if(formatName.equalsIgnoreCase("jpeg")) {
			headers.setContentType(MediaType.IMAGE_JPEG);
		}else if(formatName.equalsIgnoreCase("gif")) {
			headers.setContentType(MediaType.IMAGE_GIF);
		}else {
			filename = filename.substring(filename.indexOf("_")+1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=\""+new String(filename.getBytes("UTF-8"), "ISO-8859-1")+"\"");
		}
	}

}










