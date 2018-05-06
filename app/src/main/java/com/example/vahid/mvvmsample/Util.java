
package com.example.vahid.mvvmsample;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Util {

    public static String formatPrice(String price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(symbols);
        df.setGroupingSize(3);

        return df.format(Long.parseLong(price));
    }


}
