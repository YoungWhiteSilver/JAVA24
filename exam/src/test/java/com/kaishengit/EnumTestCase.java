package com.kaishengit;

import com.kaishengit.test.EnumTest;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/19
 */
public class EnumTestCase {

    @Test
    public void EnumTest() {

        for(EnumTest e: EnumTest.values()) {
            System.out.println(e.toString());
        }

        int[]  nums = new int[10];
        System.out.println(nums.length);
        
    }

}
