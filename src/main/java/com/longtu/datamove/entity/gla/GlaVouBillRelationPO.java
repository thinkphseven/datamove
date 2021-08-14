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
 * @since 2020-08-21
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_vou_bill_relation")
//@ApiModel(value="GlaVouBillRelationPO对象", description="")
public class GlaVouBillRelationPO{

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "id")
    @Id
    private String relId;

    //@ApiModelProperty(value = "账套编号")
    private String acctSetCode;

    //@ApiModelProperty(value = "账套名称")
    private String acctSetName;

    //@ApiModelProperty(value = "会计年度")
    private String fiscalYear;

    //@ApiModelProperty(value = "会计期间")
    private Integer acctPeriod;

    //@ApiModelProperty(value = "记账凭证号")
    private String voucherNo;

    //@ApiModelProperty(value = "原始单据序号")
    private Integer sourceBillSeq;

    //@ApiModelProperty(value = "原始单据类型")
    private Integer billType;

    //@ApiModelProperty(value = "原始单据号")
    private String originalBillNo;

    //@ApiModelProperty(value = "原始单据主单唯一标识")
    private String billId;

    //@ApiModelProperty(value = "原始单据明细单唯一标识")
    private String billDetailId;

    //@ApiModelProperty(value = "凭证主表唯一标识")
    private String vouId;

    //@ApiModelProperty(value = "凭证分录唯一标识")
    private String vouDetId;

    //@ApiModelProperty(value = "单位代码")
    private String agencyCode;

    //@ApiModelProperty(value = "单位id")
    private String agency;

    //@ApiModelProperty(value = "账套id")
    private String acctSet;

    //@ApiModelProperty(value = "模板id")
    private String mouldId;

    //@ApiModelProperty(value = "会计年度")
    private String setYear;




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
