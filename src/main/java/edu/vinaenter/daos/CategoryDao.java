package edu.vinaenter.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.vinaenter.constants.CategorySQLConstant;
import edu.vinaenter.models.Category;

@Repository
public class CategoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Category> getAll() {
		return jdbcTemplate.query(CategorySQLConstant.SELECT_ALL_SQL, new BeanPropertyRowMapper<>(Category.class));
	}

	public int save(Category category) {
		return jdbcTemplate.update(CategorySQLConstant.SAVE_SQL, new Object[] { category.getName() });
	}

	public int delete(int id) {
		return jdbcTemplate.update(CategorySQLConstant.DELETE_SQL, new Object[] { id });
	}

	public Category findById(int id) {
		return jdbcTemplate.queryForObject(CategorySQLConstant.FIND_BY_ID_SQL,
				new BeanPropertyRowMapper<>(Category.class), new Object[] { id });
	}

	public int update(Category cat) {
		return jdbcTemplate.update(CategorySQLConstant.UPDATE_SQL, new Object[] { cat.getName(), cat.getId() });
	}
}
