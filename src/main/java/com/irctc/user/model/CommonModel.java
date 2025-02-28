package com.irctc.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(Include.NON_NULL)
public class CommonModel {

    @JsonProperty("status")
    private String status;

    @JsonProperty("createdDate")
    private String createdDate;

    @JsonProperty("createdBy")
    private Long createdBy;

    @JsonProperty("modifiedDate")
    private String modifiedDate;

    @JsonProperty("modifiedBy")
    private Long modifiedBy;
}
