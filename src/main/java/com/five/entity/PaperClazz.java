package com.five.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * (PaperClazz)表实体类
 *
 * @author fly
 * @since 2023-05-08 14:13:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaperClazz {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long clazzId;

    private Long paperId;
}

