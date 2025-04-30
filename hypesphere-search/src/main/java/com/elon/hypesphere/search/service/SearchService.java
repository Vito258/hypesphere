package com.elon.hypesphere.search.service;

import com.elon.hypesphere.search.vo.SearchParam;
import com.elon.hypesphere.search.vo.SearchResult;

public interface SearchService {

    SearchResult search(SearchParam param);
}
