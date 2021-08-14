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
 * @since 2020-05-13
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_account_cls")
//@ApiModel(value="GlaAccountClsPO对象", description="")
public class GlaAccountClsPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "科目唯一标识")
    @Id
    private String accountClsId;

    //@ApiModelProperty(value = "账套id")
    private String acctSet;

    //@ApiModelProperty(value = "单位id")
    private String agency;

    //@ApiModelProperty(value = "会计年度")
    private String fiscalYear;

    //@ApiModelProperty(value = "单位编码")
    private String agencyCode;

    //@ApiModelProperty(value = "单位会计科目代码")
    private String govAcctClsCode;

    //@ApiModelProperty(value = "单位会计科目名称")
    private String govAcctClsName;

    //@ApiModelProperty(value = "父级唯一标识")
    private String parentId;

    //@ApiModelProperty(value = "级次")
    private Integer levelNo;

    //@ApiModelProperty(value = "是否末级")
    private Integer isLeaf;

    //@ApiModelProperty(value = "是否启用")
    private Integer isEnabled;

    //@ApiModelProperty(value = "余额方向")
    private Integer balanceDir;

    //@ApiModelProperty(value = "单位会计科目类型代码")
    private String  govAcctClsEnumCode;

    //@ApiModelProperty(value = "执行会计制度类型")
    private String executiveAcc;

    //@ApiModelProperty(value = "单位类型")
    private Integer agencyTypeCode;

    //@ApiModelProperty(value = "币种代码")
    private String currencyCode;

    //@ApiModelProperty(value = "是否外币核算")
    private Integer isCur;

    //@ApiModelProperty(value = "是否数量核算")
    private Integer isQty;

    //@ApiModelProperty(value = "备注")
    private String remark;

    //@ApiModelProperty(value = "是否标准")
    private Integer isStandard;

    //@ApiModelProperty(value = "账套编码")
    private String acctSetCode;

    //@ApiModelProperty(value = "科目类型 1财务，2预算")
    private Integer type;

    //@ApiModelProperty(value = "是否控制")
    private Integer isControl;

    //@ApiModelProperty(value = "1级")
    private String data1;

    //@ApiModelProperty(value = "2级")
    private String data2;

    //@ApiModelProperty(value = "3级")
    private String data3;

    //@ApiModelProperty(value = "4级")
    private String data4;

    //@ApiModelProperty(value = "5级")
    private String data5;

    //@ApiModelProperty(value = "6级")
    private String data6;

    //@ApiModelProperty(value = "7级")
    private String data7;

    //@ApiModelProperty(value = "8级")
    private String data8;

    //@ApiModelProperty(value = "9级")
    private String data9;

    //@ApiModelProperty(value = "全称")
    private String wholeName;

    //@ApiModelProperty(value = "简称")
    private String shortName;

    //@ApiModelProperty(value = "科目说明")
    private String explain;

    //@ApiModelProperty(value = "会计年度（规范）")
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
