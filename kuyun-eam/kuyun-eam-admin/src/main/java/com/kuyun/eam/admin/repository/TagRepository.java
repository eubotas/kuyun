package com.kuyun.eam.admin.repository;

import com.kuyun.eam.admin.model.Tag;
import com.kuyun.eam.admin.model.TrainingVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017-10-27.
 */
@Repository
public interface TagRepository extends ElasticsearchRepository<Tag, String> {

    Page<TrainingVideo> findAllByOrderByTagAsc(Pageable pageable);

    Tag findOneByTag(String tag);
}