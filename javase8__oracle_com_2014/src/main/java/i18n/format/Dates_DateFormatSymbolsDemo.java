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

public class Dates_DateFormatSymbolsDemo {

    static public void changeWeekDays() {

        DateFormatSymbols symbols;

        symbols = new DateFormatSymbols(new Locale("en", "US"));

        String[] defaultDays = symbols.getShortWeekdays();
        for (int i = 0; i < defaultDays.length; i++) {
            System.out.print(defaultDays[i] + "  ");
        }
        System.out.println();

        String[] capitalDays = {"", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        symbols.setShortWeekdays(capitalDays);

        String[] modifiedDays = symbols.getShortWeekdays();
        for (int i = 0; i < modifiedDays.length; i++) {
            System.out.print(modifiedDays[i] + "  ");
        }
        System.out.println();

        System.out.println();

        SimpleDateFormat formatter = new SimpleDateFormat("E", symbols);
        Date today = new Date();
        String result = formatter.format(today);
        System.out.println("Today's day of the week: " + result);
    }

    static public void main(String[] args) {
        changeWeekDays();
    }
}
