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

    /**
     * 生成题目列表编号
     *
     * @param studentId 学校id
     * @param year      入学年份
     * @param number    序号
     * @return 题目列表编号
     */
    public static String genQuestionListIdentifier(Long studentId, Integer year, Integer number) {
        return "L" +
                addLeadingZero(studentId, 2) +
                addLeadingZero(year % 100, 2) +
                addLeadingZero(number, 3);
    }

    /**
     * 生成试卷编号
     *
     * @param difficulty         难度
     * @param schoolId          学校id
     * @param questionListNumber 问题列表序号
     * @param paperNumber        试卷序号
     * @return 试卷编号
     */
    public static String genPaperIdentifier(String difficulty, Long schoolId, Integer questionListNumber, Integer paperNumber) {
        return "P" +
                difficulty +
                "B" +
                addLeadingZero(schoolId, 2) +
                addLeadingZero(questionListNumber, 3) +
                addLeadingZero(paperNumber, 3);
    }

    /**
     * 生成班级编号
     */
    public static String genClazzIdentifier(Clazz clazz) {

        return "C" +
                addLeadingZero(clazz.getSchoolId(), 2) +
                addLeadingZero(clazz.getGrade() % 100, 2) +
                addLeadingZero(clazz.getClassNumber(), 2);
    }

    /**
     * 生成用户的编号
     *
     * @param schoolId    学校id
     * @param year        入学年份
     * @param clazzNumber 班级序号
     * @param number      个人序号
     */
    public static String genStudentIdentifier(Long schoolId, Integer year, Integer clazzNumber, Integer number) {

        return "S" +
                addLeadingZero(schoolId, 2) +
                addLeadingZero(year% 100, 2) +
                addLeadingZero(clazzNumber, 2) +
                addLeadingZero(number, 2);
    }

    /**
     * 生成教师编号
     */
    public static String genTeacherIdentifier(Long schoolId, Integer number) {

        return "T" +
                addLeadingZero(schoolId, 2) +
                addLeadingZero(number, 3);
    }

    /**
     * 加前缀0
     */
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
