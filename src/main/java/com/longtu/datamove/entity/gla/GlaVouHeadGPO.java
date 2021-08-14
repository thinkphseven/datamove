package com.longtu.datamove.entity.gla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_vou_head")
//@ApiModel(value="GlaVouHeadGPO对象", description="")
public class GlaVouHeadGPO {
    private static final long serialVersionUID=1L;

//    @ApiModelProperty(value = "凭证主表主键")
    @Id
    private String vouId;

//    @ApiModelProperty(value = "会计年度")
    private String setYear;

//    @ApiModelProperty(value = "账套编号")
    private String acctSetCode;

//    @ApiModelProperty(value = "会计期间")
    private Integer acctPeriod;

//    @ApiModelProperty(value = "单位代码")
    private String agencyCode;

//    @ApiModelProperty(value = "单位会计记账凭证类型")
    private String agencyAcctVoucherType;

//    @ApiModelProperty(value = "记账凭证号")
    private String voucherNo;

//    @ApiModelProperty(value = "凭证摘要")
    private String voucherAbs;

//    @ApiModelProperty(value = "制单人")
    private String inputer;

//    @ApiModelProperty(value = "制单日期")
    private LocalDate inputerDate;

//    @ApiModelProperty(value = "审核人")
    private String auditor;

//    @ApiModelProperty(value = "审核日期")
    private LocalDate auditorDate;

//    @ApiModelProperty(value = "出纳人")
    private String checker;

//    @ApiModelProperty(value = "出纳日期")
    private LocalDate checkerDate;

//    @ApiModelProperty(value = "记账人")
    private String poster;

//    @ApiModelProperty(value = "记账日期")
    private LocalDate posterDate;

//    @ApiModelProperty(value = "记账凭证日期")
    private LocalDate voucherDate;

//    @ApiModelProperty(value = "财务负责人")
    private String fiLeader;

//    @ApiModelProperty(value = "财务贷方金额")
    private BigDecimal crAmt;

//    @ApiModelProperty(value = "财务借方金额")
    private BigDecimal drAmt;

//    @ApiModelProperty(value = "预算借方金额")
    private BigDecimal crYsAmt;

//    @ApiModelProperty(value = "预算贷方金额")
    private BigDecimal drYsAmt;

//    @ApiModelProperty(value = "附件数")
    private Integer vouCnt;

//    @ApiModelProperty(value = "红冲状态")
    private Integer redFlag;

//    @ApiModelProperty(value = "红冲记账凭证号")
    private String redVouNo;

//    @ApiModelProperty(value = "是否调整期")
    private Integer isAdjustPeriod;

//    @ApiModelProperty(value = "凭证状态")
    private Integer vouStatus;

//    @ApiModelProperty(value = "是否为结转凭证")
    private Integer isCarryOver;

//    @ApiModelProperty(value = "单位id")
    private String agency;

//    @ApiModelProperty(value = "单位会计记账凭证类型id")
    private String vouCls;

//    @ApiModelProperty(value = "账套id")
    private String acctSet;

//    @ApiModelProperty(value = "记账凭证属性")
    private String vouAttr;

//    @ApiModelProperty(value = "财务凭证号")
    private String voucherCNo;

//    @ApiModelProperty(value = "预算凭证号")
    private String voucherYNo;




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
