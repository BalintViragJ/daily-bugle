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

    public CategoryType getCategoryById(int id) {
        CategoryType categoryType = categoryTypeRepository.getCategoryById(id);
        return categoryType;

    }

    public CategoryType getCategoryByName(String name) {
        CategoryType categoryType = categoryTypeRepository.getCategoryByName(name);
        return categoryType;
    }

    public boolean saveCategory(String name) {
        if (categoryTypeRepository.getCategoryByName(name) != null) {
            boolean saveSuccessful = categoryTypeRepository.saveCategory(name);
            return saveSuccessful;
        } else {
            return false;
        }
    }
}
