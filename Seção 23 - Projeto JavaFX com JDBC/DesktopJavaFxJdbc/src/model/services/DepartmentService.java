package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

	public DepartmentService() {
	}

	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	public void saveOrUpdate(Department department) {
		if (department.getId() != null) {
			departmentDao.update(department);
		} else {
			departmentDao.insert(department);
		}
	}
}
