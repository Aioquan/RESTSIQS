package com.restsiqs.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.DAO.AcademyDAO;
import com.restsiqs.Entities.Academy;

/**
 * Created by devouty on 2015/10/13.
 */
@Transactional
@Service
public class AcademyService {
	@Autowired
	private AcademyDAO academyDAO;

	public void save(Academy t) {
		academyDAO.save(t);
	}

	public void update(Academy t) {
		academyDAO.save(t);
	}

	public void delete(String id) {
		academyDAO.delete(findById(id));
	}

	public Academy findById(String id) {
		return academyDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Academy> findAll() {
		return (List<Academy>) academyDAO.findAll();
	}

}
