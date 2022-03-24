package ru.ifmo.vovk;

import org.apache.felix.service.command.annotations.GogoCommand;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import ru.ifmo.vovk.service.NewsService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@GogoCommand(scope = "news", function = "stats")
@Component(service = Object.class, immediate = true)
public class NewsCommand {
    private final List<NewsService> newsList = new CopyOnWriteArrayList<>();

    @Reference(
            service = NewsService.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC
    )
    public void bindService(NewsService newsService) {
        newsList.add(newsService);
        System.out.println(newsService.getNewsName() + " is registered");
    }

    public void unbindService(NewsService newsService) {
        newsList.remove(newsService);
        System.out.println( "Unbound: " + newsService.getNewsName() );
    }

    public String stats() {
        if (!newsList.isEmpty()) {
            StringBuilder str = new StringBuilder();
            for (NewsService s : newsList) {
                str.append(s.getNewsName()).append(" ");
            }
            return "Available news sources: " + System.lineSeparator()
                    + String.join(", ", str.toString()) + System.lineSeparator()
                    + "usage: news:stats {news_provider_name}";
        } else
            return "No services registered!";
    }

    public String stats(String[] name) {
        if (name.length > 1){
            return "usage: news:stats {news_provider_name}";
        }
        if (!newsList.isEmpty()) {
            NewsService newsService = newsList.stream()
                    .filter(customer -> name[0].equals(customer.getNewsName()))
                    .findAny()
                    .orElse(null);

            if(newsService!= null) return String.join(", ", newsService.getTopWords());
             else return "Service " + name[0] + " is not registered";

        } else return "There are no services registered!";
    }
}

