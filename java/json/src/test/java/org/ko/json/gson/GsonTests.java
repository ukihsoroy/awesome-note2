package org.ko.json.gson;

import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GsonTests {

    private static final Logger logger = LoggerFactory.getLogger(GsonTests.class);

    @Test
    public void gsonBasicApi () {
        // Serialization
        Gson gson = new Gson();

        String j1 = gson.toJson(1);            // ==> 1
        logger.info(j1);

        String j2 = gson.toJson("abcd");       // ==> "abcd"
        logger.info(j2);

        String j3 = gson.toJson(new Long(10)); // ==> 10
        logger.info(j3);

        int[] values = { 1 };
        String j4 = gson.toJson(values);       // ==> [1]
        logger.info(j4);

        // Deserialization
        int one = gson.fromJson("1", int.class);
        Integer one1 = gson.fromJson("1", Integer.class);
        Long one2 = gson.fromJson("1", Long.class);
        Boolean false1 = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);


    }
}
