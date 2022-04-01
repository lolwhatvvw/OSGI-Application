
package ru.ifmo.vovk.stage5.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AbstractNewsServiceTest{
    @Test
    public void getAllWordsTest(){
        AbstractNewsService absCls = Mockito.mock(
                AbstractNewsService.class,
                Mockito.CALLS_REAL_METHODS);
        Mockito.when(absCls.getNewsUrl()).thenReturn("https://yourdailygerman.com/feed/");

        List<String> test = new ArrayList<>();
        test.add("halbierung");
        test.add("landen");
        test.add("grob");
        test.add("hinauf");


        assertEquals(test, absCls.getAllWords());
    }
    @Test
    public void getFrequenciesTest(){
        AbstractNewsService absCls = Mockito.mock(
                AbstractNewsService.class,
                Mockito.CALLS_REAL_METHODS);
        Mockito.when(absCls.getNewsUrl()).thenReturn("https://yourdailygerman.com/feed/");

        Map<String, Integer> test = new HashMap<>();
        test.put("halbierung", 1);
        test.put("landen", 1);
        test.put("grob", 1);
        test.put("hinauf", 1);

        assertEquals(test, absCls.getFrequencies());
    }

    @Test
    public void cleanMapTest(){
        AbstractNewsService absCls = Mockito.mock(
                AbstractNewsService.class,
                Mockito.CALLS_REAL_METHODS);
        Mockito.when(absCls.getNewsUrl()).thenReturn("https://yourdailygerman.com/feed/");
        Mockito.when(absCls.getSetOfRemovableWords())
                .thenReturn(new HashSet<>(){{
                    add("halbierung");
                    add("landen");
                }}
                );

        Map<String, Integer> test = new HashMap<>();
        test.put("grob", 1);
        test.put("hinauf", 1);

        assertEquals(test, absCls.cleanMap());
    }
    @Test
    public void getTopWordsTest(){
        AbstractNewsService absCls = Mockito.mock(
                AbstractNewsService.class,
                Mockito.CALLS_REAL_METHODS);
        Mockito.when(absCls.getNewsUrl()).thenReturn("https://yourdailygerman.com/feed/");
        Mockito.when(absCls.getSetOfRemovableWords()).thenReturn(Collections.emptySet());


        List<String> test = new ArrayList<>();
        test.add("halbierung");
        test.add("landen");

        absCls.limit = 2;
        assertEquals(test, absCls.getTopWords());
    }
}

