package com.buke.fly.mem;

/**
 * Created by renzhe on 15/7/13.
 */
public class CCStringBuilderProxy {

    private static final int INIT_CAPACITY_SHARED = 32;
    private static final int INIT_CAPACITY = 96;

    /** StringBuilder instance*/
    private static SoftReferenceProxy<CCStringBuilder> mStringBuilder = null;

    /**
     * get DDStringBuilder
     */
    public static CCStringBuilder getDDStringBuilder(){
        makeSureSoftProxy();

        CCStringBuilder ret = mStringBuilder.get();

        if (null != ret && 0 == ret.length()) {
            return ret;
        } else {
            CCStringBuilder builder = new CCStringBuilder(INIT_CAPACITY_SHARED);
            builder.setShared(true);
            return builder;
        }
    }

    /**
     * init the soft reference proxy
     */
    private static void makeSureSoftProxy(){

        if(null == mStringBuilder)
            synchronized (CCStringBuilderProxy.class) {

                if (null == mStringBuilder) {
                    mStringBuilder = new SoftReferenceProxy<>();
                    mStringBuilder.setCreator(new SoftReferenceProxy.Creator<CCStringBuilder>() {
                        @Override
                        public CCStringBuilder create() {
                            return new CCStringBuilder(INIT_CAPACITY);
                        }

                        @Override
                        public CCStringBuilder validate(CCStringBuilder reference){

                            CCStringBuilder ret = null;

                            if(null != reference && reference.length() != 0){

                                reference.setShared(true);
                                ret = new CCStringBuilder(INIT_CAPACITY);
                            }

                            return ret;
                        }
                    });
                }
            }
    }

}
