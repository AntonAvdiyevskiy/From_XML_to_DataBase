package com.comparators;

import com.model.Periodical;

import java.util.Comparator;

public class SortedByAmountOfPages implements Comparator<Periodical> {
    public int compare(Periodical o1, Periodical o2) {
        int pagesFirst = o1.getPages();
        int pagesSecond = o2.getPages();

        if(pagesFirst > pagesSecond) {
            return 1;
        }
        else if(pagesFirst < pagesSecond) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
