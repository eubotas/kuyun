package com.kuyun.eam.admin.repository;

import com.kuyun.eam.admin.model.TrainingDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017-10-27.
 */
@Repository
public interface TrainingDocRepository extends ElasticsearchRepository<TrainingDoc, String> {

    Page<TrainingDoc> findByTitleContainingOrTagContainingOrDescriptionContainingOrderByCreateTimeDesc(String title, String tag, String description, Pageable pageable);

}
