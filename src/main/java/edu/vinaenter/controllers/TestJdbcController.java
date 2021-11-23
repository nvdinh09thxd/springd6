package edu.vinaenter.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.commons.DaoUtil;
import edu.vinaenter.constants.MessageConstant;
import edu.vinaenter.daos.CategoryDao;
import edu.vinaenter.exceptions.NameException;
import edu.vinaenter.models.Category;
import edu.vinaenter.services.CategoryService;

@Controller
@RequestMapping("jdbc")
public class TestJdbcController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("selectAll")
	public String selectAll(Model model) {
		List<Category> categories = categoryService.getAll();
		model.addAttribute("categories", categories);
		return "test/selectAll";
	}
	
	@GetMapping("cat/addNew")
	public String addNew(Model model) {
		return "test/add";
	}
	
	@PostMapping("cat/addNew")
	public String addNew(@ModelAttribute Category category, RedirectAttributes re) throws NameException {
		
		int saved = categoryService.save(category);
		
		if(DaoUtil.isSuccess(saved)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_ADD);
			return "redirect:/jdbc/selectAll";
		} else {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
		}
		return "redirect:/cat/addNew";
	}
	
	@GetMapping("cat/del/{id}")
	public String addNew(@PathVariable int id, RedirectAttributes re) {
		int deleted = categoryService.delete(id);
		if(DaoUtil.isSuccess(deleted)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_DEL);
		} else {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
		}
		return "redirect:/jdbc/selectAll";
	}
	
	@GetMapping("cat/edit/{id}")
	public String addNew(@PathVariable int id, Model model) {
		Category cat = categoryService.findById(id);
		model.addAttribute("cat", cat);
		return "test/edit";
	}
	
	@PostMapping("cat/edit/{id}")
	public String addNew(@PathVariable int id, 
			@ModelAttribute Category cat, 
			Model model, RedirectAttributes re) {
		cat.setId(id);
		int updated = categoryService.update(cat);
		if(DaoUtil.isSuccess(updated)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_UPDATE);
			return "redirect:/jdbc/selectAll";
		} else {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
		}
		return "redirect:/cat/edit/"+id;
	}
}
