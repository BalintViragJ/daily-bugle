package hu.progmasters.ujratervezes.week16.dailybugle.service;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.CategoryType;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.CategoryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTypeService {

    CategoryTypeRepository categoryTypeRepository;

    @Autowired
    public CategoryTypeService(CategoryTypeRepository categoryRepository) {
        this.categoryTypeRepository = categoryRepository;
    }

    public List<CategoryType> getCategories() {
        List<CategoryType> categoryList = categoryTypeRepository.getCategories();
        return categoryList;
    }

    public CategoryType findCategoryById(int id) {
        CategoryType categoryType = categoryTypeRepository.findCategoryById(id);
        return categoryType;

    }

    public CategoryType findCategoryByName(String name) {
        CategoryType categoryType = categoryTypeRepository.findCategoryByName(name);
        return categoryType;
    }

    public boolean saveCategory(String name) {
        if (categoryTypeRepository.findCategoryByName(name) == null) {
            boolean saveSuccessful = categoryTypeRepository.saveCategory(name);
            return saveSuccessful;
        } else {
            return false;
        }
    }
}
