package com.fhh.protal.service;

import com.fhh.domain.SearchResult;

public interface SearchService {
    SearchResult search(String keyword,int page,int rows) throws Exception;
}
