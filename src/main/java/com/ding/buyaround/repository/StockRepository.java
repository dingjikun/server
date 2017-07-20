package com.ding.buyaround.repository;

import com.ding.buyaround.model.StockEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by djk on 2017/7/8.
 */
public interface StockRepository extends CrudRepository<StockEntity, Long>, StockRepositoryCustom {
    StockEntity findByCategoryidAndOwneridAndOssid(Long categoryid, Long ownerid, String ossid);
}
