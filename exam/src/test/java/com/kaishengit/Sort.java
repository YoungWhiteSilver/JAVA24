package com.kaishengit;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/18
 */
public class Sort {

    @Test
    public void sortTest() {

        int[] nums = {5,2,1,3};
        sortNum2(nums);

    }

    public void sortNum(int[] number) {

        for(int i = 0; i < number.length; i ++) {

            int j = i + 1;
            int temp = 0;

            for(; j< number.length; j ++) {

                if(j < number.length && number[i] > number[j]) {

                    temp = number[j];
                    number[j] = number[i];
                    number[i] = temp;

                }
            }

        }

        for(int n: number) {
            System.out.println(n);
        }

    }

    public void sortNum2(int[] number) {

        for(int i = 0; i < number.length; i ++) {

            int j = i;
            int temp = 0;

            while(j > 0 && number[j -1] > number[j]) {

                temp = number[j];
                number[j] = number[j - 1];
                number[j -1] = temp;
                j --;
            }

        }
        for(int n: number) {
            System.out.println(n);
        }

    }


}
