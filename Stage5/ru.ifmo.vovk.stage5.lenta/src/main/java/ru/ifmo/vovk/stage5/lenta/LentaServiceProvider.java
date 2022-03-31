package ru.ifmo.vovk.stage5.lenta;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import ru.ifmo.vovk.stage5.service.AbstractNewsService;
import ru.ifmo.vovk.stage5.service.NewsService;

@Component(service = NewsService.class, immediate = true)
@Designate(ocd = LentaServiceProvider.NewsConfig.class)
public class LentaServiceProvider  extends AbstractNewsService implements NewsService {

    @ObjectClassDefinition(name = "Lenta News Service Configuration",
            description = "Service Configuration to modify/customise URLs, names and amount of words to show")
 public @interface NewsConfig {

        @AttributeDefinition(
                name = "news name",
                description = "Name of the news provider",
                type = AttributeType.STRING
        )
        String newsName() default "Lenta";

        @AttributeDefinition(
                name = "news url",
                description = "URL for particular news provider",
                type = AttributeType.STRING
        )
        String newsUrl() default "https://api.lenta.ru/rss";

        @AttributeDefinition(
                name = "limit",
                description = "limit of the top words in Lenta news provider",
                type = AttributeType.INTEGER
        )
        int limit() default 10;
    }

    private String newsName;
    private String newsUrl;

    @Override
    public String getNewsName(){
        return newsName;
    }
    @Override
    public String getNewsUrl(){
        return newsUrl;
    }

    @Activate
    public void activate(NewsConfig newsConfig){
        newsName = newsConfig.newsName();
        newsUrl = newsConfig.newsUrl();
        limit = newsConfig.limit();
    }
}
