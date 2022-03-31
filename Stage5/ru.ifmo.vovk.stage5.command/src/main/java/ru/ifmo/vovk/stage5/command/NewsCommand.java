package ru.ifmo.vovk.stage5.command;

import org.apache.felix.service.command.annotations.GogoCommand;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import ru.ifmo.vovk.stage5.service.NewsService;

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
            return "Available news providers: " + System.lineSeparator()
                    + String.join(", ", str.toString()) + System.lineSeparator()
                    + "usage: news:stats {news_provider_name}";
        }

        return "No services registered!";
    }

    public String stats(String[] name) {
        if (name == null || name.length != 1 || name[0].isBlank()){
            return "usage: news:stats {news_provider_name}";
        }
        if (!newsList.isEmpty()) {
            NewsService newsService = newsList.stream()
                    .filter(customer -> name[0].equals(customer.getNewsName()))
                    .findAny()
                    .orElse(null);

            return (newsService != null) ? String.join(", ", newsService.getTopWords())
                                         : "Service " + name[0] + " is not registered";
        }

        return "No services registered!";
    }
}

