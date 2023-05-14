package com.five.controller;

import cn.hutool.core.util.StrUtil;
import com.five.aop.annotation.AuthVerify;
import com.five.entity.Clazz;
import com.five.enums.RoleEnum;
import com.five.service.ClazzService;
import com.five.vo.ClazzVo;
import com.five.vo.R;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 16:09
 */
@RestController
@RequestMapping("clazz")
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    @Operation(summary = "获取全部班级列表")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @GetMapping("list")
    public R<List<ClazzVo>> getClazzList() {

        List<ClazzVo> clazzVoList = clazzService.getClazzVoList();

        return R.success(clazzVoList);
    }

    @Operation(summary = "添加班级")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping("create")
    public R<Void> createClazz(@RequestBody Clazz clazz) {

        boolean checkIsExist = clazzService.checkIsExist(clazz.getClazzName());

        if (checkIsExist) {
            return R.fail("班级名重复，请重试");
        }

        clazzService.createClazz(clazz);

        return R.success("创建成功");
    }

    @Operation(summary = "更新班级信息")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping("update")
    public R<Void> update(@RequestBody Clazz clazz) {

        boolean checkIsExist = clazzService.checkIsExist(clazz.getClazzName());

        if (checkIsExist) {
            return R.fail("班级名重复，请重试");
        }

        // 对名字进行trim处理，增强健壮性
        if (StrUtil.isNotBlank(clazz.getClazzName())) {
            clazz.setClazzName(StrUtil.trim(clazz.getClazzName()));
        }

        clazzService.updateById(clazz);

        return R.success("更新成功");
    }

    @Operation(summary = "删除班级")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @DeleteMapping
    public R<Void> delete(Long clazzId) {

        clazzService.deleteClazz(clazzId);

        return R.success("删除成功");
    }

    @Operation(summary = "查询班级")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping
    public R<List<ClazzVo>> queryList(@RequestBody Clazz clazz) {

        List<ClazzVo> list = clazzService.queryList(clazz);

        return R.success(list);
    }
}
