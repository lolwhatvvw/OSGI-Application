package ru.ifmo.vovk;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import ru.ifmo.vovk.service.NewsService;

@Component(service = NewsService.class, immediate = true)
public class AifServiceProvider implements NewsService {

    @Activate
    public AifServiceProvider(){
        System.out.println("Aif is registered"); //temporary
    }

    @Override
    public String getNewsName(){
        return "Aif";
    }

    @Override
    public String getNewsUrl(){
        return "https://aif.ru/rss/news.php";
    }
}
