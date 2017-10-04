package bsd.rest.mvc.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import bsd.rest.mvc.exceptions.CategoryNotFoundException;


@Component
public class CategoriesJpaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory emf;
	
	public CategoriesJpaController(){
		
		this.emf = Persistence.createEntityManagerFactory("UP");
		
	}
	private EntityManager getEntityManager() {
		return this.emf.createEntityManager();
	}
	
	public Category create(Category category){

		
		EntityManager entityManager = null;
		
		try{
			entityManager = getEntityManager();
			
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			
			
			entityManager.persist(category);

			
			transaction.commit();
			return category;
			
		}finally {
			if(entityManager!= null){
				entityManager.close();
			}
		}
	}
	
	public Category edit(Category category){

		
		EntityManager entityManager = null;
		
		try{
			entityManager = getEntityManager();
			
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			Category categoryManager = entityManager.find(Category.class, category.getCategoriesId());
			
			categoryManager.setCategoriesName(category.getCategoriesName());
			categoryManager.setDescription(category.getDescription());
			
			entityManager.merge(categoryManager);
			
			transaction.commit();
			return categoryManager;
			
		}finally {
			if(entityManager!= null){
				entityManager.close();
			}
		}
	}
	
public void delete(int categoryId){

		
		EntityManager entityManager = null;
		
		try{
			entityManager = getEntityManager();
			
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			Category categoryManager = entityManager.find(Category.class, categoryId);
			
			
			entityManager.remove(categoryManager);
			
			transaction.commit();
			
			
		}finally {
			if(entityManager!= null){
				entityManager.close();
			}
		}
	}


public Category findByName(String categoriesName){

	
	EntityManager entityManager = null;
	
	try{
		entityManager = getEntityManager();
		
		Query query = entityManager.createNamedQuery("Category.findByName");
		query.setParameter("categoriesName", categoriesName);
		
		Category category = (Category) query.getSingleResult();
		
		
		return category;
	}finally {
		if(entityManager!= null){
			entityManager.close();
		}
	}
}


public Category findById(int categoryId){

	
	EntityManager entityManager = null;
	
	try{
		entityManager = getEntityManager();	
			
		
		Category category =  entityManager.find(Category.class, categoryId);
		
		if(category == null){
			throw new CategoryNotFoundException(categoryId);
		}
		return category;
	}finally {
		if(entityManager!= null){
			entityManager.close();
		}
	}
}


	private List<Category>findAll(boolean all, int maxResults, int firstResult){
	EntityManager entityManager = null;
		
		try{
			entityManager = getEntityManager();	
			
			CriteriaQuery<Object>criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
			
			criteriaQuery.select(criteriaQuery.from(Category.class));// esto equivale a un coge todo de la tabla
			
			Query query = entityManager.createQuery(criteriaQuery);
			
			if (!all){
			
				query.setMaxResults(maxResults);
				query.setFirstResult(firstResult);
				
			}
			
			return (List<Category>) query.getResultList();
			
			
			
		}finally {
			if(entityManager!= null){
				entityManager.close();
			}
		}
	}

	public List<Category>findAll(){
		return findAll(true, -1, -1);
	}
	
	public List<Category>findAll(int maxResults, int firstResults){
		return findAll(false, maxResults, firstResults);
	}
	
	public int getCategoryCount(){
	EntityManager entityManager = null;
		
		try{
			entityManager = getEntityManager();	
			
			CriteriaQuery<Object>criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
			
			Root<Category>root = criteriaQuery.from(Category.class);
			
			
			criteriaQuery.select(entityManager.getCriteriaBuilder().count(root));
			
			Query query = entityManager.createQuery(criteriaQuery);
			
			return ((Long)query.getSingleResult()).intValue();
				
			
			
		}finally {
			if(entityManager!= null){
				entityManager.close();
			}
		}
	}





	

}
