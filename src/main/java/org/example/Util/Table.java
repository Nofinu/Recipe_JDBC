package org.example.Util;

import org.example.model.BaseShowMethod;

import java.text.MessageFormat;
import java.util.List;

public class Table {
    public static void table (List<BaseShowMethod> elements, boolean withId){
        int modulo = elements.size()%4;

        for(int i =0; i<elements.size(); i+=4) {
            if (i + 3 < elements.size()) {
                System.out.println(MessageFormat.format("{0}  |  {1}   |  {2}   |  {3}  |", withId ? elements.get(i) : elements.get(i).toStringWithoutId(), withId ? elements.get(i +1) : elements.get(i + 1).toStringWithoutId(), withId ? elements.get(i + 2) : elements.get(i + 2).toStringWithoutId(), withId ? elements.get(i + 3) : elements.get(i + 3).toStringWithoutId()));
            } else {
                switch (modulo){
                    case 3:
                        System.out.println(MessageFormat.format("{0}  |  {1}   |  {2}  |", withId ? elements.get(i) : elements.get(i).toStringWithoutId(), withId ? elements.get(i + 1) : elements.get(i + 1).toStringWithoutId(), withId ? elements.get(i + 2) : elements.get(i + 2).toStringWithoutId()));
                        break;
                    case 2:
                        System.out.println(MessageFormat.format("{0}  |  {1}  |", withId ? elements.get(i) : elements.get(i).toStringWithoutId(), withId ? elements.get(i + 1) : elements.get(i + 1).toStringWithoutId()));
                        break;
                    case 1:
                        System.out.println(MessageFormat.format("{0}  |", withId ? elements.get(i) : elements.get(i).toStringWithoutId()));
                        break;
                }
            }
        }
    }
}
