package ext;

import org.springframework.stereotype.Component;

import dao.IDao;
@Component("d")
public class ImplDao2 implements IDao{

	public double getValue() {
		
		return Math.random()*15;
	}

}
