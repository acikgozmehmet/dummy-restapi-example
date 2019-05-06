package com.dummy.tests;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersDemo {

    @Test
    public void test(){
        assertThat(1,equalTo(1));
        String str1 = "Kunkka";
        String str2 = "Kunkka";

        assertThat(str1, is(str2));
        assertThat(str1, equalToIgnoringCase("kunkka"));
        assertThat(str1, is(not("aaaa")));

        assertThat(10, greaterThan(8));
        assertThat(str1,notNullValue());

        List<String> list = Arrays.asList("one", "two", "three");
        assertThat(list, hasSize(3));
        assertThat(list, hasItems("one"));
    }


}
