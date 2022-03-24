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
    }

    public void unbindService(NewsService newsService) {
        newsList.remove(newsService);
    }

    public String stats() {
        if (!newsList.isEmpty()) {
            StringBuilder str = new StringBuilder();
            for (NewsService s : newsList) {
                str.append(s.getNewsName());
            }
            return String.join(", ", str.toString());
        } else
            return "No services registered!";
    }

    public String stats(String name) { //todo: two args, check length, use second for limit words parameter
        if (!newsList.isEmpty()) {
            NewsService newsService = newsList.stream()
                    .filter(customer -> name.equals(customer.getNewsName()))
                    .findAny()
                    .orElse(null);

            if(newsService!= null) return String.join(", ", newsService.getTopWords());
             else return "Service " + name + " is not registered";

        } else return "There are no services registered!";
    }
}

