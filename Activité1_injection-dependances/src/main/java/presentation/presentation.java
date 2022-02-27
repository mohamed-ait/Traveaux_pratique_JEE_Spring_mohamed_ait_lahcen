package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Method;
import java.util.Scanner;

import dao.IDao;
import metier.IMetier;

public class presentation {
public static void main(String[] args)  {
	try {
		Scanner scanner = new Scanner(new File("config.txt"));	
		String daoClasseName=scanner.nextLine();
		String metierClasseNAme=scanner.nextLine();
		Class cDao=Class.forName(daoClasseName);
		Class cMetier=Class.forName(metierClasseNAme);
		IDao dao=(IDao) cDao.newInstance();
		IMetier metier = (IMetier)cMetier.newInstance();
		Method meth=cMetier.getMethod("setDao", IDao.class);
		meth.invoke(metier, dao);
		System.out.println(metier.calculer());
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	
	
}
}
