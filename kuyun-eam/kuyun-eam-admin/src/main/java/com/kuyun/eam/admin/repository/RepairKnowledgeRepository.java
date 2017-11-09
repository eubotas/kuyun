package com.kuyun.eam.admin.repository;

import com.kuyun.eam.admin.model.RepairKnowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017-10-27.
 */
@Repository
public interface RepairKnowledgeRepository extends ElasticsearchRepository<RepairKnowledge, String> {


    Page<RepairKnowledge> findByCodeOrTagOrDescriptionOrderByCreateTimeDesc(String code, String tag, String description, Pageable pageable);

}
