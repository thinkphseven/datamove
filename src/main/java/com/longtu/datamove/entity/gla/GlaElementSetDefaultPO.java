package com.longtu.datamove.entity.gla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-06-01
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_element_set_default")
//@ApiModel(value="GlaElementSetDefaultPO对象", description="")
public class GlaElementSetDefaultPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "账套")
    @Id
    private String acctSet;

    //@ApiModelProperty(value = "账套编码")
    private String acctSetCode;

    //@ApiModelProperty(value = "账套名称")
    private String acctSetName;

    //@ApiModelProperty(value = "单位")
    private String agency;

    //@ApiModelProperty(value = "单位编码")
    private String agencyCode;

    //@ApiModelProperty(value = "单位名称")
    private String agencyName;

    //@ApiModelProperty(value = "辅助要素编码")
    private String elementCode;

    //@ApiModelProperty(value = "辅助要素值级唯一键")
    private String elementValue;

    //@ApiModelProperty(value = "是否启用：（1：启用，0：禁用）")
    private Integer isEnabled;



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
