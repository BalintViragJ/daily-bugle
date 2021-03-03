package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.CategoryType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class CategoryTypeRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CategoryTypeRepository categoryTypeRepository;

    @BeforeEach
    public void init() {
        categoryTypeRepository = new CategoryTypeRepository(jdbcTemplate);
        jdbcTemplate.execute("CREATE TABLE category_type (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45)) ");
    }

    void createDummyDataCategories() {
        jdbcTemplate.update("INSERT INTO category_type (name) VALUES ('történelem'), ('zene'), ('irodalom') ");
    }

    @AfterEach
    void destruct() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS category_type");
    }

    @Test
    void test_getCategories_emptyDatabase() {
        List<CategoryType> categoryTypeList = categoryTypeRepository.getCategories();
        assertTrue(categoryTypeList.isEmpty());
    }

    @Test
    void getCategories() {
        createDummyDataCategories();

        CategoryType history = new CategoryType();
        history.setId(1);
        history.setName("történelem");

        CategoryType music = new CategoryType();
        history.setId(1);
        history.setName("zene");

        CategoryType literature = new CategoryType();
        history.setId(1);
        history.setName("irodalom");

        List<CategoryType> expected = List.of(history, music, literature);

        assertEquals(expected, categoryTypeRepository.getCategories());

    }

    @Test
    void getCategoryById() {
    }

    @Test
    void saveCategory() {
    }

    @Test
    void getCategoryByName() {
    }
}