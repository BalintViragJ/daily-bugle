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
    void test_getCategories_3categories() {
        createDummyDataCategories();

        CategoryType history = new CategoryType();
        history.setId(1);
        history.setName("történelem");

        CategoryType music = new CategoryType();
        music.setId(2);
        music.setName("zene");

        CategoryType literature = new CategoryType();
        literature.setId(3);
        literature.setName("irodalom");

        List<CategoryType> expected = List.of(history, music, literature);

        assertEquals(expected, categoryTypeRepository.getCategories());
    }

    @Test
    void test_getCategoryById_notExists_null() {
        createDummyDataCategories();
        assertNull(categoryTypeRepository.getCategoryById(5));
    }

    @Test
    void test_getCategoryById_Exists() {
        createDummyDataCategories();

        CategoryType expected = new CategoryType();
        expected.setId(2);
        expected.setName("zene");

        assertEquals(expected, categoryTypeRepository.getCategoryById(2));
    }

    @Test
    void test_saveCategory_successful() {
        createDummyDataCategories();

        boolean successful = categoryTypeRepository.saveCategory("bulvár");

        CategoryType expected = new CategoryType();
        expected.setId(4);
        expected.setName("bulvár");

        CategoryType actual = jdbcTemplate.queryForObject("SELECT * FROM category_type WHERE id = 4;", ((resultSet, i) -> {
            CategoryType categoryType = new CategoryType();
            categoryType.setId(resultSet.getInt("id"));
            categoryType.setName(resultSet.getString("name"));
            return categoryType;
        }));

        assertEquals(expected, actual);
        assertTrue(successful);

    }

    @Test
    void test_getCategoryByName_notExists_null() {
        createDummyDataCategories();
        assertNull(categoryTypeRepository.getCategoryByName("pletyka"));
    }

    @Test
    void test_getCategoryByName_Exists() {
        createDummyDataCategories();

        CategoryType expected = new CategoryType();
        expected.setId(2);
        expected.setName("zene");

        assertEquals(expected, categoryTypeRepository.getCategoryByName("zene"));
    }
}