package com.five.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 20:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage<T> {

    // 分页得到的数据
    private T data;
    // 总页数
    private Integer totalPage;
    // 总数据
    private Integer total;

    public void calTotalPage(int total, int size) {

        int tp;
        if (total % size == 0) tp = total / size;
        else tp = total / size + 1;

        this.totalPage = tp;
    }
}
