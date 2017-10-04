package bsd.rest.mvc.dao;

import java.util.List;

import bsd.rest.mvc.modelo.Category;

public interface CategoryDAO {
	Category addCategory(Category category);
	Category editCategory(Category category);
	void deleteCategory(int idCategory);
	Category findCategory(int idCategory);
	List<Category> findAll();
}
