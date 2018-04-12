package common;
/**
 * 
 * 管理需要的对象
 *
 */

import woss.client.Gather;

public class Configuration implements ConfigurationAware{
	
	//获取采集模块的对象
	public Gather getGather() {
		return null;
		
	}

	@Override
	public void setConfiguration(ConfigurationAware config) {
		// TODO Auto-generated method stub
		
	}
}
