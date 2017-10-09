package com.bee.transfer.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.bee.transfer.Source;
import com.bee.transfer.Transfer;
import com.bee.transfer.TransferData;

/**
 * 本地文件的数据源
 *
 */
public class LocalFileSource extends Source {

	public LocalFileSource(Map<String, String> params) {
		super(params);
	}

	@Override
	public void copy(Transfer transfer) throws IOException {
		if(! params.containsKey("src")) {
			throw new IOException("src参数不存在");
		}
		File src = new File(params.get("src"));
		if(! src.exists()) {
			return;
		}
		
		copyTo(src, transfer, "");
	}
	
	private void copyTo(File src, Transfer transfer, String parentPath) throws IOException {
		if(src.isFile()) { //源文件为文件时
			copyFile(src, parentPath + src.getName(), transfer);
			return;
		}
		
		//源文件为目录时, 先创建目录
		String path = parentPath + src.getName() + "/";
		transfer.writeDst(new TransferData(path, null, true));
		
		//再遍历子目录拷贝
		for(File subFile: src.listFiles()) {
			copyTo(subFile, transfer, path);
		}
	}
	
	private void copyFile(File src, String path, Transfer transfer) throws IOException {
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src));
			transfer.writeDst(new TransferData(path, in, false));
		} finally {
			if(in != null) in.close();
		}
	}
	

	@Override
	public void writeDst(TransferData data) throws IOException{
		if(! params.containsKey("dst")) {
			throw new IOException("dst参数不存在");
		}
		
		String dst = this.params.get("dst");
		
		File dstFile = new File(dst + "/" + data.getPath());
		
		if(data.isDirectory()) {
			dstFile.mkdirs(); //创建目录
			return;
		}
		
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(dstFile));
			byte[] buff = new byte[512];
			int readLen = 0;
			while((readLen = data.getIn().read(buff)) > 0) {
				out.write(buff, 0, readLen);
			}
		} finally {
			if(out != null) out.close();
		}
	}
	
}
