package com.ding.buyaround.repository;

import com.ding.buyaround.model.ChatMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by djk on 2017/7/24.
 */
public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessageEntity, Long> {
    Page<ChatMessageEntity> findAllBySenderidOrderByIdDesc(Pageable page, Long senderid);

    Long countBySenderid(Long senderid);
}
