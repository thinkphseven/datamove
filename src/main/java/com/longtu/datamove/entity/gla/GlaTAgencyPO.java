package com.longtu.datamove.entity.gla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * <p>
 * 单位表
 * </p>
 *
 * @author ${author}
 * @since 2020-12-14
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_t_agency")
//@ApiModel(value="GlaTAgencyPO对象", description="单位表")
public class GlaTAgencyPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "编码")
    private String code;

    //@ApiModelProperty(value = "名称")
    private String name;

    //@ApiModelProperty(value = "简称")
    private String shortName;

    //@ApiModelProperty(value = "全称")
    private String wholeName;

    //@ApiModelProperty(value = "父级节点ID")
    private String parentId;

    //@ApiModelProperty(value = "级次")
    private Integer levelNo;

    //@ApiModelProperty(value = "是否末级")
    private Integer isLeaf;

    //@ApiModelProperty(value = "是否启用")
    private Integer isEnabled;

    //@ApiModelProperty(value = "是否标准")
    private Integer isStandard;

    //@ApiModelProperty(value = "状态")
    private Integer status;

    //@ApiModelProperty(value = "类型")
    private String type;

    //@ApiModelProperty(value = "启用日期")
    private LocalDate startDate;

    //@ApiModelProperty(value = "停用日期")
    private LocalDate endDate;

    //@ApiModelProperty(value = "账套")
    private String acctSet;

    //@ApiModelProperty(value = "单位")
    private String agency;

    //@ApiModelProperty(value = "预留字段1")
    private String data1;

    //@ApiModelProperty(value = "预留字段2")
    private String data2;

    //@ApiModelProperty(value = "预留字段3")
    private String data3;

    //@ApiModelProperty(value = "预留字段4")
    private String data4;

    //@ApiModelProperty(value = "预留字段5")
    private String data5;

    //@ApiModelProperty(value = "预留字段6")
    private String data6;

    //@ApiModelProperty(value = "预留字段7")
    private String data7;

    //@ApiModelProperty(value = "预留字段8")
    private String data8;

    //@ApiModelProperty(value = "预留字段9")
    private String data9;

    //@ApiModelProperty(value = "单位类型 : 政府会计主体  其他")
    private String agencyType;

    //@ApiModelProperty(value = "单位主键")
    @Id
    private String agencyId;




    //    @ApiModelProperty(value = "主键")
    private String dataId;

    //    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

    //    @ApiModelProperty(value = "创建人")
    private String createUser;

    //    @ApiModelProperty(value = "更新人")
    private String updateUser;

    //    @ApiModelProperty(value = "创建时间")
    private String createTime;

    //    @ApiModelProperty(value = "更新时间")
    private String updateTime;
}
