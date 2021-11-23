package edu.vinaenter.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.daos.CategoryDao;
import edu.vinaenter.exceptions.NameException;
import edu.vinaenter.models.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public int save(Category category) throws NameException {
		String name = category.getName();
		String[] arNames = { "abc", "cdx" };
		if (Arrays.asList(arNames).contains(name)) {
			throw new NameException("Please enter other words!");
		}
		return categoryDao.save(category);
	}

	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	public int delete(int id) {
		return categoryDao.delete(id);
	}

	public Category findById(int id) {
		return categoryDao.findById(id);
	}

	public int update(Category cat) {
		return categoryDao.update(cat);
	}

}
