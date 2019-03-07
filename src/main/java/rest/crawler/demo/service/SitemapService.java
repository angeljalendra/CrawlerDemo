package rest.crawler.demo.service;

import java.io.File;
import java.util.HashSet;

import rest.crawler.demo.model.Resource;

public interface SitemapService {

	public File generateSitemap(String url);
}
