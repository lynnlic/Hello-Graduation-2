package com.cms.dao;

import com.cms.entity.TemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDao {
    List<TemplateEntity> getAllTemplate();
}
