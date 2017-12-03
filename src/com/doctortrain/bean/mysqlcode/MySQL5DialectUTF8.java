package com.doctortrain.bean.mysqlcode;

import org.hibernate.dialect.MySQL57InnoDBDialect;

/**
 * 字符编码 设置 "UTF-8"
 * @author 马营屹
 *
 */
public class MySQL5DialectUTF8 extends MySQL57InnoDBDialect {
	  @Override  
      public String getTableTypeString() {  
          return " ENGINE=InnoDB DEFAULT CHARSET=utf8";    
      }  
}
