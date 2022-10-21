package com.ZenPack.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ZenPack.Dto.MenuDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zen_pack")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Builder
public class ZenPack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zen_pack_id")
    private Long zenPackId;

    @Column(name = "zen_pack_name")
    private String name;

//    @Column(name = "json_data",length = 50000)
//    private String jsonData;
    
    @Type(type = "jsonb") // See (2)
    @Column(name = "json_data", columnDefinition = "jsonb")
    private List<MenuDto> menus;

    @Column(name = "created_date")
    private Date updatedTime= new Date();

    @Column(name = "updated_time")
    private Date createdDate =new Date();

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;




    /*@Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_time")
    private Long updatedTime;*/

/*    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private List<Menu> menus;*/

}
