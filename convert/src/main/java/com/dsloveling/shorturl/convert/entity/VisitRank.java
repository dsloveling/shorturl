package com.dsloveling.shorturl.convert.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SwordZ
 * @version 1.0
 * @date 2020/6/16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitRank {
    private Long rank;

    private String address;

    private Double score;
}
