package com.github.danrog303.epubify.tests.utils;

import com.github.danrog303.epubify.utils.ListUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilsTest {
    @Test
    void enumerate_emptyListIteration_makeZeroIterations() {
        var list = new ArrayList<Integer>();
        int iterationsCounter = 0;

        for (var iteration : ListUtils.enumerate(list)) {
            iterationsCounter++;
        }

        Assertions.assertEquals(0, iterationsCounter);
    }

    @Test
    void enumerate_stringList_iterateCorrectlyOverTheList() {
        var list = List.of("aaa", "bbb", "ccc");
        int iterationCounter = 0;

        for (var iteration : ListUtils.enumerate(list)) {
            switch (iterationCounter) {
                case 0 -> {
                    Assertions.assertEquals(iteration.index, 0);
                    Assertions.assertEquals(iteration.item, "aaa");
                }
                case 1 -> {
                    Assertions.assertEquals(iteration.index, 1);
                    Assertions.assertEquals(iteration.item, "bbb");
                }
                case 2 -> {
                    Assertions.assertEquals(iteration.index, 2);
                    Assertions.assertEquals(iteration.item, "ccc");
                }
            }

            iterationCounter++;
        }

        Assertions.assertEquals(3, iterationCounter);
    }
}
