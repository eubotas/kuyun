package com.kuyun.eam.admin.repository;

import com.kuyun.eam.admin.model.Tag;
import com.kuyun.eam.admin.model.TrainingVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2017-10-27.
 */
@Repository
public interface TagRepository extends ElasticsearchRepository<Tag, String> {

    public Page<TrainingVideo> findAllByOrderByTagAsc(Pageable pageable);
}
