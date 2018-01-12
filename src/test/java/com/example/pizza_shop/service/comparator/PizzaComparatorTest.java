package com.example.pizza_shop.service.comparator;

import com.example.pizza_shop.model.Pizza;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class PizzaComparatorTest {

    private PizzaComparator comparator;
    private Date now;

    @Before
    public void setUp() throws Exception {
        comparator = new PizzaComparator();
        now = new Date();
    }

    @Test
    public void compareWhenLhsIsNull() throws Exception {
        final int actual = comparator.compare(null, new Pizza("pizza", now));

        assertThat(actual).isEqualTo(-1);
    }

    @Test
    public void compareWhenRhsIsNull() throws Exception {
        final int actual = comparator.compare(new Pizza("pizza", now), null);

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void compareWhenLhsNameIsNull() throws Exception {
        final int actual = comparator.compare(new Pizza(null, now), new Pizza("pizza", now));

        assertThat(actual).isEqualTo(-1);
    }

    @Test
    public void compareWhenRhsNameIsNull() throws Exception {
        final int actual = comparator.compare(new Pizza("pizza", now), new Pizza(null, now));

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void compareWhenLhsDateIsNull() throws Exception {
        final int actual = comparator.compare(new Pizza("pizza", null), new Pizza("pizza", now));

        assertThat(actual).isEqualTo(-1);
    }

    @Test
    public void compareWhenRhsDateIsNull() throws Exception {
        final int actual = comparator.compare(new Pizza("pizza", now), new Pizza("pizza", null));

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void compareForDifferentNames() throws Exception {
        final Pizza veggie = new Pizza("veggie", now);
        final Pizza meat = new Pizza("meat", now);

        //act
        final int actual = comparator.compare(veggie, meat);

        assertThat(actual).isGreaterThan(0);
    }

    @Test
    public void compareForSameNamesWithDifferentDates() throws Exception {
        final Date oneHourAgo = DateUtils.addHours(now, -1);
        final Pizza veggie1 = new Pizza("veggie", oneHourAgo);
        final Pizza veggie2 = new Pizza("veggie", now);

        //act
        final int actual = comparator.compare(veggie1, veggie2);

        assertThat(actual).isLessThan(0);
    }

    @Test
    public void compareForSameNamesWithSameDates() throws Exception {
        final Pizza veggie1 = new Pizza("veggie", now);
        final Pizza veggie2 = new Pizza("veggie", now);

        //act
        final int actual = comparator.compare(veggie1, veggie2);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void comparatorIsCaseInsensitive() throws Exception {
        final Pizza veggie = new Pizza("Veggie", now);
        final Pizza meat = new Pizza("meat", now);

        //act
        final int actual = comparator.compare(veggie, meat);

        assertThat(actual).isGreaterThan(0);
    }
}