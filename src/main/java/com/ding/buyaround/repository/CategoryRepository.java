package com.ding.buyaround.repository;

import com.ding.buyaround.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by djk on 2017/7/6.
 */
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAll();
}
