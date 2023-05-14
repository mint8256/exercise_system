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

    /**
     * 生成用户的编号
     *
     * @param schoolId    学校id
     * @param year        入学年份（当前年份 - 年级）
     * @param clazzNumber 班级序号
     * @param number      个人序号
     */
    public static String genStudentIdentifier(Long schoolId, Integer year, Integer clazzNumber, Integer number) {

        return "S" +
                addLeadingZero(schoolId, 2) +
                addLeadingZero(year, 2) +
                addLeadingZero(clazzNumber, 2) +
                addLeadingZero(number, 2);
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
