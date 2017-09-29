package com.bee.local;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalFile {

	private File src;
	
	public LocalFile(File src) throws Exception {
		if(! src.exists()) {
			throw new Exception("文件不存在: " + src.getAbsolutePath());
		}
		this.src = src;
	}
	
	public void copyTo(File dst) throws Exception {
		this.copyTo(src, dst);
	}
	
	private void copyTo(File src, File dst) throws Exception {
		if(src.isFile()) { //源文件为文件时
			copyFile(src, new File(dst, src.getName()));
			return;
		}
		
		//源文件为目录时, 先创建目录
		File dstFile = new File(dst, src.getName());
		dstFile.mkdirs();
		LocalFileTransfer.log("Mkdirs: " + dstFile.getAbsolutePath());
		
		//再遍历子目录拷贝
		for(File subFile: src.listFiles()) {
			copyTo(subFile, dstFile);
		}
	}
	
	private void copyFile(File src, File dst) throws IOException {
		if(dst.exists()) {
			throw new IOException("文件已存在: " + dst.getAbsolutePath());
		}
		
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src));
			out = new BufferedOutputStream(new FileOutputStream(dst));
			byte[] buff = new byte[512];
			int readLen = 0;
			while((readLen = in.read(buff)) > 0) {
				out.write(buff, 0, readLen);
			}
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
		}
		LocalFileTransfer.log("CopyTo: " + dst.getAbsolutePath());
	}
	
	
	public void delete() {
		this.src.delete();
	}
	
}
