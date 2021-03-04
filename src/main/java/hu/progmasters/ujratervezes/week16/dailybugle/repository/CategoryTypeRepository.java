package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryTypeRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CategoryType> getCategories() {
        String sql = "SELECT * FROM category_type ";
        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            CategoryType categoryType = new CategoryType();
            categoryType.setId(resultSet.getInt("id"));
            categoryType.setName(resultSet.getString("name"));
            return categoryType;
        });
    }

    public CategoryType findCategoryById(int id) {
        try {
            String sql = "SELECT * FROM category_type WHERE id = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, rowNumber) -> {
                CategoryType categoryType = new CategoryType();
                categoryType.setId(resultSet.getInt("id"));
                categoryType.setName(resultSet.getString("name"));
                return categoryType;
            }));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CategoryType findCategoryByName(String name) {
        try {
            String sql = "SELECT * FROM category_type WHERE name = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{name}, ((resultSet, rowNumber) -> {
                CategoryType categoryType = new CategoryType();
                categoryType.setId(resultSet.getInt("id"));
                categoryType.setName(resultSet.getString("name"));
                return categoryType;
            }));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean saveCategory(String name) {
        String sql = "INSERT INTO category_type (name) VALUES (?) ";
        try {
            int rowsAffected = jdbcTemplate.update(sql, name);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
