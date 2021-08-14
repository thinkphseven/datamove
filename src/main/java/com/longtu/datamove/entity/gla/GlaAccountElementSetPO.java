package com.longtu.datamove.entity.gla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ${author}
 * @since 2020-05-14
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_account_element_set")
//@ApiModel(value="GlaAccountElementSetPO对象", description="")
public class GlaAccountElementSetPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "科目id")
    @Id
    private String accountCls;

    //@ApiModelProperty(value = "账套id")
    private String acctSet;

    //@ApiModelProperty(value = "单位id")
    private String agency;

    //@ApiModelProperty(value = "辅助核算编码")
    private String elementCode;

    //@ApiModelProperty(value = "是否启用")
    private Integer isEnabled;

    //@ApiModelProperty(value = "是否必填")
    private Integer isRequired;

    //@ApiModelProperty(value = "启用框（是否禁用）")
    private Integer isDisabled;


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
