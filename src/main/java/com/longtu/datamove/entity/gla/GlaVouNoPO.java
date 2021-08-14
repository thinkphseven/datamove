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
 * @since 2020-07-18
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_vou_no")
//@ApiModel(value="GlaVouNoPO对象", description="")
public class GlaVouNoPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "凭证号")
    @Id
    private Integer voucherNo;

    //@ApiModelProperty(value = "财务顺序号")
    private Integer voucherCNo;

    //@ApiModelProperty(value = "预算顺序号")
    private Integer voucherYNo;

    //@ApiModelProperty(value = "账套")
    private String acctSet;

    //@ApiModelProperty(value = "单位")
    private String agency;

    //@ApiModelProperty(value = "期间")
    private Integer acctPeriod;

    //@ApiModelProperty(value = "凭证类别")
    private String vouCls;

    //@ApiModelProperty(value = "校验码")
    private String checkCode;




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
