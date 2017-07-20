package com.ding.buyaround.controller;

import com.ding.buyaround.model.CategoryInfo;
import com.ding.buyaround.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by djk on 2017/7/6.
 */
@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/getAll")
    public List<CategoryInfo> getAllCategory() {
        return categoryService.getAllCategory();
    }
}
