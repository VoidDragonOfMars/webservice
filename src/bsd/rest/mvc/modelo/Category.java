package bsd.rest.mvc.modelo;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQueries(
		{
			@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c"),
			
			@NamedQuery(name="Category.findByName", query="SELECT c FROM Category c WHERE c.categoriesName = :categoriesName")
		}
		)

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CATEGORIES_ID")
	private int categoriesId;

	@Column(name="CATEGORIES_NAME")
	private String categoriesName;

	private String description;
	

	public Category() {
	}

	public int getCategoriesId() {
		return this.categoriesId;
	}

	public void setCategoriesId(int categoriesId) {
		this.categoriesId = categoriesId;
	}

	public String getCategoriesName() {
		return this.categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [categoriesId=" + categoriesId + ", categoriesName=" + categoriesName + ", description="
				+ description + "]";
	}

}