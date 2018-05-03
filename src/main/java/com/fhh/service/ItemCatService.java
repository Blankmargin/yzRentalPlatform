package com.fhh.service;

import com.fhh.domain.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(String parentId);
}
