package com.tpm.dao;

import java.io.File;
import java.util.Map;

public interface ExportDao {


	File createDoc(Map<String, Object> dataMap);

}
