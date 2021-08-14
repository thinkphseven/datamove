package com.longtu.datamove.entity.gla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * 单位核算账套表
 * </p>
 *
 * @author ${author}
 * @since 2020-05-11
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_acct_set")
//@ApiModel(value="GlaAcctSetPO对象", description="单位核算账套表")
public class GlaAcctSetPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "账套id")
    @Id
    private String acctSet;

    //@ApiModelProperty(value = "财政区划代码")
    private String mofDivCode;
    //@ApiModelProperty(value = "账套编号")
    private String acctSetCode;

    //@ApiModelProperty(value = "套账名称")
    private String acctSetName;

    //@ApiModelProperty(value = "科目体系")
    private String acctSetType;

    //@ApiModelProperty(value = "单位代码")
    private String agencyCode;

    //@ApiModelProperty(value = "会计年度")
    private String fiscalYear;

    //@ApiModelProperty(value = "启用时间")
    private Date startDate;

    //@ApiModelProperty(value = "账套状态")
    private Integer isEnabled;

    //@ApiModelProperty(value = "财务负责人")
    private String flLeader;

    //@ApiModelProperty(value = "凭证号生成方式")
    private String vchnoType;

    //@ApiModelProperty(value = "科目层级编码")
    private String codeFormat;

    //@ApiModelProperty(value = "启用月份")
    private String startMonth;

    //@ApiModelProperty(value = "账套Id")
    private String acctSetId;

    //@ApiModelProperty(value = "会计年度")
    private String setYear;

    //@ApiModelProperty(value = "山西账套读取的编码")
    private String acctViewCode;

    //@ApiModelProperty(value = "厂商名称")
    private String appId;



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
