/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package i18n.format;

import java.util.*;
import java.text.*;

public class Numbers_DecimalFormatDemo {

    static public void customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(value + "  " + pattern + "  " + output);
    }

    static public void localizedFormat(String pattern, double value, Locale loc) {
        NumberFormat nf = NumberFormat.getNumberInstance(loc);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern(pattern);
        String output = df.format(value);
        System.out.println(pattern + "  " + output + "  " + loc.toString());
    }

    static public void main(String[] args) {

        customFormat("###,###.###", 123456.789);
        customFormat("###.##", 123456.789);
        customFormat("000000.000", 123.78);
        customFormat("$###,###.###", 12345.67);
        customFormat("\u00a5###,###.###", 12345.67);

        // [Altering the Formatting Symbols]
        Locale currentLocale = new Locale("en", "US");

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(currentLocale);
        unusualSymbols.setDecimalSeparator('|');
        unusualSymbols.setGroupingSeparator('^');
        String strange = "#,##0.###";
        DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
        weirdFormatter.setGroupingSize(4);
        String bizarre = weirdFormatter.format(12345.678);
        System.out.println("\nbizarre:");
        System.out.println(bizarre);

        // The preceding example created a DecimalFormat object for the default Locale.
        // If you want a DecimalFormat object for a non-default Locale
        System.out.println("\nlocalizedFormat:");
        Locale[] locales = {
                new Locale("en", "US"),
                new Locale("de", "DE"),
                new Locale("fr", "FR")
        };
        for (int i = 0; i < locales.length; i++) {
            localizedFormat("###,###.###", 123456.789, locales[i]);
        }
    }

}
/*
123456.789  ###,###.###  123,456.789
123456.789  ###.##  123456.79
123.78  000000.000  000123.780
12345.67  $###,###.###  $12,345.67
12345.67  ¥###,###.###  ¥12,345.67

bizarre:
1^2345|678

localizedFormat:
###,###.###  123,456.789  en_US
###,###.###  123.456,789  de_DE
###,###.###  123 456,789  fr_FR
 */
