package com.fate.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.fate.bean.QQuser;

public class IOUtils {
	public static void writeQQuser(ArrayList<QQuser> A){
		writeQQuser(A,"data/userList.List");
	}
	public static ArrayList<QQuser> readQQuser(){
		return readQQuser("data/userList.List");
	}
	public static void writeQQuser(ArrayList<QQuser> A,String str){
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(str);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(A);
			oos.writeObject(null);
		} catch (IOException e) {
			System.out.println("写入程序遭遇致命错误！");
		}finally {
			if(oos != null)	{
				try {
					oos.close();
				} catch (IOException e) {
				}
			}
			if(fos != null)	{
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
			oos = null;
			fos = null;
		}
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<QQuser> readQQuser(String str){
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		ArrayList<QQuser> A;
		try {
			fis = new FileInputStream(str);
			ois = new ObjectInputStream(fis);
			A = (ArrayList<QQuser>)ois.readObject();
			ois.close();
			return A;
		} catch (ClassNotFoundException e) {
			System.out.println("用户类错误");
		} catch (FileNotFoundException e) {
			System.out.println("未找到文件");
		} catch (IOException e) {
			System.out.println("读取程序发生致命错误");
		}finally {
			if(ois != null)	{
				try {
					ois.close();
				} catch (IOException e) {
				}
			}
			if(fis != null)	{
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
			ois = null;
			fis = null;
		}
		return null;
	}
}

