package com.kaishengit.test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/22
 */
public class Example {


    String str = new String("good");

    char[ ] ch = { 'a' , 'b' , 'c' };

    public static void main(String args[]){
        Example ex = new Example();
        ex.change(ex.str,ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }

    public void change(String str,char ch[ ]){
        str = "test ok";
        ch[0] = 'g';
    }


}
