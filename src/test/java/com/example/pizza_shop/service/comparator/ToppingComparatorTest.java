package com.example.pizza_shop.service.comparator;

import com.example.pizza_shop.model.Topping;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ToppingComparatorTest {

    private ToppingComparator comparator;
    private Date now;

    @Before
    public void setUp() throws Exception {
        comparator = new ToppingComparator();
        now = new Date();
    }

    @Test
    public void compareWhenLhsIsNull() throws Exception {
        int actual = comparator.compare(null, new Topping("topping", now));

        assertThat(actual).isEqualTo(-1);
    }

    @Test
    public void compareWhenRhsIsNull() throws Exception {
        int actual = comparator.compare(new Topping("topping", now), null);

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void compareForDifferentToppingNames() throws Exception {
        Topping veggie = new Topping("veggie", now);
        Topping meat = new Topping("meat", now);

        //act
        int actual = comparator.compare(veggie, meat);

        assertThat(actual).isGreaterThan(0);
    }

    @Test
    public void compareForSameToppingNamesWithDifferentDates() throws Exception {
        Date oneHourAgo = DateUtils.addHours(now, -1);
        Topping veggie1 = new Topping("veggie", oneHourAgo);
        Topping veggie2 = new Topping("veggie", now);

        //act
        int actual = comparator.compare(veggie1, veggie2);

        assertThat(actual).isLessThan(0);
    }

    @Test
    public void compareForSameToppingNamesWithSameDates() throws Exception {
        Topping veggie1 = new Topping("veggie", now);
        Topping veggie2 = new Topping("veggie", now);

        //act
        int actual = comparator.compare(veggie1, veggie2);

        assertThat(actual).isEqualTo(0);
    }
}