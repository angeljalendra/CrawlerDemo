package rest.crawler.demo.service;

import java.util.HashSet;

import rest.crawler.demo.model.Resource;

public interface CrawlerService {
	public HashSet<Resource> doCrawling(String url);
}
