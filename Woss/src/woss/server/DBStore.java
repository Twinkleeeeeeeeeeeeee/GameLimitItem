package woss.server;

import java.util.Collection;
import java.util.Properties;

import base.BIDR;


public interface DBStore {
	void saveToDB(Collection<BIDR> data);

}
