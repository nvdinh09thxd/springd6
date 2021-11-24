package edu.vinaenter.controllers;

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
import edu.vinaenter.exceptions.NameException;
import edu.vinaenter.models.Category;
import edu.vinaenter.services.CategoryService;

@Controller
@RequestMapping("jdbc")
public class TestJdbcController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("list")
	public String list() {
		return "test/selectAll";
	}

	@GetMapping("selectAll")
	public String selectAll(Model model, RedirectAttributes re) {
		try {
			List<Category> categories = categoryService.getAll();
			model.addAttribute("categories", categories);
			return "test/selectAll";
		} catch (Exception e) {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
			return "redirect:/jdbc/list";
		}
	}

	@GetMapping("cat/addCat")
	public String addCat() {
		return "test/add";
	}

	@PostMapping("cat/addCat")
	public String addCat(@ModelAttribute Category category, RedirectAttributes re) throws NameException {

		try {
			int saved = categoryService.save(category);

			if (DaoUtil.isSuccess(saved)) {
				re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_ADD);
				return "redirect:/jdbc/selectAll";
			} else {
				re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
				return "redirect:/jdbc/cat/addCat";
			}
		} catch (Exception e) {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
			return "redirect:/jdbc/cat/addCat";
		}
	}

	@GetMapping("cat/del/{id}")
	public String delCat(@PathVariable int id, RedirectAttributes re) {
		try {
			int deleted = categoryService.delete(id);
			if (DaoUtil.isSuccess(deleted)) {
				re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_DEL);
			} else {
				re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
			}
		} catch (Exception e) {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
		}
		return "redirect:/jdbc/selectAll";
	}

	@GetMapping("cat/edit/{id}")
	public String editCat(@PathVariable int id, Model model, RedirectAttributes re) {
		try {
			Category cat = categoryService.findById(id);
			model.addAttribute("cat", cat);
			return "test/edit";
		} catch (Exception e) {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
			return "redirect:/jdbc/selectAll";
		}
	}

	@PostMapping("cat/edit/{id}")
	public String editCat(@PathVariable int id, @ModelAttribute Category cat, Model model, RedirectAttributes re) {
		cat.setId(id);
		try {
			int updated = categoryService.update(cat);
			if (DaoUtil.isSuccess(updated)) {
				re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_UPDATE);
				return "redirect:/jdbc/selectAll";
			} else {
				re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
				return "redirect:/jdbc/cat/edit/" + id;
			}
		} catch (Exception e) {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERROR);
			return "redirect:/jdbc/cat/edit/" + id;
		}
	}
}
