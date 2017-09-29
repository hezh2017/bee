package com.bee.local;

import java.io.File;

/**
 * 
 * 本地文件拷贝和移动类
 */
public class LocalFileTransfer {

	public static void main(String[] args) {
		if(args.length < 2) {
			log("Usage: LocalFileTransfer srcFile dstFile [deleteSource]");
			return;
		}
		File src = new File(args[0]);
		File dst = new File(args[1]);
		
		boolean deleteSource = false;
		if(args.length >= 3) {
			deleteSource = "true".equalsIgnoreCase(args[2]) ? true : false;
		}
		
		log("Source: " + src.getAbsolutePath());
		log("Dst: " + dst.getAbsolutePath());
		log("Delete Source: " + deleteSource);
		try {
			LocalFile local = new LocalFile(src);
			local.copyTo(dst);
		
			if(deleteSource) {
				local.delete();
			}
		} catch(Exception ex) {
			log("Copy Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public static void log(String msg) {
		System.out.println("INFO: " + msg);
	}

}
