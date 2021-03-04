package hu.progmasters.ujratervezes.week16.dailybugle.controller;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.CategoryType;
import hu.progmasters.ujratervezes.week16.dailybugle.service.CategoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryTypeController {

    private CategoryTypeService categoryTypeService;

    @Autowired
    public CategoryTypeController(CategoryTypeService categoryTypeService) {
        this.categoryTypeService = categoryTypeService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryType>> getCategories() {
        List<CategoryType> categoryList = categoryTypeService.getCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryType> findCategoryById(@PathVariable int id) {
        CategoryType categoryType = categoryTypeService.findCategoryById(id);
        if (categoryType != null) {
            return new ResponseEntity<>(categoryType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryType> findCategoryByName(@PathVariable String name) {
        CategoryType categoryType = categoryTypeService.findCategoryByName(name);
        if (categoryType != null) {
            return new ResponseEntity<>(categoryType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Void> saveCategory(@RequestBody String name) {
        boolean saveSuccessful = categoryTypeService.saveCategory(name);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
