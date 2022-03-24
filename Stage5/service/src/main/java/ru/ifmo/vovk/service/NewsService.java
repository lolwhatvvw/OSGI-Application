package ru.ifmo.vovk.service;

import java.util.*;

public interface NewsService {

    String getNewsName();
    String getNewsUrl();
    List<String> getTopWords();

}