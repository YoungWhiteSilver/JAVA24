package com.kaishengit.test;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/20
 */
public class OverloadTest {


    public String sayHi() {

        return "hi";

    }

    public int sayHi(String hi) {
        return 1;
    }

}
