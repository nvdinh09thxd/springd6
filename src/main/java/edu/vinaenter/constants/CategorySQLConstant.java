package edu.vinaenter.constants;

public class CategorySQLConstant {
	
	public static final String SELECT_ALL_SQL = "SELECT id, name FROM categories ORDER BY id DESC";

	public static final String SAVE_SQL = "INSERT INTO categories(name) VALUES(?)";
	
	public static final String DELETE_SQL = "DELETE FROM categories WHERE id = ?";
	
	public static final String FIND_BY_ID_SQL = "SELECT id, name FROM categories WHERE id = ?";
	
	public static final String UPDATE_SQL = "UPDATE categories SET name = ? WHERE id = ?";
}
