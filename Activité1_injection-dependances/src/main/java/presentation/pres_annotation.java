package presentation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import metier.IMetier;
import metier.ImplMetier;

public class pres_annotation {

	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext("ext","metier","dao");
		IMetier metier = context.getBean(IMetier.class); 
		System.out.println(metier.calculer());

	}

}
