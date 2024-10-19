package com.site.mountain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    //主键 id
    private String id;

    //项目代码 project_code
    private String projectCode;

    //项目类型（1项目2工区） project_type
    private String projectType;

    //与全国平台项目编码统一  project_sys_code
    private String projectSysCode;

    //项目名称 project_name
    private String projectName;

    //项目全称 project_full_name
    private String projectFullName;

    //标段 section
    private String section;

    //施工许可证编号 builder_licenses
    private String builderLicenses;

    //施工许可证发证日期- issue_contract_date
    private Timestamp issueContractDate;

    //建设用地规划许可证编号 build_plan_num
    private String buildPlanNum;

    //建设工程规划许可证编号 prj_plan_num
    private String prjPlanNum;

    //项目分类（见字典表） category
    private String category;

    //项目所在地。参考行政区划字典 area_code
    private String areaCode;

    //所在城市 city
    private String city;

    // 排序num
    private Integer num;

    //上级id pid
    private String pid;

    //所有上级id pids
    private String pids;

    //机构id org_id
    private BigInteger orgId;

    //开工时间 start_time
    private Timestamp startTime;

    //竣工时间 end_time
    private Timestamp endTime;

    //项目简介 description
    private String description;

    //合同额，单位：（万元） invest
    private Double invest;

    //总面积，单位：平方米 building_area
    private Double buildingArea;

    //路宽 road_width
    private Double roadWidth;

    //总长度，单位：米 building_length
    private Double buildingLength;

    //起点桩号 start_stake_num
    private String startStakeNum;

    //终点桩号 terminus_stake_num
    private String terminusStakeNum;

    //起点桩号 加号后 stake_num_meter
    private Integer stakeNumMeter;

    //终点桩号 加号后 terminus_stake_num_meter
    private Integer terminusStakeNumMeter;

    //项目状态。（001筹备，002立项，003在建，004完工，005停工） prj_status
    private String prjStatus;

    //起点纬度 start_lat
    private String startLat;

    //起点经度 start_lng
    private String startLng;

    //终点纬度 end_lat
    private String endLat;

    //终点经度 end_lng
    private String endLng;

    //项目地址 address
    private String address;

    //中标资质 bidding_qualification
    private String biddingQualification;

    //立项时间 approval_date
    private Timestamp approvalDate;

    //立项文号 approval_num
    private String approvalNum;

    //立项级别。（001部级，002省级，003地市级，004区县级，005国家级，006其他） approval_level_num
    private String approvalLevelNum;

    //申请人 applicant
    private String applicant;

    //申请部门 apply_dept
    private String applyDept;

    //归属工程局 belong_engineering_dept
    private String belongEngineeringDept;

    //大项目办所属单位 big_project
    private String bigProject;

    //行政管理單位 administrative_unit
    private String administrativeUnit;

    //上级单位 parent_dept
    private String parentDept;

    //合同编号 contract_no
    private Integer contractNo;

    //建设规模。（01大型，02中型，03小型） prj_size
    private String prjSize;

    //建设性质。001新建，002改建，003扩建，004恢复，005迁建，006拆除，009其他 property_num
    private String propertyNum;

    //工程用途。（字典表） function_num
    private String functionNum;

    //建设单位 construction_unit
    private String constructionUnit;

    //设计单位 design_unit
    private String designUnit;

    //监理单位 supervising_unit
    private String supervisingUnit;

    //施工单位 execution_unit
    private String executionUnit;

    //项目经理姓名 manager_name
    private String managerName;

    //项目经理电话 manager_phone
    private String managerPhone;

    //项目经理身份证号 manager_idcard
    private String managerIdcard;

    //上报状态 report_status
    private String reportStatus;

    //上报批次号 report_batch_number
    private Long reportBatchNumber;

    //是否删除 is_delete
    private Integer isDelete;

    //创建时间 gmt_create
    private Timestamp gmtCreate;

    //最后修改时间 gmt_modify
    private Timestamp gmtModify;

    //user_create
    private String userCreate;

    //user_modify
    private String userModify;

    private List<ProjectFile> projectFileList = new ArrayList<ProjectFile>();

    // 扩展字段
    private Integer pageNum;
    private Integer pageSize;

    private List<Project> children = new ArrayList<>();


    //get,set
    public List<Project> getChildren() {
        return children;
    }

    public void setChildren(List<Project> children) {
        this.children = children;
    }

    public List<ProjectFile> getProjectFileList() {
        return projectFileList;
    }

    public void setProjectFileList(List<ProjectFile> projectFileList) {
        this.projectFileList = projectFileList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectSysCode() {
        return projectSysCode;
    }

    public void setProjectSysCode(String projectSysCode) {
        this.projectSysCode = projectSysCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectFullName() {
        return projectFullName;
    }

    public void setProjectFullName(String projectFullName) {
        this.projectFullName = projectFullName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getBuilderLicenses() {
        return builderLicenses;
    }

    public void setBuilderLicenses(String builderLicenses) {
        this.builderLicenses = builderLicenses;
    }

    public Timestamp getIssueContractDate() {
        return issueContractDate;
    }

    public void setIssueContractDate(Timestamp issueContractDate) {
        this.issueContractDate = issueContractDate;
    }

    public String getBuildPlanNum() {
        return buildPlanNum;
    }

    public void setBuildPlanNum(String buildPlanNum) {
        this.buildPlanNum = buildPlanNum;
    }

    public String getPrjPlanNum() {
        return prjPlanNum;
    }

    public void setPrjPlanNum(String prjPlanNum) {
        this.prjPlanNum = prjPlanNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getInvest() {
        return invest;
    }

    public void setInvest(Double invest) {
        this.invest = invest;
    }

    public Double getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Double buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Double getRoadWidth() {
        return roadWidth;
    }

    public void setRoadWidth(Double roadWidth) {
        this.roadWidth = roadWidth;
    }

    public Double getBuildingLength() {
        return buildingLength;
    }

    public void setBuildingLength(Double buildingLength) {
        this.buildingLength = buildingLength;
    }

    public String getStartStakeNum() {
        return startStakeNum;
    }

    public void setStartStakeNum(String startStakeNum) {
        this.startStakeNum = startStakeNum;
    }

    public String getTerminusStakeNum() {
        return terminusStakeNum;
    }

    public void setTerminusStakeNum(String terminusStakeNum) {
        this.terminusStakeNum = terminusStakeNum;
    }

    public Integer getStakeNumMeter() {
        return stakeNumMeter;
    }

    public void setStakeNumMeter(Integer stakeNumMeter) {
        this.stakeNumMeter = stakeNumMeter;
    }

    public Integer getTerminusStakeNumMeter() {
        return terminusStakeNumMeter;
    }

    public void setTerminusStakeNumMeter(Integer terminusStakeNumMeter) {
        this.terminusStakeNumMeter = terminusStakeNumMeter;
    }

    public String getPrjStatus() {
        return prjStatus;
    }

    public void setPrjStatus(String prjStatus) {
        this.prjStatus = prjStatus;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getStartLng() {
        return startLng;
    }

    public void setStartLng(String startLng) {
        this.startLng = startLng;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getEndLng() {
        return endLng;
    }

    public void setEndLng(String endLng) {
        this.endLng = endLng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBiddingQualification() {
        return biddingQualification;
    }

    public void setBiddingQualification(String biddingQualification) {
        this.biddingQualification = biddingQualification;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(String approvalNum) {
        this.approvalNum = approvalNum;
    }

    public String getApprovalLevelNum() {
        return approvalLevelNum;
    }

    public void setApprovalLevelNum(String approvalLevelNum) {
        this.approvalLevelNum = approvalLevelNum;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplyDept() {
        return applyDept;
    }

    public void setApplyDept(String applyDept) {
        this.applyDept = applyDept;
    }

    public String getBelongEngineeringDept() {
        return belongEngineeringDept;
    }

    public void setBelongEngineeringDept(String belongEngineeringDept) {
        this.belongEngineeringDept = belongEngineeringDept;
    }

    public String getBigProject() {
        return bigProject;
    }

    public void setBigProject(String bigProject) {
        this.bigProject = bigProject;
    }

    public String getAdministrativeUnit() {
        return administrativeUnit;
    }

    public void setAdministrativeUnit(String administrativeUnit) {
        this.administrativeUnit = administrativeUnit;
    }

    public String getParentDept() {
        return parentDept;
    }

    public void setParentDept(String parentDept) {
        this.parentDept = parentDept;
    }

    public Integer getContractNo() {
        return contractNo;
    }

    public void setContractNo(Integer contractNo) {
        this.contractNo = contractNo;
    }

    public String getPrjSize() {
        return prjSize;
    }

    public void setPrjSize(String prjSize) {
        this.prjSize = prjSize;
    }

    public String getPropertyNum() {
        return propertyNum;
    }

    public void setPropertyNum(String propertyNum) {
        this.propertyNum = propertyNum;
    }

    public String getFunctionNum() {
        return functionNum;
    }

    public void setFunctionNum(String functionNum) {
        this.functionNum = functionNum;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getDesignUnit() {
        return designUnit;
    }

    public void setDesignUnit(String designUnit) {
        this.designUnit = designUnit;
    }

    public String getSupervisingUnit() {
        return supervisingUnit;
    }

    public void setSupervisingUnit(String supervisingUnit) {
        this.supervisingUnit = supervisingUnit;
    }

    public String getExecutionUnit() {
        return executionUnit;
    }

    public void setExecutionUnit(String executionUnit) {
        this.executionUnit = executionUnit;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerIdcard() {
        return managerIdcard;
    }

    public void setManagerIdcard(String managerIdcard) {
        this.managerIdcard = managerIdcard;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Long getReportBatchNumber() {
        return reportBatchNumber;
    }

    public void setReportBatchNumber(Long reportBatchNumber) {
        this.reportBatchNumber = reportBatchNumber;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserModify() {
        return userModify;
    }

    public void setUserModify(String userModify) {
        this.userModify = userModify;
    }


    // 扩展字段
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
