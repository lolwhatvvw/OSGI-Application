package ru.ifmo.vovk;

import org.osgi.service.component.annotations.Component;
import ru.ifmo.vovk.service.AbstractNewsService;
import ru.ifmo.vovk.service.NewsService;

@Component(service = NewsService.class, immediate = true)
public class CNNServiceProvider extends AbstractNewsService implements NewsService {
    @Override
    public String getNewsName(){
        return "CNN";
    }

    @Override
    public String getNewsUrl(){
        return "http://rss.cnn.com/rss/cnn_latest.rss";
    }
}
