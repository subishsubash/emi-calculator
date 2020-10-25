package com.subash.emi.calculator.util;

import java.io.UnsupportedEncodingException;
import java.util.Currency;
import java.util.Locale;

/**
 * Class helps to keep generic util methods
 *
 * @author subash s
 */
public class GenericUtil {

    /**
     * Method will get the symbol for the currency inputted
     *
     * @param currency
     * @return
     */
    public static String getCurrencySymbol(String currency) {
        String symbol = new String();
        Locale locale = null;
        switch (currency) {
            case "USD":
            case "SGD":
                locale= Locale.US;
                break;
            case "EUR":
                locale= Locale.GERMANY;
                break;
            case "GBP":
                locale= Locale.UK;
                break;
            case "CNY":
            case "CHN":
            case "JPN":
                locale= Locale.JAPAN;
                break;
            case "INR":
                String string = "\u20B9";
                byte[] utf8 = new byte[0];
                try {
                    utf8 = string.getBytes("UTF-8");
                    symbol = new String(utf8, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    symbol = "INR";
                }
                break;
            default:
                symbol = currency;
        }
        if (symbol == null || symbol.isEmpty()) {
            Currency currencyObject = Currency.getInstance(locale);
            symbol = currencyObject.getSymbol(locale);
        }
        return symbol;
    }
}
