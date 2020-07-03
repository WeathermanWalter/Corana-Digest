package com.company;

import java.util.Map;

public class Area {
    static String name;                             //this is the name taken under "Province_State"
    static int cases;                               //this is the cases taken under "Confirmed"
    static Map<String, Integer> historicValues;     //a record of previose cases, this will give it a delta, String value will be "MM-DD-YYYY"

    Area(String name) {
        Area.name = name;
    }

    static void assignCases(int i) {
        cases = i;
    }

    public static String getName() {
        return name;
    }

    public static int getCases() {
        return cases;
    }


}
