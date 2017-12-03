package com.doctortrain.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.doctortrain.bean.DtConfig;
import com.doctortrain.bean.Permission;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.RolePermissionRelation;
import com.doctortrain.bean.User;
import com.doctortrain.bean.UserRoleRelation;
import com.doctortrain.dao.ClinicalRotationDAO;
import com.doctortrain.dao.DtConfigDAO;
import com.doctortrain.dao.PermissionDAO;
import com.doctortrain.dao.RoleDAO;
import com.doctortrain.dao.RolePermissionRelationDAO;
import com.doctortrain.dao.UserDAO;
import com.doctortrain.dao.UserRoleRelationDAO;
import com.doctortrain.service.InitDataService;
import com.doctortrain.util.DateUtil;

@Service("initDataService")
public class InitDataServiceImpl implements InitDataService {

	@Resource(name="userDao")
	private UserDAO userDao;
	
	@Resource(name="roleDao")
	private RoleDAO roleDao;
	@Resource(name="permissionDao")
	private PermissionDAO permissionDao;
	
	@Resource(name="rolePermissionRelationDao")
	private RolePermissionRelationDAO rolePermissionRelationDao;
	
	@Resource(name="userRoleRelationDao")
	private UserRoleRelationDAO userRoleRelationDao;
	
	@Resource(name="dtConfigDao")
	private DtConfigDAO dtConfigDAO;
	
	
	public void importXmlFile(String xmlFile) throws Exception {
		//创建SAX解析器 （以下解析器使用了SAXReader，但是底层实现仍然是dom的编程方法）
		SAXReader reader = new SAXReader();
		//获取当前线程的类加载器 模仿hibernate中的config方法获取
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//通过类加载器获取指向某一个配置文件的输入流
		InputStream in = classLoader.getResourceAsStream(xmlFile);
		//读取xml文件，将xml文件全部加载到内存中，生成dom树
		Document document = reader.read(in);
		
		List<Element> perList = document.selectNodes("/sys-data/permission-info/permission");
		addAllPermission(perList);
		Role role = addRole((Element)document.selectSingleNode("/sys-data/role-info/role"));
		User user = addUser((Element)document.selectSingleNode("/sys-data/user-info/user"));
		addRolePermissionRelation(role);
		addUserRoleRelation(user, role);
		addDtConfig((Element)document.selectSingleNode("/sys-data/config-info/config"));
	}
	
	public void addAllPermission(List<Element> PerList) throws Exception{
		for (Element element : PerList) {
			Permission per = new Permission();
			per.setId(element.attributeValue("id"));
			per.setPid(element.attributeValue("pid"));
			per.setName(element.attributeValue("name"));
			if(element.attributeValue("icon")!=null||!"".equals(element.attributeValue("icon"))){
				per.setIcon(element.attributeValue("icon"));
			}
			if(element.attributeValue("module-url")!=null||!"".equals(element.attributeValue("module-url"))){
				per.setModuleUrl(element.attributeValue("module-url"));
			}
			if(element.attributeValue("operation-url")!=null||!"".equals(element.attributeValue("operation-url"))){
				per.setOperationUrl(element.attributeValue("operation-url"));
			}
			per.setCreateTime(DateUtil.getSystemTime());
			permissionDao.addPermission(per);
		}
	}
	
	public void addRoles(List<Element> roleList) throws Exception{
		for (Element element : roleList) {
			Role role = new Role();
			role.setId(element.attributeValue("id"));
			role.setName(element.attributeValue("name"));
			role.setType(element.attributeValue("type"));
			role.setCreateTime(DateUtil.getSystemTime());
			roleDao.addRole(role);
		}
	}
	
	public void addUsers(List<Element> userList) throws Exception{
		for (Element element : userList){
			User user = new User();
			user.setUsername(element.attributeValue("username"));
			user.setPassword(element.attributeValue("password"));
			user.setPrivilege(element.attributeValue("privilege"));
			user.setStatus(element.attributeValue("status"));
			userDao.addUser(user);
		}
	}
	
	public void addRolePermissionRelation(Role role) throws Exception{
		List<Permission> perList = permissionDao.getAllPermission();
		for (Permission permission : perList) {
			RolePermissionRelation rpr = new RolePermissionRelation();
			rpr.setPermission(permission);
			rpr.setRole(role);
			rolePermissionRelationDao.save(rpr);
		}
	}
	
	public void addUserRoleRelation(User user,Role role) throws Exception{
		UserRoleRelation userRoleRelation = new UserRoleRelation();
		userRoleRelation.setRole(role);
		userRoleRelation.setUser(user);
		userRoleRelationDao.save(userRoleRelation);
	}
	
	public User addUser(Element element) throws Exception{
		User user = new User();
		user.setUsername(element.attributeValue("username"));
		user.setPassword(element.attributeValue("password"));
		user.setPrivilege(element.attributeValue("privilege"));
		user.setStatus(element.attributeValue("status"));
		userDao.addUser(user);
		return user;
	}
	
	public Role addRole(Element element) throws Exception{
		Role role = new Role();
		role.setId(element.attributeValue("id"));
		role.setName(element.attributeValue("name"));
		role.setType(element.attributeValue("type"));
		role.setCreateTime(DateUtil.getSystemTime());
		roleDao.addRole(role);
		return role;
	}
	
	public void addDtConfig(Element element) throws Exception{
		DtConfig dt = new DtConfig();
		dt.setName(element.attributeValue("name"));
		dt.setValue(element.attributeValue("value"));
		dtConfigDAO.addDc(dt);
	}
	

}
