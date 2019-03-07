package rest.crawler.demo.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redfin.sitemapgenerator.WebSitemapGenerator;

import rest.crawler.demo.model.Resource;
import rest.crawler.demo.service.CrawlerService;
import rest.crawler.demo.service.SitemapService;
import rest.crawler.demo.constants.*;
import rest.crawler.demo.exception.BadRequest400Exception;

@Service
public class SitemapServiceImpl implements SitemapService {

	@Autowired
	private CrawlerService crawlerService;
	private static final Logger LOGGER = LoggerFactory.getLogger(SitemapServiceImpl.class);

	@Override
	public File generateSitemap(String url) {
		return getMap(url);
	}

	private File getMap(String url) {
		LOGGER.info("Enter getMap method of SitemapServiceImpl.class");
		HashSet<Resource> linksSet = crawlerService.doCrawling(url);

		String workingDirectory = Paths.get(".").toAbsolutePath().toString();
		File dir = new File(workingDirectory);
		File siteMap = new File(workingDirectory.substring(0, workingDirectory.length() - 1) + Constants.FILE_NAME
				+ Constants.FILE_EXT);
		WebSitemapGenerator wsg;
		if (siteMap == null || dir == null) {
			throw new BadRequest400Exception();
		}
		try {
			wsg = WebSitemapGenerator.builder(url, dir).fileNamePrefix(Constants.FILE_NAME).build();
			URL main = new URL(url);
			linksSet.stream().forEach(res -> {
				try {
					URL u = new URL(res.getLink());
					String p = u.getFile().substring(0, u.getFile().length());
					String base = main.getProtocol() + "://" + u.getHost() + p;
					wsg.addUrl(base);
				} catch (MalformedURLException e) {
					LOGGER.error("Exception occure:", e);
				}
			});
			wsg.write();
		} catch (IOException e) {
			LOGGER.error("Exception occure:", e);
		}
		LOGGER.info("Exit getMap method of SitemapServiceImpl.class");
		return siteMap;
	}
}
