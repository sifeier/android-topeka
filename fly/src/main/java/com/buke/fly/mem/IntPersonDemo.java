package com.buke.fly.mem;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author buke@taobao.com
 * @since 16/9/12
 */
public class IntPersonDemo {

    public static final int MALE = 0x01;

    public static final int FEMALE = 0x02;

    public static final String COLOR_GREEN = "green";

    public static final String COLOR_RED = "red";


    public void doSth() {
        Person p = new Person();

        p.setSex(MALE);

    }

    public void doString() {
        Person p = new Person();

        p.setColor(COLOR_GREEN);
    }

    class Person {

        @SEX
        private int sex;

        @COLOR
        private String color;

        public void setSex(@SEX int sex) {
            this.sex = sex;
        }

        @SEX
        public int getSex() {
            return sex;
        }

        public void setColor(@COLOR String color) {
            this.color = color;
        }

        @COLOR
        public String getColor() {
            return color;
        }

    }

    @IntDef({MALE, FEMALE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SEX {

    }

    @StringDef({COLOR_GREEN, COLOR_RED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface COLOR {

    }

}
