package bsd.rest.mvc.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import bsd.rest.mvc.modelo.CategoriesJpaController;
import bsd.rest.mvc.modelo.Category;





@Repository
@Qualifier("categoryDao")
public class CategoryDaoImpl implements CategoryDAO {

	@Autowired
	private CategoriesJpaController  categoriesJpaController;
	
	@Override
	public Category addCategory(Category category) {
		categoriesJpaController.create(category);
		
		
		return category;
	}

	@Override
	public Category editCategory(Category category) {
		return categoriesJpaController.edit(category);
	}

	@Override
	public void deleteCategory(int idCategory) {
		categoriesJpaController.delete(idCategory);
	}


	@Override
	public Category findCategory(int idCategory) {
		
		
		return categoriesJpaController.findById(idCategory);
	}

	@Override
	public List<Category> findAll() {
		return categoriesJpaController.findAll();
	
	}


	
}
