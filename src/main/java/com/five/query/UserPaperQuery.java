package com.five.query;

import lombok.Data;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/15 15:43
 */
@Data
public class UserPaperQuery extends BaseQuery {

    private String paperName;
    private String clazzName;
    private String studentName;
    private Integer status;
}
