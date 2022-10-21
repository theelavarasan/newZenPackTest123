package com.ZenPack.Dto;

import com.ZenPack.model.FeaturedList;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MenuDto {

    private String menuName;
    private Date createdTime;
    private String createdBy;
    private Integer parentMenuId;
    private List<FeatureDto> features;

}
