package com.ding.buyaround.repository;

import com.ding.buyaround.model.StockEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by djk on 2017/7/8.
 */
//自定义jpa命名规则很重要
//StockRepository
//StockRepositoryCustom  必须保持custom后缀
//StockRepositoryImpl    注意没有custom
public class StockRepositoryImpl implements StockRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    public static final String spliter = "@";

    public static final String placeholder = "X";

    @Transactional
    @Override
    public void saveMyStock(StockEntity stock){

        if (stock.getImages().equals(""))
        {
            stock.setImages(placeholder);
        }

        if (stock.getVideos().equals(""))
        {
            stock.setVideos(placeholder);
        }

        //native
        String sqlCheckExist = "select count(1) from stock s where s.ossid=:ossid";
        //jpql
        //String sqlCheckExist = "select count(s) from stock s where s.ossid='ossid'";
        int ret = ((Number)entityManager.createNativeQuery(sqlCheckExist)
                .setParameter("ossid", stock.getOssid())
                .getSingleResult()).intValue();

        if (ret == 0) {
            entityManager.persist(stock);
        }
        else {
            String sqlConcat = "update stock s set s.videos=concat(s.videos,'" +
                    spliter + "',:video),s.images=concat(s.images,'" +
                    spliter + "',:image) where s.ossid=:ossid";

            entityManager.createNativeQuery(sqlConcat)
                    .setParameter("ossid", stock.getOssid())
                    .setParameter("video", stock.getVideos())
                    .setParameter("image", stock.getImages())
                    .executeUpdate();
        }
    }
}
