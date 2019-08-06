package com.ghroth251.util;

import java.sql.*;
import java.util.Properties;

/**
 * @author bruceliu
 * @time 2019年4月2日上午9:59:27
 * @Description JDBC操作的工具类，有一个传统的名字：BaseDao.java , 有些程序喜欢取别的名字： JDBCUtils.java
 * Base:基础的
 * Dao:数据库访问层  
 */
public class BaseDao {

	private Connection conn=null; //连接对象
	private PreparedStatement ps=null; //执行SQL对象
	private ResultSet rs=null; //结果集
	
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		//静态代码块，性能高，只加载一次！
		try {
			Properties p=new Properties();
			p.load(BaseDao.class.getClassLoader().getResourceAsStream("db.properties"));
			driverClass=(String) p.get("driverClass");
			url=(String) p.get("jdbcUrl");
			username=(String) p.get("user");
			password=(String) p.get("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 01-打开数据库连接
	 */
	private void openConn(){
		try {
			Class.forName(driverClass);
			conn=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 02-通用的执行增删改的方法
	 */
	public int myexecuteUpdate(String sql,Object[] params){
		this.openConn();//打开数据库连接
		try {
			ps=conn.prepareStatement(sql);
			//有参数
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closConn();//关闭数据库
		}
		return 0;
	}
	
	/**
	 * 03-通用的执行查询的方法
	 * @return
	 */
	public ResultSet myexecuteQuery(String sql,Object[] parms){
		this.openConn();
		try {
			ps=conn.prepareStatement(sql);
			if(parms!=null){
				for (int i = 0; i < parms.length; i++) {
					ps.setObject(i+1, parms[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//查询的此处不能提前关闭数据库。不然话结果集合没法遍历
		}
		return rs;
	}
	
	
	/**
	 * 04-关闭数据库
	 */
	protected void closConn(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}