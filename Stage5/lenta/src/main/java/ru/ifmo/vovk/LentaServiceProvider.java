package ru.ifmo.vovk;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import ru.ifmo.vovk.service.NewsService;

@Component(service = NewsService.class, immediate = true)
public class LentaServiceProvider  implements NewsService {

    @Activate
    public LentaServiceProvider(){
        System.out.println("Lenta is registered"); //temporary
    }
    @Override
    public String getNewsName(){
        return "Lenta";
    }

    @Override
    public String getNewsUrl(){
        return "https://api.lenta.ru/rss";
    }
}
