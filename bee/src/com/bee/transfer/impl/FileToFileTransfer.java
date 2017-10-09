package com.bee.transfer.impl;

import com.bee.transfer.Source;
import com.bee.transfer.Transfer;

/**
 * 文件到文件类的数据传输，保持默认即可，不需要做任务数据转换
 */
public class FileToFileTransfer extends Transfer {

	public FileToFileTransfer(Source dst) {
		super(dst);
	}

}
