package ru.job4j.simpleset;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**.
 * Class SimpleLinkedSetTest tests SimpleLinkedSet class.
 * @author Mikhail Vlasov
 * @since 03.29.2018
 * @version 1
 */
public class SimpleLinkedSetTest {
    /**.
     * Tests add().
     */
    @Test
    public void whenCreateContainerShouldReturnTheSameType() {
        SimpleLinkedSet<String> simpleLinkedSet = new SimpleLinkedSet<>();

        simpleLinkedSet.add("test1");

        assertThat(simpleLinkedSet.iterator().next(), is("test1"));
    }

    /**.
     * Tests thad hasNext() and next() don't affect each other. And check that there is no duplicates.
     */
    @Test
    public void whenTestHasNextAndNextShouldReturnExpectedValue() {
        SimpleLinkedSet<String> simpleLinkedSet = new SimpleLinkedSet<>();

        for (int i = 0; i < 5; i++) {
            simpleLinkedSet.add(String.format("test%d", i));
            //Duplicates.
            simpleLinkedSet.add(String.format("test%d", i));
        }
        Iterator<String> iterator = simpleLinkedSet.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test0"));
        assertThat(iterator.next(), is("test1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test2"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test3"));
        assertThat(iterator.next(), is("test4"));
        assertThat(iterator.hasNext(), is(false));
    }

    /**.
     * Tests NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenEndOfListAndGetNextShouldThrowException() {
        SimpleLinkedSet<String> simpleLinkedSet = new SimpleLinkedSet<>();

        for (int i = 0; i < 5; i++) {
            simpleLinkedSet.add(String.format("test%d", i));
        }
        Iterator<String> iterator = simpleLinkedSet.iterator();

        assertThat(iterator.next(), is("test0"));
        assertThat(iterator.next(), is("test1"));
        assertThat(iterator.next(), is("test2"));
        assertThat(iterator.next(), is("test3"));
        assertThat(iterator.next(), is("test4"));
        iterator.next();
    }
}
