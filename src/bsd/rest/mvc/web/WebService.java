package bsd.rest.mvc.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import bsd.rest.mvc.exceptions.CategoryNotFoundException;
import bsd.rest.mvc.modelo.Category;
import bsd.rest.mvc.services.CategoryService;
import bsd.rest.mvc.exceptions.Error;;



@RestController
@RequestMapping(value="/webservice")
public class WebService {
	
	@Autowired
	private CategoryService service ;
	
	
	@RequestMapping(value="/list/",method=RequestMethod.GET,produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Category> getAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="/{categoryId}/",method=RequestMethod.GET,produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Category getById(@PathVariable("categoryId")int categoryId){
		return service.findCategory(categoryId);
	}
	
	@RequestMapping(value="/{categoryId}/",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable("categoryId")int categoryId){
		service.deleteCategory(categoryId);
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Category> addCategory(@RequestBody Category category, UriComponentsBuilder builder){
		Category newCategory = service.addCategory(category);
		
		HttpHeaders headers = new HttpHeaders();
		
		URI locationUri = builder.path("/categories").path(String.valueOf(newCategory.getCategoriesId())).build().toUri();
		
		headers.setLocation(locationUri);
		
		ResponseEntity<Category>entity = new ResponseEntity<>(newCategory, headers, HttpStatus.CREATED);
		
		return entity;
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, UriComponentsBuilder builder){
		Category modifiedCategory = service.addCategory(category);
		
		HttpHeaders headers = new HttpHeaders();
		
		URI locationUri = builder.path("/categories").path(String.valueOf(modifiedCategory.getCategoriesId())).build().toUri();
		
		headers.setLocation(locationUri);
		
		ResponseEntity<Category>entity = new ResponseEntity<>(modifiedCategory, headers, HttpStatus.OK);
		
		return entity;
		
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody Error managedError ( CategoryNotFoundException ex){
		int categoryId = ex.getCategoryId();
		
		Error error = new Error(categoryId,"Category " + categoryId + " not found");
		
		return error;
	}

}


