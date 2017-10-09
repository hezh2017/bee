package com.bee.transfer;

import java.io.IOException;
import java.util.Map;

/**
 * 数据源
 *
 */
public abstract class Source {

	protected Map<String, String> params;
	
	public Source(Map<String, String> params) {
		this.params = params;
	}
	
	/**
	 * 作为源端时发起COPY动作
	 * @throws IOException 
	 */
	public abstract void copy(Transfer transfer) throws IOException;
	
	/**
	 * 作为目标时写入数据
	 * @param data
	 * @throws IOException
	 */
	public abstract void writeDst(TransferData data) throws IOException;
	
}
