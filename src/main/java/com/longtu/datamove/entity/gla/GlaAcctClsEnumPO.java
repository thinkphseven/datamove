package com.longtu.datamove.entity.gla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jhq
 * @since 2020-07-14
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_acct_cls_enum")
//@ApiModel(value="GlaAcctClsEnumPO对象", description="科目类型")
public class GlaAcctClsEnumPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "id")
    @Id
    private String enumId;

    //@ApiModelProperty(value = "账套id")
    private String acctSet;

    //@ApiModelProperty(value = "类型编码")
    private String glaAcctClsEnumCode;

    //@ApiModelProperty(value = "类型名称")
    private String glaAcctClsEnumName;

    //@ApiModelProperty(value = "借贷方向")
    private Integer balanceDir;

    //@ApiModelProperty(value = "科目类型 1财务，2预算")
    private Integer type;

    //@ApiModelProperty(value = "账套类型1：政府 2：企业")
    private String acctType;



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
