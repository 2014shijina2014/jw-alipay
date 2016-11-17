package com.jeecg.alipay.sucai.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jeecg.alipay.util.SystemUtil;

/**
 * 描述：</b>QywxNewstemplate:图文素材模板; InnoDB free: 130048 kB<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author zhoujf
 * @since：2016年03月24日 18时59分45秒 星期四 
 * @version:1.0
 */
public class AlipayNewstemplate implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *模板名称	 */	private String templateName;	/**	 *模板类型	 */	private String templateType;	/**	 *微信企业号账号id	 */	private String accountid;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;
	/*子表实体
	 * 图文素材新闻
	 */
	private List <AlipayNewsitem> iNewsitem;
		
	public List<AlipayNewsitem> getiNewsitem() {
		return iNewsitem;
	}
	public void setiNewsitem(List<AlipayNewsitem> iNewsitem) {
		this.iNewsitem = iNewsitem;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getAccountid() {
		return SystemUtil.getOnlieAlipayAccountId();	}	public void setAccountid(String accountid) {	    this.accountid=accountid;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}
	@Override
	public String toString() {
		return "QywxNewstemplate [id=" + id + ", templateName=" + templateName
				+ ", templateType=" + templateType + ", accountid=" + accountid
				+ ", createName=" + createName + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateName=" + updateName
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}
	
}

