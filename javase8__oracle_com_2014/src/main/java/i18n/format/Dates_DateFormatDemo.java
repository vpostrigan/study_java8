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

public class Dates_DateFormatDemo {

    public static void displayDate(Locale currentLocale) {
        Date today = new Date();

        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
        String dateOut = dateFormatter.format(today);

        System.out.println(dateOut + "   " + currentLocale.toString());
    }

    public static void show(Locale currentLocale) {

        Date today = new Date();
        DateFormat formatter;

        int[] styles = {
                DateFormat.DEFAULT,
                DateFormat.SHORT,
                DateFormat.MEDIUM,
                DateFormat.LONG,
                DateFormat.FULL
        };

        System.out.println();
        System.out.println("Locale: " + currentLocale.toString());
        System.out.println();

        for (int k = 0; k < styles.length; k++) {
            formatter = DateFormat.getDateTimeInstance(styles[k], styles[k], currentLocale);
            String result1 = formatter.format(today);

            formatter = DateFormat.getDateInstance(styles[k], currentLocale);
            String result2 = formatter.format(today);

            formatter = DateFormat.getTimeInstance(styles[k], currentLocale);
            String result3 = formatter.format(today);
            System.out.println(styles[k] + ": [DateTimeInstance] " + result1 + "  ||  [DateInstance] " + result2 + "  ||  [TimeInstance] " + result3);
        }
    }

    public static void main(String[] args) {

        Locale[] locales = {
                new Locale("fr", "FR"),
                new Locale("de", "DE"),
                new Locale("en", "US")
        };

        for (int i = 0; i < locales.length; i++) {
            displayDate(locales[i]);
        }

        System.out.println("\nshow:");
        show(new Locale("en", "US"));
        show(new Locale("fr", "FR"));
    }

}
/*
22 nov. 2023   fr_FR
22.11.2023   de_DE
Nov 22, 2023   en_US

show:

Locale: en_US

          [DateTimeInstance]                                [DateInstance]                    [TimeInstance]
DEFAULT 2: Nov 22, 2023 10:37:22 PM                      ||  Nov 22, 2023                  ||  10:37:22 PM
SHORT   3: 11/22/23 10:37 PM                             ||  11/22/23                      ||  10:37 PM
MEDIUM  2: Nov 22, 2023 10:37:22 PM                      ||  Nov 22, 2023                  ||  10:37:22 PM
LONG    1: November 22, 2023 10:37:22 PM EET             ||  November 22, 2023             ||  10:37:22 PM EET
FULL    0: Wednesday, November 22, 2023 10:37:22 PM EET  ||  Wednesday, November 22, 2023  ||  10:37:22 PM EET

Locale: fr_FR

          [DateTimeInstance]                         [DateInstance]                 [TimeInstance]
DEFAULT 2: 22 nov. 2023 22:37:22                  ||  22 nov. 2023               ||  22:37:22
SHORT   3: 22/11/23 22:37                         ||  22/11/23                   ||  22:37
MEDIUM  2: 22 nov. 2023 22:37:22                  ||  22 nov. 2023               ||  22:37:22
LONG    1: 22 novembre 2023 22:37:22 EET          ||  22 novembre 2023           ||  22:37:22 EET
FULL    0: mercredi 22 novembre 2023 22 h 37 EET  ||  mercredi 22 novembre 2023  ||  22 h 37 EET
 */
