package bsd.rest.mvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsd.rest.mvc.dao.CategoryDAO;
import bsd.rest.mvc.modelo.Category;

@Service("categoryService")

public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryDAO dao;
	@Override
	public Category addCategory(Category category) {
		 return dao.addCategory(category);
		
	}

	@Override
	public Category editCategory(Category category) {
		return dao.editCategory(category);
		
	}

	@Override
	public void deleteCategory(int idCategory) {
		dao.deleteCategory(idCategory);
		
	}

	@Override
	public Category findCategory(int idCategory) {
		
		return dao.findCategory(idCategory) ;
	}

	@Override
	public List<Category> findAll() {
		
		return dao.findAll();
	}


	
	
}
