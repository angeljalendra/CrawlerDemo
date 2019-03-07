package rest.crawler.demo.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.crawler.demo.constants.Constants;
import rest.crawler.demo.model.Resource;
import rest.crawler.demo.service.CrawlerService;
import rest.crawler.demo.service.SitemapService;

@RestController
public class CrawlerRequestController {

	@Autowired
	private CrawlerService crawlerService;
	@Autowired
	private SitemapService sitemapService;

	@GetMapping("/")
	public void display() {
		System.out.println("working");
	}

	@GetMapping("/crawl")
	public HashSet<Resource> crawl(@RequestParam(name = "url") String url) {
		return crawlerService.doCrawling(url);
	}

	@GetMapping("/sitemap")
	public ResponseEntity<FileSystemResource> getSitemap1(@RequestParam(name = "url") String url) {
		FileSystemResource resource = new FileSystemResource(sitemapService.generateSitemap(url));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + Constants.FILE_NAME)
				.contentType(MediaType.APPLICATION_XML).contentLength(resource.getFile().length()).body(resource);
	}

}
