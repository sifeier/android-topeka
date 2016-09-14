/*
 * Project: DingTalk
 *
 * File Created at 2015-07-16
 *
 * Copyright 2015 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.buke.fly.mem;

/**
 * DDStringBuilder.<br/>
 * 为实现StringBulder共享，对系统StringBuilder进行改造.
 *
 * <p>注意事项(STC保证如下规则)：
 * 1 DDStringBuilder不能最为成员变量使用
 * 2 DDStringBuilder一定要调用{@link #toString()}方法，且最后调用的方法一定是{@link #toString()}.
 * 3 调用{@link #toString()}方法,DDStringBuilder里面的数据会被清空一次.
 * @author renzhe.zxx
 */
public class CCStringBuilder {
    private StringBuilder mStringBuilder;

    /** shared */
    private boolean mShared = false;


    /**
     * Constructs an instance with an initial capacity of {@code 16}.
     *
     */
    public CCStringBuilder() {
        mStringBuilder = new StringBuilder();
    }

    /**
     * Constructs an instance with the specified capacity.
     *
     * @param capacity
     *            the initial capacity to use.
     * @throws NegativeArraySizeException
     *             if the specified {@code capacity} is negative.
     */
    public CCStringBuilder(int capacity) {
        mStringBuilder = new StringBuilder(capacity);
    }

    /**
     * Constructs an instance that's initialized with the contents of the
     * specified {@code CharSequence}. The capacity of the new builder will be
     * the length of the {@code CharSequence} plus 16.
     *
     * @param seq
     *            the {@code CharSequence} to copy into the builder.
     * @throws NullPointerException
     *            if {@code seq} is {@code null}.
     */
    public CCStringBuilder(CharSequence seq) {
        mStringBuilder = new StringBuilder(seq);
    }

    /**
     * Constructs an instance that's initialized with the contents of the
     * specified {@code String}. The capacity of the new builder will be the
     * length of the {@code String} plus 16.
     *
     * @param str
     *            the {@code String} to copy into the builder.
     * @throws NullPointerException
     *            if {@code str} is {@code null}.
     */
    public CCStringBuilder(String str) {
        mStringBuilder = new StringBuilder(str);
    }

    /**
     * Appends the string representation of the specified {@code boolean} value.
     * The {@code boolean} value is converted to a String according to the rule
     * defined by {@link String#valueOf(boolean)}.
     *
     * @param b
     *            the {@code boolean} value to append.
     * @return this builder.
     * @see String#valueOf(boolean)
     */
    public CCStringBuilder append(boolean b) {
        mStringBuilder.append(b);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code char} value.
     * The {@code char} value is converted to a string according to the rule
     * defined by {@link String#valueOf(char)}.
     *
     * @param c
     *            the {@code char} value to append.
     * @return this builder.
     * @see String#valueOf(char)
     */
    public CCStringBuilder append(char c) {
        mStringBuilder.append(c);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code int} value. The
     * {@code int} value is converted to a string according to the rule defined
     * by {@link String#valueOf(int)}.
     *
     * @param i
     *            the {@code int} value to append.
     * @return this builder.
     * @see String#valueOf(int)
     */
    public CCStringBuilder append(int i) {
        mStringBuilder.append(i);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code long} value.
     * The {@code long} value is converted to a string according to the rule
     * defined by {@link String#valueOf(long)}.
     *
     * @param l
     *            the {@code long} value.
     * @return this builder.
     * @see String#valueOf(long)
     */
    public CCStringBuilder append(long l) {
        mStringBuilder.append(l);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code float} value.
     * The {@code float} value is converted to a string according to the rule
     * defined by {@link String#valueOf(float)}.
     *
     * @param f
     *            the {@code float} value to append.
     * @return this builder.
     * @see String#valueOf(float)
     */
    public CCStringBuilder append(float f) {
        mStringBuilder.append(f);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code double} value.
     * The {@code double} value is converted to a string according to the rule
     * defined by {@link String#valueOf(double)}.
     *
     * @param d
     *            the {@code double} value to append.
     * @return this builder.
     * @see String#valueOf(double)
     */
    public CCStringBuilder append(double d) {
        mStringBuilder.append(d);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code Object}.
     * The {@code Object} value is converted to a string according to the rule
     * defined by {@link String#valueOf(Object)}.
     *
     * @param obj
     *            the {@code Object} to append.
     * @return this builder.
     * @see String#valueOf(Object)
     */
    public CCStringBuilder append(Object obj) {
        mStringBuilder.append(obj);
        return this;
    }

    /**
     * Appends the contents of the specified string. If the string is {@code
     * null}, then the string {@code "null"} is appended.
     *
     * @param str
     *            the string to append.
     * @return this builder.
     */
    public CCStringBuilder append(String str) {
        mStringBuilder.append(str);
        return this;
    }

    /**
     * Appends the contents of the specified {@code StringBuffer}. If the
     * StringBuffer is {@code null}, then the string {@code "null"} is
     * appended.
     *
     * @param sb
     *            the {@code StringBuffer} to append.
     * @return this builder.
     */
    public CCStringBuilder append(StringBuffer sb) {
        mStringBuilder.append(sb);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code char[]}.
     * The {@code char[]} is converted to a string according to the rule
     * defined by {@link String#valueOf(char[])}.
     *
     * @param chars
     *            the {@code char[]} to append..
     * @return this builder.
     * @see String#valueOf(char[])
     */
    public CCStringBuilder append(char[] chars) {
        mStringBuilder.append(chars);
        return this;
    }

    /**
     * Appends the string representation of the specified subset of the {@code
     * char[]}. The {@code char[]} value is converted to a String according to
     * the rule defined by {@link String#valueOf(char[],int,int)}.
     *
     * @param str
     *            the {@code char[]} to append.
     * @param offset
     *            the inclusive offset index.
     * @param len
     *            the number of characters.
     * @return this builder.
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code offset} and {@code len} do not specify a valid
     *             subsequence.
     * @see String#valueOf(char[],int,int)
     */
    public CCStringBuilder append(char[] str, int offset, int len) {
        mStringBuilder.append(str, offset, len);
        return this;
    }

    /**
     * Appends the string representation of the specified {@code CharSequence}.
     * If the {@code CharSequence} is {@code null}, then the string {@code
     * "null"} is appended.
     *
     * @param csq
     *            the {@code CharSequence} to append.
     * @return this builder.
     */
    public CCStringBuilder append(CharSequence csq) {
        mStringBuilder.append(csq);
        return this;
    }

    /**
     * Appends the string representation of the specified subsequence of the
     * {@code CharSequence}. If the {@code CharSequence} is {@code null}, then
     * the string {@code "null"} is used to extract the subsequence from.
     *
     * @param csq
     *            the {@code CharSequence} to append.
     * @param start
     *            the beginning index.
     * @param end
     *            the ending index.
     * @return this builder.
     * @throws IndexOutOfBoundsException
     *             if {@code start} or {@code end} are negative, {@code start}
     *             is greater than {@code end} or {@code end} is greater than
     *             the length of {@code csq}.
     */
    public CCStringBuilder append(CharSequence csq, int start, int end) {
        mStringBuilder.append(csq, start, end);
        return this;
    }

    /**
     * Appends the encoded Unicode code point. The code point is converted to a
     * {@code char[]} as defined by {@link Character#toChars(int)}.
     *
     * @param codePoint
     *            the Unicode code point to encode and append.
     * @return this builder.
     * @see Character#toChars(int)
     */
    public CCStringBuilder appendCodePoint(int codePoint) {
        mStringBuilder.appendCodePoint(codePoint);
        return this;
    }

    /**
     * Deletes a sequence of characters specified by {@code start} and {@code
     * end}. Shifts any remaining characters to the left.
     *
     * @param start
     *            the inclusive start index.
     * @param end
     *            the exclusive end index.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code start} is less than zero, greater than the current
     *             length or greater than {@code end}.
     */
    public CCStringBuilder delete(int start, int end) {
        mStringBuilder.delete(start, end);
        return this;
    }

    /**
     * Deletes the character at the specified index. shifts any remaining
     * characters to the left.
     *
     * @param index
     *            the index of the character to delete.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code index} is less than zero or is greater than or
     *             equal to the current length.
     */
    public CCStringBuilder deleteCharAt(int index) {
        mStringBuilder.deleteCharAt(index);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code boolean} value
     * at the specified {@code offset}. The {@code boolean} value is converted
     * to a string according to the rule defined by
     * {@link String#valueOf(boolean)}.
     *
     * @param offset
     *            the index to insert at.
     * @param b
     *            the {@code boolean} value to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length}.
     * @see String#valueOf(boolean)
     */
    public CCStringBuilder insert(int offset, boolean b) {
        mStringBuilder.insert(offset, b);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code char} value at
     * the specified {@code offset}. The {@code char} value is converted to a
     * string according to the rule defined by {@link String#valueOf(char)}.
     *
     * @param offset
     *            the index to insert at.
     * @param c
     *            the {@code char} value to insert.
     * @return this builder.
     * @throws IndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     * @see String#valueOf(char)
     */
    public CCStringBuilder insert(int offset, char c) {
        mStringBuilder.insert(offset, c);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code int} value at
     * the specified {@code offset}. The {@code int} value is converted to a
     * String according to the rule defined by {@link String#valueOf(int)}.
     *
     * @param offset
     *            the index to insert at.
     * @param i
     *            the {@code int} value to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     * @see String#valueOf(int)
     */
    public CCStringBuilder insert(int offset, int i) {
        mStringBuilder.insert(offset, i);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code long} value at
     * the specified {@code offset}. The {@code long} value is converted to a
     * String according to the rule defined by {@link String#valueOf(long)}.
     *
     * @param offset
     *            the index to insert at.
     * @param l
     *            the {@code long} value to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {code length()}.
     * @see String#valueOf(long)
     */
    public CCStringBuilder insert(int offset, long l) {
        mStringBuilder.insert(offset, l);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code float} value at
     * the specified {@code offset}. The {@code float} value is converted to a
     * string according to the rule defined by {@link String#valueOf(float)}.
     *
     * @param offset
     *            the index to insert at.
     * @param f
     *            the {@code float} value to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     * @see String#valueOf(float)
     */
    public CCStringBuilder insert(int offset, float f) {
        mStringBuilder.insert(offset, f);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code double} value
     * at the specified {@code offset}. The {@code double} value is converted
     * to a String according to the rule defined by
     * {@link String#valueOf(double)}.
     *
     * @param offset
     *            the index to insert at.
     * @param d
     *            the {@code double} value to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     * @see String#valueOf(double)
     */
    public CCStringBuilder insert(int offset, double d) {
        mStringBuilder.insert(offset, d);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code Object} at the
     * specified {@code offset}. The {@code Object} value is converted to a
     * String according to the rule defined by {@link String#valueOf(Object)}.
     *
     * @param offset
     *            the index to insert at.
     * @param obj
     *            the {@code Object} to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     * @see String#valueOf(Object)
     */
    public CCStringBuilder insert(int offset, Object obj) {
        mStringBuilder.insert(offset, obj);
        return this;
    }

    /**
     * Inserts the specified string at the specified {@code offset}. If the
     * specified string is null, then the String {@code "null"} is inserted.
     *
     * @param offset
     *            the index to insert at.
     * @param str
     *            the {@code String} to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     */
    public CCStringBuilder insert(int offset, String str) {
        mStringBuilder.insert(offset, str);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code char[]} at the
     * specified {@code offset}. The {@code char[]} value is converted to a
     * String according to the rule defined by {@link String#valueOf(char[])}.
     *
     * @param offset
     *            the index to insert at.
     * @param ch
     *            the {@code char[]} to insert.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     * @see String#valueOf(char[])
     */
    public CCStringBuilder insert(int offset, char[] ch) {
        mStringBuilder.insert(offset, ch);
        return this;
    }

    /**
     * Inserts the string representation of the specified subsequence of the
     * {@code char[]} at the specified {@code offset}. The {@code char[]} value
     * is converted to a String according to the rule defined by
     * {@link String#valueOf(char[],int,int)}.
     *
     * @param offset
     *            the index to insert at.
     * @param str
     *            the {@code char[]} to insert.
     * @param strOffset
     *            the inclusive index.
     * @param strLen
     *            the number of characters.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}, or {@code strOffset} and {@code strLen} do
     *             not specify a valid subsequence.
     * @see String#valueOf(char[],int,int)
     */
    public CCStringBuilder insert(int offset, char[] str, int strOffset,
            int strLen) {
        mStringBuilder.insert(offset, str, strOffset, strLen);
        return this;
    }

    /**
     * Inserts the string representation of the specified {@code CharSequence}
     * at the specified {@code offset}. The {@code CharSequence} is converted
     * to a String as defined by {@link CharSequence#toString()}. If {@code s}
     * is {@code null}, then the String {@code "null"} is inserted.
     *
     * @param offset
     *            the index to insert at.
     * @param s
     *            the {@code CharSequence} to insert.
     * @return this builder.
     * @throws IndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}.
     * @see CharSequence#toString()
     */
    public CCStringBuilder insert(int offset, CharSequence s) {
        mStringBuilder.insert(offset, s);
        return this;
    }

    /**
     * Inserts the string representation of the specified subsequence of the
     * {@code CharSequence} at the specified {@code offset}. The {@code
     * CharSequence} is converted to a String as defined by
     * {@link CharSequence#subSequence(int, int)}. If the {@code CharSequence}
     * is {@code null}, then the string {@code "null"} is used to determine the
     * subsequence.
     *
     * @param offset
     *            the index to insert at.
     * @param s
     *            the {@code CharSequence} to insert.
     * @param start
     *            the start of the subsequence of the character sequence.
     * @param end
     *            the end of the subsequence of the character sequence.
     * @return this builder.
     * @throws IndexOutOfBoundsException
     *             if {@code offset} is negative or greater than the current
     *             {@code length()}, or {@code start} and {@code end} do not
     *             specify a valid subsequence.
     * @see CharSequence#subSequence(int, int)
     */
    public CCStringBuilder insert(int offset, CharSequence s, int start, int end) {
        mStringBuilder.insert(offset, s, start, end);
        return this;
    }

    /**
     * Replaces the specified subsequence in this builder with the specified
     * string.
     *
     * @param start
     *            the inclusive begin index.
     * @param end
     *            the exclusive end index.
     * @param string
     *            the replacement string.
     * @return this builder.
     * @throws StringIndexOutOfBoundsException
     *             if {@code start} is negative, greater than the current
     *             {@code length()} or greater than {@code end}.
     * @throws NullPointerException
     *            if {@code str} is {@code null}.
     */
    public CCStringBuilder replace(int start, int end, String string) {
        mStringBuilder.replace(start, end, string);
        return this;
    }

    /**
     * Reverses the order of characters in this builder.
     *
     * @return this buffer.
     */
    public CCStringBuilder reverse() {
        mStringBuilder.reverse();
        return this;
    }

    /**
     * Returns the contents of this builder.
     *
     * @return the string representation of the data in this builder.
     */
    public String toString() {

        if(!mShared) {

            String resultStr = "";
            final int len = mStringBuilder.length();

            if (len > 0) {
                resultStr = mStringBuilder.substring(0, mStringBuilder.length());
                mStringBuilder.setLength(0);
            }

            return resultStr;
        }else{
            return mStringBuilder.toString();
        }
    }

    public int  length() {
        return mStringBuilder.length();
    }

    public char charAt(int index){
        return mStringBuilder.charAt(index);
    }

    public void setLength(int length) {
        mStringBuilder.setLength(length);
    }

    /**
     * Returns the String value of the subsequence from the {@code start} index
     * to the current end.
     *
     * @param start
     *            the inclusive start index to begin the subsequence.
     * @return a String containing the subsequence.
     * @throws StringIndexOutOfBoundsException
     *             if {@code start} is negative or greater than the current
     *             {@link #length()}.
     */
    public String substring(int start) {
        return mStringBuilder.substring(start);
    }

    /**
     * Returns the String value of the subsequence from the {@code start} index
     * to the {@code end} index.
     *
     * @param start
     *            the inclusive start index to begin the subsequence.
     * @param end
     *            the exclusive end index to end the subsequence.
     * @return a String containing the subsequence.
     * @throws StringIndexOutOfBoundsException
     *             if {@code start} is negative, greater than {@code end} or if
     *             {@code end} is greater than the current {@link #length()}.
     */
    public String substring(int start, int end) {
        return mStringBuilder.substring(start, end);
    }

    void setShared(boolean shared){
        mShared = shared;
    }
}
