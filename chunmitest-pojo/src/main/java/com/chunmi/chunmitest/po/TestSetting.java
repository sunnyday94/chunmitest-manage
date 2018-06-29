package com.chunmi.chunmitest.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TestSetting implements Serializable{
	/**
	 * 主键id
	 */
    private Long id;

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 并发数
     */
    private Long concurrentNum;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 请求方式(0=get;1=post)
     */
    private String requestMode;

    /**
     * 请求头参数(token)
     */
    private String token;
    

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 测试用例描述
     */
    private String testCaseDes;

    /**
     * 期望结果
     */
    private String expectedResult;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 删除标记(0=未删除;1=删除)
     */
    private String delFlag;

    
    /**
     * 备注
     */
    private String remark;
    
    
    /**
     * 是否需要验证签名(0=不需要;1=需要)
     */
    private String isNeedSign;
    
    
    /**
     * sign生成规则(0=默认)
     */
    private String signType;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Long getConcurrentNum() {
        return concurrentNum;
    }

    public void setConcurrentNum(Long concurrentNum) {
        this.concurrentNum = concurrentNum;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getRequestMode() {
        return requestMode;
    }

    public void setRequestMode(String requestMode) {
        this.requestMode = requestMode;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getTestCaseDes() {
        return testCaseDes;
    }

    public void setTestCaseDes(String testCaseDes) {
        this.testCaseDes = testCaseDes;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsNeedSign() {
		return isNeedSign;
	}

	public void setIsNeedSign(String isNeedSign) {
		this.isNeedSign = isNeedSign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

}