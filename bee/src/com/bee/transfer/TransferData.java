package com.bee.transfer;

import java.io.InputStream;

/**
 * 
 * 传输的数据
 */
public class TransferData {

	public String path;
	
	public InputStream in;
	
	public boolean isDirectory;

	public TransferData(String path, InputStream in, boolean isDirectory) {
		super();
		this.path = path;
		this.in = in;
		this.isDirectory = isDirectory;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	
	
}
