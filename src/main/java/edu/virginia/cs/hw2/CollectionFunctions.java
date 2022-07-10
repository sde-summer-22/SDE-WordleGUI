package edu.virginia.cs.hw2;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class CollectionFunctions {
    public static <E> E getRandomItemFromSet(Set<E> set) {
        int randomIndex = (int) (Math.random() * set.size());
        Iterator<E> iter = set.iterator();
        for (int i=0; i < randomIndex; i++) {
            iter.next();
        }
        return iter.next();
    }
}
