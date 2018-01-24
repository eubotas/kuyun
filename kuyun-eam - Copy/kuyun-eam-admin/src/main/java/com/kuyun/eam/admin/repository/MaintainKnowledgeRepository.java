package com.kuyun.eam.admin.repository;

import com.kuyun.eam.admin.model.MaintainKnowledge;
import com.kuyun.eam.admin.model.RepairKnowledge;
import com.kuyun.eam.admin.model.TrainingVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017-10-27.
 */
@Repository
public interface MaintainKnowledgeRepository extends ElasticsearchRepository<MaintainKnowledge, String> {

    Page<MaintainKnowledge> findByTitleOrTagOrContentOrderByCreateTimeDesc(String title, String tag, String content, Pageable pageable);

}
