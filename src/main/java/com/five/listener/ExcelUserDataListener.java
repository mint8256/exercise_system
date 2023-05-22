package com.five.listener;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.five.Bo.ExcelUser;
import com.five.entity.Clazz;
import com.five.entity.User;
import com.five.entity.UserClazz;
import com.five.enums.RoleEnum;
import com.five.enums.SexEnum;
import com.five.service.ClazzService;
import com.five.service.UserClazzService;
import com.five.service.UserService;
import com.five.util.AuthUserContext;
import com.five.util.IdentifierGenerator;
import com.five.util.PasswordManager;
import com.five.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/14 15:21
 */
@Slf4j
public class ExcelUserDataListener implements ReadListener<ExcelUser> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private final UserService userService;
    /**
     * 缓存的数据
     */
    private List<ExcelUser> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private final String clazzName;

    public ExcelUserDataListener(String clazzName, UserService userService) {
        this.clazzName = clazzName;
        this.userService = userService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param excelUser 解析的一行数据
     */
    @Override
    public void invoke(ExcelUser excelUser, AnalysisContext analysisContext) {
        String s = JSONUtil.toJsonStr(excelUser);
        log.info("解析到一条数据:{}", s);
        cachedDataList.add(excelUser);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {

        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        System.out.println(cachedDataList);

        Long userId = AuthUserContext.get().getUserId();

        User user = userService.getById(userId);

        Long schoolId = user.getSchoolId();

        // 查询班级信息
        Clazz clazz = SpringContextUtil.getBean(ClazzService.class).getClazzByName(clazzName);

        Long clazzId = clazz.getClazzId();

        AtomicReference<Integer> nextStuNumber = new AtomicReference<>(userService.nextStuNumber(clazzId));

        List<User> users = cachedDataList.stream().map((e) -> {
            User u = SpringContextUtil.getBean(MapperFacade.class).map(e, User.class);

            Integer sex = SexEnum.UNKNOWN.value();

            if (e.getSexDesc().trim().equals(SexEnum.WOMEN.desc())) {
                sex = SexEnum.WOMEN.value();
            } else if (e.getSexDesc().trim().equals(SexEnum.MAN.desc())) {
                sex = SexEnum.MAN.value();
            }

            u.setPassword(PasswordManager.encryption("123456"));
            u.setRole(RoleEnum.STUDENT.value());
            u.setSchoolId(schoolId);
            u.setSex(sex);
            u.setSignTime(LocalDateTime.now());

            String studentIdentifier = IdentifierGenerator
                    .genStudentIdentifier(schoolId, clazz.getGrade() % 100, clazz.getClassNumber(), nextStuNumber.get());

            u.setUserIdentifier(studentIdentifier);
            // 将编号+1
            nextStuNumber.getAndSet(nextStuNumber.get() + 1);

            return u;
        }).collect(Collectors.toList());

        // 批量导入学生信息
        userService.insertBatch(users);

        // 插入其学生-班级关联信息
        users.forEach((u) -> {
            UserClazz userClazz = new UserClazz();
            userClazz.setUserId(u.getUserId());
            userClazz.setClazzId(clazzId);
            SpringContextUtil.getBean(UserClazzService.class).save(userClazz);
        });

        log.info("存储数据库成功！");
    }
}
