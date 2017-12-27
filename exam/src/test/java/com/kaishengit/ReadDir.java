package com.kaishengit;

import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/18
 */
public class ReadDir {


    @Test
    public void readDirTest() {

        File file = new File("G:/test");
        readDir(file);

    }

    public void readDir(File file) {

        if(!file.isFile()) {

            File[] fileName = file.listFiles();

            for(File n: fileName) {

                readDir(n);

            }

        } else {

            System.out.println(file.getAbsolutePath());

        }
    }

}
