package com.five.util;

import com.five.entity.Clazz;

import java.io.Serializable;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 17:17
 */
public class IdentifierGenerator {

    public static String genClazzIdentifier(Clazz clazz) {

        return "C" +
                addLeadingZero(clazz.getSchoolId(), 2) +
                addLeadingZero(clazz.getGrade(), 2) +
                addLeadingZero(clazz.getClassNumber(), 2);
    }

    public static String genTeacherIdentifier(Long schoolId, Integer number) {

        return "T" +
                addLeadingZero(schoolId, 2) +
                addLeadingZero(number, 3);
    }

    private static String addLeadingZero(Serializable num, Integer len) {
        String s = String.valueOf(num);
        int sl = s.length();
        if (sl >= len) return s;
        else {

            int x = len - sl;
            StringBuilder builder = new StringBuilder();
            while (x > 0) {
                builder.append("0");
                x--;
            }
            builder.append(s);
            return builder.toString();
        }
    }

}
