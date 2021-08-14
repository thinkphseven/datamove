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
 * @since 2020-07-01
 */
@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_element_set")
//@ApiModel(value="GlaElementSetPO对象", description="")
public class GlaElementSetPO{

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "辅助核算编码")
    @Id
    private String elementCode;

    //@ApiModelProperty(value = "辅助核算名称")
    private String elementName;

    //@ApiModelProperty(value = "是否自定义 0公共  1自定义")
    private Integer isSelf;

    //@ApiModelProperty(value = "是否允许相同名称")
    private Integer isAllowSamename;

    //@ApiModelProperty(value = "文本类型")
    private String inputType;

    //@ApiModelProperty(value = "是否允许使用别名")
    private Integer isAllowAlias;

    //@ApiModelProperty(value = "是否启用:公共")
    private Integer isEnabled;

    //@ApiModelProperty(value = "排序")
    private Integer orderNum;

    //@ApiModelProperty(value = "是否有来源")
    private Integer isSource;



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
