This is a rest api demo which has exposed two end points . one will be used for crawling and other will generate sitemap of crawled url.

1.get code in your local machine 
3. clean and build project.
2.using command prompt go to path : [local machine path where you kept code]\WebCrawlerDemo\Crawler\target 
3.now execute java -jar WebCrawler.jar
4.Once server is up one can get crawl data by hitting url : http://localhost:8080/crawl?url=https://www.prudential.co.uk/
  and sitemap : http://localhost:8080/sitemap?url=https://www.prudential.co.uk/
