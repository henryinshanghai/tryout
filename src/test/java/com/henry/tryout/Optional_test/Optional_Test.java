package com.henry.tryout.Optional_test;

import com.henry.tryout.java_8_features.optional_06.optional_in_action_04.Properties;
import org.junit.Assert;
import org.junit.Test;

import static com.henry.tryout.java_8_features.optional_06.optional_in_action_04.using_optional_to_avoid_trivial_grammar_03.readDurationNotUsingOptional;

public class Optional_Test {

    @Test
    public void readDuration_should_bigger_than_zero() {
        Properties properties = new Properties();
        properties.init();

        Assert.assertEquals(5, readDurationNotUsingOptional(properties, "attrA"));
        Assert.assertEquals(0, readDurationNotUsingOptional(properties, "attrB"));
        Assert.assertEquals(0, readDurationNotUsingOptional(properties, "attrC"));
        Assert.assertEquals(0, readDurationNotUsingOptional(properties, "attrD"));
    }
}
