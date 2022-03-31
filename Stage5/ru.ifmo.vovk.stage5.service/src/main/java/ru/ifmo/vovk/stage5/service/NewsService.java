package ru.ifmo.vovk.stage5.service;

import java.util.List;

public interface NewsService {
    String getNewsName();

    String getNewsUrl();

    List<String> getTopWords();
}