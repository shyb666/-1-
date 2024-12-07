package com.bite.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class PageRequest {
    private Integer pageNum =1;
    private Integer pageSize = 10;
    private Integer offset;

    public Integer getOffset() {
        return (pageNum-1) * pageSize;
    }

}
