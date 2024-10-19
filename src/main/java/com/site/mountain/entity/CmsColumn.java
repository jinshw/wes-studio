package com.site.mountain.entity;
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;


public class CmsColumn {
    private BigInteger cid;
    private String code;
    private String pcode;
    private Integer status;
    private Integer orders;
    private String cname;
    private String alias;
    private String directory;
    private Integer type;
    private Integer isStatic;
    private String hrefUrl;
    private String logo;
    private Integer detailpageRule;
    private Integer contentType;
    private String homepagetemplPc;
    private String listtemplPc;
    private String articledetailtemplPc;
    private String imgdetailtemplPc;
    private String listPagesize;
    private Timestamp createtime;
    private Timestamp updatetime;
    private BigInteger optPerson;

    public BigInteger getCid() {
        return cid;
    }
    public String getCode() {
        return code;
    }
    public String getPcode() {
        return pcode;
    }
    public Integer getStatus() {
        return status;
    }
    public Integer getOrders() {
        return orders;
    }
    public String getCname() {
        return cname;
    }
    public String getAlias() {
        return alias;
    }
    public String getDirectory() {
        return directory;
    }
    public Integer getType() {
        return type;
    }
    public Integer getIsStatic() {
        return isStatic;
    }
    public String getHrefUrl() {
        return hrefUrl;
    }
    public String getLogo() {
        return logo;
    }
    public Integer getDetailpageRule() {
        return detailpageRule;
    }
    public Integer getContentType() {
        return contentType;
    }
    public String getHomepagetemplPc() {
        return homepagetemplPc;
    }
    public String getListtemplPc() {
        return listtemplPc;
    }
    public String getArticledetailtemplPc() {
        return articledetailtemplPc;
    }
    public String getImgdetailtemplPc() {
        return imgdetailtemplPc;
    }
    public String getListPagesize() {
        return listPagesize;
    }
    public Timestamp getCreatetime() {
        return createtime;
    }
    public Timestamp getUpdatetime() {
        return updatetime;
    }
    public BigInteger getOptPerson() {
        return optPerson;
    }

    public void setCid(BigInteger cid) {
        this.cid = cid;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public void setIsStatic(Integer isStatic) {
        this.isStatic = isStatic;
    }
    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public void setDetailpageRule(Integer detailpageRule) {
        this.detailpageRule = detailpageRule;
    }
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
    public void setHomepagetemplPc(String homepagetemplPc) {
        this.homepagetemplPc = homepagetemplPc;
    }
    public void setListtemplPc(String listtemplPc) {
        this.listtemplPc = listtemplPc;
    }
    public void setArticledetailtemplPc(String articledetailtemplPc) {
        this.articledetailtemplPc = articledetailtemplPc;
    }
    public void setImgdetailtemplPc(String imgdetailtemplPc) {
        this.imgdetailtemplPc = imgdetailtemplPc;
    }
    public void setListPagesize(String listPagesize) {
        this.listPagesize = listPagesize;
    }
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
    public void setOptPerson(BigInteger optPerson) {
        this.optPerson = optPerson;
    }

}
