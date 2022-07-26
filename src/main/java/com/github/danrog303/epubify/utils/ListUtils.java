package com.github.danrog303.epubify.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Utility class for enumerating over a list.
 * Source: https://stackoverflow.com/a/34804751
 */
public class ListUtils {

    public static class EnumeratedItem<T> {
        public T item;
        public int index;

        private EnumeratedItem(T item, int index) {
            this.item = item;
            this.index = index;
        }
    }

    private static class ListEnumerator<T> implements Iterable<EnumeratedItem<T>> {
        private final Iterable<T> target;
        private final int start;

        public ListEnumerator(Iterable<T> target, int start) {
            this.target = target;
            this.start = start;
        }

        @Override
        public @NotNull Iterator<EnumeratedItem<T>> iterator() {
            final Iterator<T> targetIterator = target.iterator();
            return new Iterator<EnumeratedItem<T>>() {

                int index = start;

                @Override
                public boolean hasNext() {
                    return targetIterator.hasNext();
                }

                @Override
                public EnumeratedItem<T> next() {
                    EnumeratedItem<T> nextIndexedItem = new EnumeratedItem<T>(targetIterator.next(), index);
                    index++;
                    return nextIndexedItem;
                }

            };
        }

    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable, int start) {
        return new ListEnumerator<T>(iterable, start);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable) {
        return enumerate(iterable, 0);
    }
}
