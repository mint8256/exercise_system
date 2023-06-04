package com.five.controller;

import cn.hutool.core.util.StrUtil;
import com.five.aop.annotation.AuthVerify;
import com.five.entity.Paper;
import com.five.enums.RoleEnum;
import com.five.exception.BaseException;
import com.five.service.PaperService;
import com.five.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 14:39
 */
@RestController
@RequestMapping("paper")
public class PaperController {

    @Resource
    private PaperService paperService;

    @Operation(summary = "根据试卷id获取试卷详情")
    @AuthVerify(roles = {RoleEnum.TEACHER,RoleEnum.STUDENT})
    @GetMapping("{paperId}")
    public R<PaperDetail> getSelf(@PathVariable("paperId") Long paperId) {
        Paper paper = paperService.getById(paperId);
        PaperDetail detail = paperService.paperToDetail(paper);
        return R.success(detail);
    }

    @Operation(summary = "更改试卷状态")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping("{paperId}")
    public R<Void> updateStatus(@PathVariable("paperId") Long paperId) {
        paperService.updateStatus(paperId);
        return R.success();
    }


    @Operation(summary = "获取该教师发布的全部试卷")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @GetMapping("list")
    public R<List<PaperVo>> getAllPaper() {

        List<PaperVo> voList = paperService.getAllPaper();

        return R.success(voList);
    }

    @Operation(summary = "删除试卷信息")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @DeleteMapping("{paperId}")
    public R<Void> deletePaper(@PathVariable("paperId") Long paperId) {

        paperService.deletePaper(paperId);

        return R.success("删除成功");
    }


    @Operation(summary = "修改试卷信息")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping("update")
    public R<Void> updatePaper(@RequestBody PaperVo paperVo) {

        Paper paper = paperService.getById(paperVo.getPaperId());

        //校验试卷名称是否唯一
        if (StrUtil.isNotBlank(paperVo.getPaperName()) && !paper.getPaperName().equals(paperVo.getPaperName())) {
            String paperName = paperVo.getPaperName();
            if (paperService.paperNameIsExist(paperName)) {
                throw new BaseException(RCodeEnum.PAPER_NAME_IS_EXIST);
            }
        }

        paperService.updatePaper(paperVo);

        return R.success("修改成功");
    }

    @Operation(summary = "添加试卷信息")
    @AuthVerify(roles = RoleEnum.TEACHER)
    @PostMapping("save")
    public R<Void> savePaper(@RequestBody PaperVo paperVo) {

        //校验试卷名称是否唯一
        String paperName = paperVo.getPaperName();

        if (paperService.paperNameIsExist(paperName)) {
            throw new BaseException(RCodeEnum.PAPER_NAME_IS_EXIST);
        }

        paperService.savePaper(paperVo);

        return R.success("修改成功");
    }



}
