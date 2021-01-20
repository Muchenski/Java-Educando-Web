package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {

	private SellerDao sellerDao = DaoFactory.createSellerDao();

	public SellerService() {
	}

	public List<Seller> findAll() {
		return sellerDao.findAll();
	}

	public void saveOrUpdate(Seller seller) {
		if (seller.getId() != null) {
			sellerDao.update(seller);
		} else {
			sellerDao.insert(seller);
		}
	}

	public void deleteById(Integer id) {
		sellerDao.deleteById(id);
	}
}
