package com.fhh.manager.service;

import com.fhh.domain.EasyUITreeNode;
import com.fhh.utils.YZResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(Long parentId);

    YZResult createContentCategory(Long parentId, String name);

    YZResult updateContentCategory(Long id, String name);

    YZResult deleteContentCategory(Long id);
}
