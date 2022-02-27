package presentation;

import java.applet.AppletContext;
import java.util.logging.Logger;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;

import metier.IMetier;

public class pres_config {
public static void main(String[] args) {
	ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
	IMetier metier=(IMetier) context.getBean("metier");
	System.out.println(metier.calculer());
}
}
