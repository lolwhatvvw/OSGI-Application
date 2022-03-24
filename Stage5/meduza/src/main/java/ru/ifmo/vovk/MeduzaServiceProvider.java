package ru.ifmo.vovk;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import ru.ifmo.vovk.service.NewsService;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@Component(service = NewsService.class, immediate = true)
public class MeduzaServiceProvider implements NewsService {

    @Activate
    public MeduzaServiceProvider() {
        System.out.println("Meduza is registered"); //temporary
    }

    @Override
    public String getNewsName() {
        return "Meduza";
    }

    @Override
    public String getNewsUrl() {
        return "https://meduza.io/rss/news";
    }

    @Override
    public List<String> getAllWords() {
        List<String> res = new ArrayList<>();
        try {
            URL weburl = new URL(getNewsUrl());
            Proxy webProxy
                    = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("138.3.245.81", 8080)); //todo: set property
            HttpURLConnection webProxyConnection
                    = (HttpURLConnection) weburl.openConnection(webProxy);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(webProxyConnection));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                res.addAll(List.of(entry.getTitle()
                        .replaceAll("\\p{Z}", "\s")
                        .replaceAll("[^\\s-\\wА-Яа-я%]", "").toLowerCase().split("\s")));
            }
        } catch (IOException | FeedException e) {
            e.printStackTrace(); //todo
        }
        return res;
    }
}
