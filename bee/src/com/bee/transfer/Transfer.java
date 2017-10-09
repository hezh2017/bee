package com.bee.transfer;

import java.io.IOException;

/**
 *  数据传输对像，同时完成对不同数据源之间的格式转换
 *
 */
public abstract class Transfer {

	protected Source dst;
	
	public Transfer(Source dst) {
		this.dst = dst;
	}
	
	/**
	 * 将数据写入到目标源
	 * @param data
	 * @throws IOException
	 */
	public void writeDst(TransferData data) throws IOException {
		this.dst.writeDst(data);
	}
	
}
