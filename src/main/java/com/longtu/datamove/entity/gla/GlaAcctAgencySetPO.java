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
 * @since 2020-05-12
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_acct_agency_set")
//@ApiModel(value="GlaAcctAgencySetPO对象", description="")
public class GlaAcctAgencySetPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "账套id")
    @Id
    private String acctSet;

    //@ApiModelProperty(value = "单位id")
    private String agency;

    //@ApiModelProperty(value = "会计主管")
    private String acctDirector;

    //@ApiModelProperty(value = "制单人")
    private String creator;

    //@ApiModelProperty(value = "审核人")
    private String checker;

    //@ApiModelProperty(value = "记账人")
    private String acctMaker;

    //@ApiModelProperty(value = "凭证号生成方式")
    private Integer agencyVchnoType;

    //@ApiModelProperty(value = "出纳人")
    private String teller;

    //@ApiModelProperty(value = "报账人")
    private String firstCreator;

    //@ApiModelProperty(value = "原制单人")
    private String secondCrearor;

    //@ApiModelProperty(value = "已月结期数")
    private String monthsClosed;

    //@ApiModelProperty(value = "是否启用")
    private Integer isEnabled;

    //@ApiModelProperty(value = "会计科目类型")
    private String executiveAcc;

    //@ApiModelProperty(value = "是否年结")
    private String yearClosed;



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
