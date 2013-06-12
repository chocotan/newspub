package io.loli.newspub.ywl.jsfbean;

import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.jsr330.RequestScoped;
import io.loli.newspub.ywl.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CatBean {
	private final static int MAXCOUNT = 10;
	@Inject
	@Named("catService")
	private CategoryService catService;

	private Category cat;
	private Map<Integer, Boolean> deleted;

	@PostConstruct
	private void init() {
		cat = new Category();
	}

	public void add() {
		catService.save(cat);
	}

	public String delete() {
		for (Object obj : deleted.entrySet().toArray()) {
			@SuppressWarnings("unchecked")
			Entry<Integer, Boolean> entry = (Entry<Integer, Boolean>) obj;
			if (!entry.getValue()) {
				deleted.remove(entry.getKey());
			}
		}
		int[] ids = new int[deleted.size()];
		int count = 0;
		for (int id : deleted.keySet()) {
			ids[count++] = id;
		}
		catService.deleteByIds(ids);
		return "list";
	}

	public List<Category> listPage(int page) {
		if (page == 0)
			page = 1;
		if (deleted == null) {
			deleted = new HashMap<Integer, Boolean>();
		}
		List<Category> catList = catService.listPage(MAXCOUNT * (page - 1),
				MAXCOUNT);
		for (Category cat : catList) {
			deleted.put(cat.getId(), false);
		}
		return catList;
	}

	public void initCat(int id) {
		if (cat == null || (cat.getId() != id)) {
			cat = catService.findById(id);
		}
	}
	
	public Category findById(int id){
		return catService.findById(id);
	}
	
	public String update(){
		catService.update(cat);
		return "list";
	}

	public List<Category> list() {
		if (deleted == null) {
			deleted = new HashMap<Integer, Boolean>();
		}
		List<Category> catList = catService.list();
		for (Category cat : catList) {
			deleted.put(cat.getId(), false);
		}
		return catList;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public Map<Integer, Boolean> getDeleted() {
		return deleted;
	}

	public void setDeleted(Map<Integer, Boolean> deleted) {
		this.deleted = deleted;
	}

}
