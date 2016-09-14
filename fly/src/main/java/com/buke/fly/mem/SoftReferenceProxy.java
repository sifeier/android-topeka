package com.buke.fly.mem;

import java.lang.ref.SoftReference;

/**
 * @author buke 
 * @since 16/8/18
 */
public class SoftReferenceProxy<T>{

    /**
     * the creator
     */
    public interface Creator<T>{

        /**
         * creator
         */
        T create();

        /**
         * validate
         */
        T validate(T reference);
    }

    /** the soft reference, thread local variable.*/
    private ThreadLocal<SoftReference<T>> mTLReference = new ThreadLocal<SoftReference<T>>();

    /** the creator */
    private Creator<T> mCreator = null;

    /**
     * set the instance
     */
    public void setCreator(Creator creator){
        mCreator = creator;
    }

    /**
     * get the instance
     */
    public T get(){

        T ret = null;

        SoftReference<T> reference = mTLReference.get();

        if(null != reference){
            ret = reference.get();
        }

        /** if the ret is not null, check if it is still used by someone, just change one */
        if(null != ret){

            if(null != mCreator){

                final T check = mCreator.validate(ret);
                if(null != check){
                    mTLReference.remove();
                    mTLReference.set(new SoftReference<T>(check));
                    ret = check;
                }
            }

        }else{

            ret = create();
            if(null != ret){
                reference = new SoftReference<T>(ret);
                mTLReference.set(reference);
            }
        }

        return ret;
    }

    /**
     * create instance
     */
    private T create(){

        T ret = null;

        if(null != mCreator){
            ret = mCreator.create();
        }

        return ret;
    }
}
