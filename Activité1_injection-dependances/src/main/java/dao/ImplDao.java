package dao;

import org.springframework.stereotype.Component;

@Component
public class ImplDao implements IDao{

	public double getValue() {
		
		return Math.random()*10;
	}

}
