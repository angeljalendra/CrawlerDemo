package rest.crawler.demo.service.impl;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import rest.crawler.demo.exception.BadRequest400Exception;
import rest.crawler.demo.model.Resource;
import rest.crawler.demo.service.CrawlerService;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	private Resource resource;
	private HashSet<Resource> linksSet = new HashSet<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerServiceImpl.class);

	@Override
	public HashSet<Resource> doCrawling(String url) {
		LOGGER.info("Enter doCrawling method of CrawlerServiceImpl.class");
		if (StringUtils.isEmpty(url)) {
			LOGGER.error("BadRequest400Exception : Url is Empty1");
			throw new BadRequest400Exception();
		} else {
			try {
				Document doc = Jsoup.connect(url).get();
				Elements newsHeadlines = doc.select("a[href]");
				newsHeadlines.stream().forEach(headline -> {

					if (!StringUtils.isEmpty(headline.attr("abs:href")) && headline.attr("abs:href").contains(url)) {
						resource = new Resource();
						resource.setLink(headline.attr("abs:href"));
						resource.setTitle(headline.text());
						linksSet.add(resource);
					}
				});
			} catch (IOException e) {
				LOGGER.error("Exception occure:", e);
			}
		}
		LOGGER.info("Exit doCrawling method of CrawlerServiceImpl.class");
		return linksSet;
	}

}
