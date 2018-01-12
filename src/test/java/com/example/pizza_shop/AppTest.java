package com.example.pizza_shop;

import com.example.pizza_shop.service.OrderService;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    public static final String INPUT_FILE = "src/test/resources/sample_data_unnordered.txt";
    public static final String OUTPUT_FILE = "target/app.txt";

    @Before
    public void setUp() throws Exception {
        System.setProperty("orderInputFile", INPUT_FILE);
        System.setProperty("orderOutputFile", OUTPUT_FILE);
        FileUtils.forceMkdirParent(new File(OUTPUT_FILE));
    }

    @After
    public void tearDown() throws Exception {
        System.clearProperty("orderInputFile");
        System.clearProperty("orderOutputFile");
    }

    @Test
    public void main() throws Exception {
        assertThat(new File(OUTPUT_FILE)).doesNotExist();

        //act
        App.main();

        assertThat(new File(OUTPUT_FILE)).exists();
    }

}