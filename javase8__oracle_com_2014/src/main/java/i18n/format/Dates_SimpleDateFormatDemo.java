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

public class Dates_SimpleDateFormatDemo {

    static public void displayPattern(String pattern, Locale currentLocale) {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        Date today = new Date();
        String result = formatter.format(today);

        System.out.println("Locale: " + currentLocale + "   " + pattern + "   " + result);
    }

    static public void main(String[] args) {

        Locale[] locales = {
                new Locale("fr", "FR"),
                new Locale("de", "DE"),
                new Locale("en", "US")
        };

        for (int i = 0; i < locales.length; i++) {
            displayPattern("EEE d MMM yy", locales[i]);
        }
        System.out.println();

        String[] patterns = {
                "dd.MM.yy",
                "yyyy.MM.dd G 'at' hh:mm:ss z",
                "EEE, MMM d, ''yy",
                "h:mm a",
                "H:mm",
                "H:mm:ss:SSS",
                "K:mm a,z",
                "yyyy.MMMMM.dd GGG hh:mm aaa"
        };

        for (int k = 0; k < patterns.length; k++) {
            displayPattern(patterns[k], new Locale("en", "US"));
        }
        System.out.println();

    }

}
/*
Locale: fr_FR   EEE d MMM yy   jeu. 23 nov. 23
Locale: de_DE   EEE d MMM yy   Do 23 Nov 23
Locale: en_US   EEE d MMM yy   Thu 23 Nov 23

Locale: en_US   dd.MM.yy                       23.11.23
Locale: en_US   yyyy.MM.dd G 'at' hh:mm:ss z   2023.11.23 AD at 03:52:35 EET
Locale: en_US   EEE, MMM d, ''yy               Thu, Nov 23, '23
Locale: en_US   h:mm a                         3:52 PM
Locale: en_US   H:mm                           15:52
Locale: en_US   H:mm:ss:SSS                    15:52:35:073
Locale: en_US   K:mm a,z                       3:52 PM,EET
Locale: en_US   yyyy.MMMMM.dd GGG hh:mm aaa    2023.November.23 AD 03:52 PM
 */
