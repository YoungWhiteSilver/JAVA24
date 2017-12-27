package com.kaishengit;

import com.kaishengit.test.OverloadTest;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/20
 */
public class OverloadTestCase {

    @Test
    public void sayHiTest() {

        OverloadTest overloadTest = new OverloadTest();
        System.out.println(overloadTest.sayHi());
        System.out.println(overloadTest.sayHi(""));

        int a = 1;
        Integer b = 1;
        System.out.println(a == b);

    }

    public void testString() {

    }

}
