package com.ztel.app.vo.sq;

import java.math.BigDecimal;

public class RouteScorelineVo {
    /**
     * 序号ID
     */
    private Integer id;

    /**
     * 考核项ID
     */
    private Integer evalitemid;

    /**
     * 考核权重
     */
    private BigDecimal assessweight;

    /**
     * T_SQ_ROUTE_SCORESUM 汇总表访问ID 
     */
    private Long fid;

    /**
     * 实际得分
     */
    private BigDecimal actualscore;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 序号ID
     * @return ID 序号ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 序号ID
     * @param id 序号ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 考核项ID
     * @return EVALITEMID 考核项ID
     */
    public Integer getEvalitemid() {
        return evalitemid;
    }

    /**
     * 考核项ID
     * @param evalitemid 考核项ID
     */
    public void setEvalitemid(Integer evalitemid) {
        this.evalitemid = evalitemid;
    }

    /**
     * 考核权重
     * @return ASSESSWEIGHT 考核权重
     */
    public BigDecimal getAssessweight() {
        return assessweight;
    }

    /**
     * 考核权重
     * @param assessweight 考核权重
     */
    public void setAssessweight(BigDecimal assessweight) {
        this.assessweight = assessweight;
    }

    /**
     * T_SQ_ROUTE_SCORESUM 汇总表访问ID 
     * @return FID T_SQ_ROUTE_SCORESUM 汇总表访问ID 
     */
    public Long getFid() {
        return fid;
    }

    /**
     * T_SQ_ROUTE_SCORESUM 汇总表访问ID 
     * @param fid T_SQ_ROUTE_SCORESUM 汇总表访问ID 
     */
    public void setFid(Long fid) {
        this.fid = fid;
    }

    /**
     * 实际得分
     * @return ACTUALSCORE 实际得分
     */
    public BigDecimal getActualscore() {
        return actualscore;
    }

    /**
     * 实际得分
     * @param actualscore 实际得分
     */
    public void setActualscore(BigDecimal actualscore) {
        this.actualscore = actualscore;
    }

    /**
     * 备注
     * @return REMARKS 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}