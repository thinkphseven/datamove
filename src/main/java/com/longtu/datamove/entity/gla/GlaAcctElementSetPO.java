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
 * @since 2020-05-14
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_acct_element_set")
//@ApiModel(value="GlaAcctElementSetPO对象", description="")
public class GlaAcctElementSetPO {
    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "账套ID")
    @Id
    private String acctSet;

    //@ApiModelProperty(value = "单位ID")
    private String agency;

    //@ApiModelProperty(value = "辅助核算编码")
    private String elementCode;

    //@ApiModelProperty(value = "文本类型")
    private String inputType;

    //@ApiModelProperty(value = "核算别名")
    private String elementAlias;

    //@ApiModelProperty(value = "是否启用")
    private Integer isEnabled;

    //@ApiModelProperty(value = "执行会计制度类型")
    private String executiveAcc;

    //@ApiModelProperty(value = "排序")
    private Integer orderNum;

    //@ApiModelProperty(value = "列宽度")
    private Integer colWidth;

    //@ApiModelProperty(value = "宽度类型 1-width px 2- width % 3-minWidth px 4-minWidth %")
    private Integer widthType;



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
