package com.bee.transfer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bee.transfer.impl.FileToFileTransfer;
import com.bee.transfer.impl.LocalFileSource;

public class Main {

	public static void main(String[] args) throws IOException {
		createSource(args).copy(createTransfer(args));
	}
	
	/**
	 * 创建源对像
	 * @param args
	 * @return
	 */
	private static Source createSource(String[] args) {
		return new LocalFileSource(parseArgs(args));
	}
	
	/**
	 * 创建目标对像及传输对像
	 * @param args
	 * @return
	 */
	private static Transfer createTransfer(String[] args) {
		return new FileToFileTransfer(new LocalFileSource(parseArgs(args)));
	}
	

	private static Map<String, String> parseArgs(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		
		for(String arg : args) {
			int index = arg.indexOf("=");
			if(index <=0) {
				continue;
			}
			params.put(arg.substring(0, index).trim(), arg.substring(index + 1).trim());
		}
		return params;
	}
}
