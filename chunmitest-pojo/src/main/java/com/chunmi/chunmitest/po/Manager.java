package com.chunmi.chunmitest.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Manager implements Serializable{
	/**
	 * 主键
	 */
    private Long id;

    /**
     * 用户名
     */
    private String managerName;

    /**
     * 用户密码
     */
    private String managerPassword;

    /**
     * 用户昵称
     */
    private String managerNickname;

    /**
     * 禁用标记(0=未禁用;1=禁用)
     */
    private String delFlag;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 备注
     */
    private String remark;
    
    
    /**
     * 头像地址
     */
    private String headPic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerNickname() {
        return managerNickname;
    }

    public void setManagerNickname(String managerNickname) {
        this.managerNickname = managerNickname;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
    
    
}