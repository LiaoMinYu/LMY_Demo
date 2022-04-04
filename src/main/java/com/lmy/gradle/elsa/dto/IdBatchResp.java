package com.lmy.gradle.elsa.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author LMinY
 * @since 2020/7/8
 */
@Data
public class IdBatchResp {
    @JsonProperty(required = true)
    @JsonAlias("time_used")
    private int timeUsed;

    @JsonProperty(required = true)
    @JsonAlias("uids")
    private List<String> uids;

}
