package ru.ifmo.vovk.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface NewsService {

    String getNewsName();

    String getNewsUrl();

    default  List<String> getAllWords(){
        List<String> res = new ArrayList<>();
        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(createURLForService()));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                res.addAll(List.of(entry.getTitle().toLowerCase().split(" ")));
            }
        } catch (IOException | FeedException e) {
            e.printStackTrace(); //todo
        }
        return res;
    }

    private URL createURLForService(){
        try {
            return new URL(getNewsUrl());
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

  default  List<String> getTopWords(){
        Map<String, Integer> map = getFrequencies();

        return map.entrySet().stream()
              .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
              .map(Map.Entry::getKey)
              .limit(10)
              .collect(Collectors.toList());
    }

    private Map<String, Integer> getFrequencies(){
        return getAllWords().stream()
                .collect( Collectors.groupingBy( Function.identity(), Collectors.summingInt(e -> 1) ));
    }
}