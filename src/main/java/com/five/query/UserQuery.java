package com.five.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 19:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery extends BaseQuery{

    private String realName;
    private String username;
    private String userIdentifier;
    private Integer sex;
    private Long clazzId;

}
