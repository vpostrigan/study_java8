/*
 * Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN  ` ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package i18n.format;

import java.util.*;
import java.text.*;

public class Numbers_NumberFormatDemo {

    static public void displayNumber(Locale currentLocale) {
        Integer quantity = new Integer(123456);
        Double amount = new Double(345987.246);

        NumberFormat numberFormatter = NumberFormat.getNumberInstance(currentLocale);
        String quantityOut = numberFormatter.format(quantity);
        String amountOut = numberFormatter.format(amount);
        System.out.println(currentLocale + "   " + quantityOut);
        System.out.println(currentLocale + "   " + amountOut);
    }

    static public void displayCurrency(Locale currentLocale) {
        Double currencyAmount = new Double(9876543.21);

        Currency currentCurrency = Currency.getInstance(currentLocale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        System.out.println(currentLocale.getDisplayName() + ", "
                + currentCurrency.getDisplayName() + ": "
                + currencyFormatter.format(currencyAmount));
    }

    static public void displayPercent(Locale currentLocale) {
        Double percent = new Double(0.75);

        NumberFormat percentFormatter = NumberFormat.getPercentInstance(currentLocale);
        String percentOut = percentFormatter.format(percent);
        System.out.println(percentOut + "   " + currentLocale.toString());
    }

    static public void main(String[] args) {
        List<Locale> locales = new ArrayList<>();
        locales.add(0, new Locale.Builder().setLanguage("fr").setRegion("FR").build());
        locales.add(1, new Locale.Builder().setLanguage("de").setRegion("DE").build());
        locales.add(2, new Locale.Builder().setLanguage("en").setRegion("US").build());
        locales.add(3, new Locale.Builder().setLanguage("uk").setRegion("UA").build());

        System.out.println("\ndisplayNumber:");
        for (int i = 0; i < locales.size(); i++) {
            displayNumber(locales.get(i));
        }

        System.out.println("\ndisplayCurrency:");
        for (int i = 0; i < locales.size(); i++) {
            displayCurrency(locales.get(i));
        }

        System.out.println("\ndisplayPercent:");
        for (int i = 0; i < locales.size(); i++) {
            displayPercent(locales.get(i));
        }
    }

}
/*
displayNumber:
fr_FR   123 456
fr_FR   345 987,246
de_DE   123.456
de_DE   345.987,246
en_US   123,456
en_US   345,987.246
uk_UA   123 456
uk_UA   345 987,246

displayCurrency:
French (France), Euro: 9 876 543,21 €
German (Germany), Euro: 9.876.543,21 €
English (United States), US Dollar: $9,876,543.21
Ukrainian (Ukraine), Ukrainian Hryvnia: 9 876 543,21 грн.

displayPercent:
75 %   fr_FR
75%   de_DE
75%   en_US
75%   uk_UA
 */
