package com.buke.fly.mem;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author buke
 * @since
 */
public class CalendarProxy {

    /** calendar instance */
    private static SoftReferenceProxy<Calendar> mCalendar = null;

    /**
     * get calendar
     */
    public static Calendar getCalendar(){
        makeSureSoftProxy();
        return mCalendar.get();
    }

    /**
     * init the soft reference proxy
     */
    private static void makeSureSoftProxy(){

        if(null == mCalendar){

            synchronized (CalendarProxy.class){

                if(null == mCalendar){
                    mCalendar = new SoftReferenceProxy<Calendar>();
                    mCalendar.setCreator(new SoftReferenceProxy.Creator<Calendar>() {
                        @Override
                        public Calendar create() {
                            return Calendar.getInstance(Locale.getDefault());
                        }

                        @Override
                        public Calendar validate(Calendar reference) {
                            return null;
                        }
                    });
                }
            }
        }
    }
}
