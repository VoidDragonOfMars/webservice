package bsd.rest.mvc.exceptions;

public class CategoryNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int categoryId;

	public CategoryNotFoundException(int categoryId) {
		super();
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	
	

}
