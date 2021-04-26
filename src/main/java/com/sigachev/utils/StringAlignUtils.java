package com.sigachev.utils;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class StringAlignUtils extends Format {

    private static final long serialVersionUID = 1L;

    public enum Alignment {
        LEFT, CENTER, RIGHT, JUSTIFY, HARD
    }

    private Alignment currentAlignment;

   //max length for the line
    private int width;

    public StringAlignUtils(int width, Alignment align) {
        switch (align) {
            case LEFT:
            case CENTER:
            case RIGHT:
                this.currentAlignment = align;
                break;
            default:
                throw new IllegalArgumentException("invalid justification arg.");
        }
        if (width < 0) {
            throw new IllegalArgumentException("width must be positive.");
        }
        this.width = width;
    }

    public StringBuffer format(Object input, StringBuffer buffer, FieldPosition ignore) {
        String s = input.toString();
        List<String> strings = splitInputString(s);

        strings.forEach(st -> {

            //Get the spaces in the right place.
            switch (currentAlignment) {
                case LEFT:
                    buffer.append(st);
                    pad(buffer, width - st.length());
                    break;

                case RIGHT:
                    pad(buffer, width - st.length());
                    buffer.append(st);
                    break;

                case CENTER:
                    int toAdd = width - st.length();
                    pad(buffer, toAdd / 2);
                    buffer.append(st);
                    pad(buffer, toAdd - toAdd / 2);
                    break;
            }
            buffer.append("\n");

        });


        return buffer;
    }

    protected final void pad(StringBuffer sb, int numSpaces) {
        for (int i = 0; i < numSpaces; i++)
            sb.append(' ');
    }

    String format(String s) {
        return format(s, new StringBuffer(), null).toString();
    }


    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }


    private List<String> splitInputString(String str) {
        List<String> list = new ArrayList<>();
        if (str == null)
            return list;

        String[] arr = str.split(" ");

        String row = new String();
        for (String ss : arr) {
            //checking row length after appending new word
            if (row.length() + ss.length() + 1 >= width) {
                list.add(row);
                row = ss;
            } else {
                StringBuilder sb = new StringBuilder(row);
                //do not append space to first word in the row
                if (row.length()==0) {
                    row = sb.append(ss).toString();
                }
                else {
                    row = sb.append(" ").append(ss).toString();
                }
            }
        }

        return list;
    }

}
