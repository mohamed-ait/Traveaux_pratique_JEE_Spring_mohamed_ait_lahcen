package metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dao.IDao;
@Component
public class ImplMetier implements IMetier{
@Qualifier("d")
@Autowired()
private IDao dao;
	public double calculer() {
      double res=dao.getValue()*Math.random()*30;
		return res;
	}
	public IDao getDao() {
		return dao;
	}
	public void setDao(IDao dao) {
		this.dao = dao;
	}
	

}
