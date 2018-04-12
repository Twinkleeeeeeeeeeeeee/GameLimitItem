package woss.client;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.client.Gather;

import log.LoggerImp;
import util.BackUpImp;
import util.ConfigurationImpl;

public class GatherImpl implements Gather,ConfigurationAWare{
	ConfigurationImpl con;
	@Override
	public void init(Properties pro) {
		// TODO Auto-generated method stub
			pro.getProperty("");
	}
	
	@Override
	public Collection<BIDR> gather() throws Exception {
		// TODO Auto-generated method stub
		Long index=0l;
		LoggerImp llmp = new LoggerImp();
		String fileName = "back_up_1.txt";
		Map<String, BIDR> map = new TreeMap<String, BIDR>();
		BufferedReader buf = new BufferedReader(new FileReader("src/tmp"));
		String br = null;
		int num = 0;
		BackUpImp back_up = new BackUpImp();
		Object obj = back_up.load(fileName, true);
		Map<String, BIDR> map1 = (Map<String, BIDR>)obj;
		index = (Long)back_up.load("index",true);
		if(map1 != null) {
			map = map1;
		}
		if(index ==null) {
			index = 0l;
		}
	    buf.skip(index);
		List<BIDR> list = new ArrayList<BIDR>();
		while ((br = buf.readLine()) != null) {
			index += br.length()+2l;
			String[] str = br.split("[|]");
			String aaa_login_name = str[0];
			String Nas_ip = str[1];
			String status = str[2];
			String login_date = str[3];
			String logout_date = str[3];
			String login_ip = str[4];
			Timestamp login = new Timestamp(Long.parseLong(login_date)*1000);
			Timestamp logout = new Timestamp(Long.parseLong(logout_date)*1000);
			if(!aaa_login_name.equals("#")){
				BIDR bidr = new BIDR();
				bidr.setAAA_login_name(aaa_login_name);
				bidr.setLogin_date(login);
				bidr.setLogin_ip(login_ip);
				bidr.setNAS_ip(Nas_ip);
				map.put(login_ip, bidr);
			}else if (map.containsKey(login_ip)) {
					map.get(login_ip).setLogout_date(logout);
					map.get(login_ip).setTime_deration((int)((logout.getTime() - map.get(login_ip).getLogin_date().getTime()))/1000);
				    list.add(map.get(login_ip));
					map.remove(login_ip);
				}
			
		}
		
		System.out.println("完整数据对象大小为:"+list.size()+".不完整数据对象的大小为:"+map.size());
		llmp.info("正在将存储备份信息：");
		BackUpImp bi = new BackUpImp();
		bi.store(fileName, map,bi.STORE_OVERRIDE);
		bi.store("index", index, bi.STORE_OVERRIDE);
		llmp.info("储存备份信息完毕");
		return list;
	}

	public static void main(String[] args) {

		try {
			GatherImpl gImpl = new GatherImpl();
			gImpl.gather();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		con = (ConfigurationImpl) config;
	}

}
