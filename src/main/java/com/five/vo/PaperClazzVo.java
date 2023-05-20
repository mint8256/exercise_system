package com.five.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/15 16:26
 */
@Data
public class PaperClazzVo {


    @TableId(type = IdType.AUTO)
    private Long id;

    private Long clazzId;
    private String clazzName;

    private Long paperId;
    private String paperName;

}
