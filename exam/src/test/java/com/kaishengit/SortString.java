package com.kaishengit;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/18
 */
public class SortString {


    @Test
    public void sortStringTest() {

        sortString2("abcd");

    }


    public void sortString(String str) {

        if(str.length() != 0) {

            String lastStr =  str.charAt(str.length() - 1) + "";
            System.out.print(lastStr);

            sortString(str.substring(0, str.lastIndexOf(lastStr)));

        }

    }

    public void sortString2(String str) {

        char[] chars = str.toCharArray();

        for(int i = chars.length - 1; i >= 0 ; i --) {

            System.out.print(chars[i]);

        }

    }


}
