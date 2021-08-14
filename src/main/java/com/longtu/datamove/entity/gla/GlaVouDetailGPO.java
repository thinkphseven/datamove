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

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-08-24
 */

@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="gla_vou_detail")
//@ApiModel(value="GlaVouDetailGPO对象", description="")
public class GlaVouDetailGPO {

    private static final long serialVersionUID=1L;

    //@ApiModelProperty(value = "凭证分录唯一标识")
    @Id
    private String vouDetId;

    //@ApiModelProperty(value = "凭证主表唯一标识")
    private String vouId;

    //@ApiModelProperty(value = "会计年度")
    private String setYear;

    //@ApiModelProperty(value = "账套编号")
    private String acctSetCode;

    //@ApiModelProperty(value = "会计期间")
    private Integer acctPeriod;

    //@ApiModelProperty(value = "单位代码")
    private String agencyCode;

    //@ApiModelProperty(value = "记账凭证分录序号")
    private Integer vouSeq;

    //@ApiModelProperty(value = "记账凭证号")
    private String voucherNo;

    //@ApiModelProperty(value = "会计分录摘要")
    private String vouDetDesc;

    //@ApiModelProperty(value = "单位会计科目代码")
    private String govAcctClsCode;

    //@ApiModelProperty(value = "单位会计科目名称")
    private String govAcctClsName;

    //@ApiModelProperty(value = "借贷方向")
    private Integer drCr;

    //@ApiModelProperty(value = "金额")
    private BigDecimal amt;

    //@ApiModelProperty(value = "外币金额")
    private BigDecimal foreignAmt;

    //@ApiModelProperty(value = "汇率")
    private BigDecimal extRat;

    //@ApiModelProperty(value = "币种代码")
    private String currencyCode;

    //@ApiModelProperty(value = "数量")
    private BigDecimal qty;

    //@ApiModelProperty(value = "项目代码")
    private String proCode;

    //@ApiModelProperty(value = "部门支出经济分类代码")
    private String depBgtEcoCode;

    //@ApiModelProperty(value = "政府支出经济分类代码")
    private String govBgtEcoCode;

    //@ApiModelProperty(value = "部门代码")
    private String departmentCode;

    //@ApiModelProperty(value = "人员代码")
    private String employeeCode;

    //@ApiModelProperty(value = "资金往来对象类别代码")
    private String fundTraobjTypeCode;

    //@ApiModelProperty(value = "资金往来对象编码")
    private String fundTraObjNo;

    //@ApiModelProperty(value = "资金往来对象名称")
    private String fundTraObjName;

    //@ApiModelProperty(value = "到期日")
    private LocalDate dueDate;

    //@ApiModelProperty(value = "支出功能分类科目代码")
    private String expFuncCode;

    //@ApiModelProperty(value = "资金性质代码")
    private String fundTypeCode;

    //@ApiModelProperty(value = "资金来源代码")
    private String foundTypeCode;

    //@ApiModelProperty(value = "支付业务类型代码")
    private String payBusTypeCode;

    //@ApiModelProperty(value = "支付方式代码")
    private String payTypeCode;

    //@ApiModelProperty(value = "结算方式代码")
    private String setModeCode;

    //@ApiModelProperty(value = "政府采购方式代码")
    private String purMetCode;

    //@ApiModelProperty(value = "资产分类代码")
    private String fatypeCode;

    //@ApiModelProperty(value = "费用经济性质代码")
    private String costTypeCode;

    //@ApiModelProperty(value = "票据日期")
    private LocalDate billDate;

    //@ApiModelProperty(value = "本级指标文号")
    private String corBgtDocNo;

    //@ApiModelProperty(value = "预算级次代码")
    private String budgetLevelCode;

    //@ApiModelProperty(value = "上级指标文号")
    private String supBgtDocNo;

    //@ApiModelProperty(value = "来源项目代码")
    private String oriProCode;

    //@ApiModelProperty(value = "指标类型代码")
    private String bgtTypeCode;

    //@ApiModelProperty(value = "财政内部机构代码")
    private String mofDepCode;

    //@ApiModelProperty(value = "备注")
    private String remark;

    //@ApiModelProperty(value = "单位会计记账凭证类型")
    private String agencyAcctVoucherType;

    //@ApiModelProperty(value = "账套id")
    private String acctSet;

    //@ApiModelProperty(value = "单位id")
    private String agency;

    //@ApiModelProperty(value = "会计科目id")
    private String accountCls;

    //@ApiModelProperty(value = "项目id")
    private String pro;

    //@ApiModelProperty(value = "部门经济分类")
    private String depBgtEco;

    //@ApiModelProperty(value = "政府经济分类")
    private String govBgtEco;

    //@ApiModelProperty(value = "部门")
    private String department;

    //@ApiModelProperty(value = "人员")
    private String employee;

    //@ApiModelProperty(value = "资金往来对象类型")
    private String fundTraobjType;

    //@ApiModelProperty(value = "资金往来对象")
    private String fundTraobj;

    //@ApiModelProperty(value = "功能分类")
    private String expFunc;

    //@ApiModelProperty(value = "资金性质")
    private String fundType;

    //@ApiModelProperty(value = "资金来源")
    private String foundType;

    //@ApiModelProperty(value = "支付业务类型")
    private String payBusType;

    //@ApiModelProperty(value = "支付方式")
    private String payType;

    //@ApiModelProperty(value = "结算方式")
    private String setMode;

    //@ApiModelProperty(value = "政府采购方式")
    private String purMet;

    //@ApiModelProperty(value = "资产分类")
    private String faType;

    //@ApiModelProperty(value = "费用经济性质")
    private String costType;

    //@ApiModelProperty(value = "自定义要素01")
    private String glaElement01;

    //@ApiModelProperty(value = "自定义要素02")
    private String glaElement02;

    //@ApiModelProperty(value = "自定义要素03")
    private String glaElement03;

    //@ApiModelProperty(value = "自定义要素04")
    private String glaElement04;

    //@ApiModelProperty(value = "自定义要素05")
    private String glaElement05;

    //@ApiModelProperty(value = "自定义要素06")
    private String glaElement06;

    //@ApiModelProperty(value = "自定义要素07")
    private String glaElement07;

    //@ApiModelProperty(value = "自定义要素08")
    private String glaElement08;

    //@ApiModelProperty(value = "自定义要素09")
    private String glaElement09;

    //@ApiModelProperty(value = "自定义要素10")
    private String glaElement10;

    //@ApiModelProperty(value = "自定义要素11")
    private String glaElement11;

    //@ApiModelProperty(value = "自定义要素12")
    private String glaElement12;

    //@ApiModelProperty(value = "自定义要素13")
    private String glaElement13;

    //@ApiModelProperty(value = "自定义要素14")
    private String glaElement14;

    //@ApiModelProperty(value = "自定义要素15")
    private String glaElement15;

    //@ApiModelProperty(value = "自定义要素16")
    private String glaElement16;

    //@ApiModelProperty(value = "自定义要素17")
    private String glaElement17;

    //@ApiModelProperty(value = "自定义要素18")
    private String glaElement18;

    //@ApiModelProperty(value = "自定义要素19")
    private String glaElement19;

    //@ApiModelProperty(value = "自定义要素20")
    private String glaElement20;

    //@ApiModelProperty(value = "文本01")
    private String text01;

    //@ApiModelProperty(value = "文本02")
    private String text02;

    //@ApiModelProperty(value = "文本03")
    private String text03;

    //@ApiModelProperty(value = "文本04")
    private String text04;

    //@ApiModelProperty(value = "文本05")
    private String text05;

    //@ApiModelProperty(value = "文本06")
    private String text06;

    //@ApiModelProperty(value = "文本07")
    private String text07;

    //@ApiModelProperty(value = "文本08")
    private String text08;

    //@ApiModelProperty(value = "金额01")
    private BigDecimal amt01;

    //@ApiModelProperty(value = "金额02")
    private BigDecimal amt02;

    //@ApiModelProperty(value = "会计科目类型")
    private String accountClsType;

    //@ApiModelProperty(value = "到期期限")
    private String expire;

    //@ApiModelProperty(value = "公共基础设施项目")
    private String infrastructure;

    //@ApiModelProperty(value = "长期股权投资取得方式")
    private String investment;

    //@ApiModelProperty(value = "项目归类")
    private String proFund;

    //@ApiModelProperty(value = "核算方法")
    private String accountMethod;

    //@ApiModelProperty(value = "长期股权投资取得方式")
    private String affordableHousingProject;

    //@ApiModelProperty(value = "资产处理方式")
    private String assetDisposalMethods;

    //@ApiModelProperty(value = "事业收入来源")
    private String businessIncomeSource;

    //@ApiModelProperty(value = "现金流量")
    private String cashFlow;

    //@ApiModelProperty(value = "往来单位")
    private String glaAgency;

    //@ApiModelProperty(value = "自定义部门")
    private String glaDepartment;

    //@ApiModelProperty(value = "往来个人")
    private String glaPersonal;

    //@ApiModelProperty(value = "无形资产类别")
    private String iaType;

    //@ApiModelProperty(value = "预算来源")
    private String indSource;




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
