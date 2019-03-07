package rest.crawler.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rest.crawler.demo.service.CrawlerService;
import rest.crawler.demo.service.SitemapService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private CrawlerService crawlerService;

	@Autowired
	private SitemapService sitemapService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void doCrawling() {
		String url = "https://www.prudential.co.uk/";
		crawlerService.doCrawling(url);

	}

	@Test
	public void generateSitemap() {
		String url = "https://www.prudential.co.uk/";
		sitemapService.generateSitemap(url);

	}
}
