package ru.ifmo.vovk.stage5.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract public class AbstractNewsService implements NewsService{
    protected int limit;

    protected Set<String> forbiddenWords = new HashSet<>(){{
        {
            addAll(List.of("и", "у", "в", "не", "на", "я", "вот", "быть", "с", "что", "а", "по", "о", "за", "для",
                    "из-за", "из", "об", "от", "к", "-", "это", "его", "они", "мы", "там", "как",

                    "at", "after", "a", "on", "the", "be", "of", "and", "in", "into", "to", "have", "", "it", "for",
                    "you", "as", "do", "this", "are", "is", "us", "its", "new", "more", "an", "by", "says", "—"));
        }
    }};

    public List<String> getTopWords(){
        Map<String, Integer> map = cleanMap();

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(this.limit)
                .collect(Collectors.toList());
    }

    protected  List<String> getAllWords() {
        List<String> res = new ArrayList<>();
        SyndFeedInput input = new SyndFeedInput();
        String rss = getNewsUrl();
        try {
            SyndFeed feed = input.build(new XmlReader(new URL((rss))));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                res.addAll(List.of(entry.getTitle()
                        .replaceAll("\\p{Z}", "\s")
                        .replaceAll("[^\\s-\\wА-Яа-я%]", "")
                        .toLowerCase().split("\s")));
            }
        } catch (IOException | FeedException e) {
            System.err.println("failed to read rss feed [" + rss + "]");
        }
        return res;
    }

    protected   Map<String, Integer> getFrequencies(){
        return getAllWords().stream()
                .collect(Collectors.groupingBy( Function.identity(), Collectors.summingInt(e -> 1) ));
    }

    protected  Set<String> getSetOfRemovableWords(){
        return forbiddenWords;
    }

    protected   Map<String, Integer> cleanMap(){
        Map<String, Integer> mapFromNews = getFrequencies();
        Set<String> setOfForbiddenWords = getSetOfRemovableWords();
        mapFromNews.keySet().removeAll(setOfForbiddenWords);
        return mapFromNews;
    }
}
