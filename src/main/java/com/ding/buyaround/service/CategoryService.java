package com.ding.buyaround.service;

import com.ding.buyaround.model.CategoryEntity;
import com.ding.buyaround.model.CategoryInfo;
import com.ding.buyaround.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by djk on 2017/7/6.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryInfo> getAllCategory() {

        return categoryRepository.findAll().stream()
                .map(cat -> {
                    CategoryInfo rc = new CategoryInfo();
                    rc.setId(cat.getId());
                    rc.setName(cat.getName());
                    return rc;
                }).collect(Collectors.toList());
    }
}
