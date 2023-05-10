package com.five.query;

import lombok.*;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/9 19:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuery {

    private Integer page;
    private Integer size;
    private Integer start;

    public Integer getStart() {
        return (Math.max(page - 1, 0)) * size;
    }

    public void setStart(Integer start) {
        this.start = (Math.max(page - 1, 0)) * size;
    }
}
