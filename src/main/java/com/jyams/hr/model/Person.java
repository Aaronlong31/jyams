package com.jyams.hr.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;
import com.jyams.util.DateTimeUtils;

/**
 * 员工
 * 
 * @author zhanglong
 * 
 */
public class Person {

    /*** 工资类型-日薪 */
    public static final short SALARY_TYPE_DAILY = 1;
    /*** 工资类型-月薪 */
    public static final short SALARY_TYPE_MONTH = 2;

    /*** 员工状态-在职 */
    public static final short STATUS_ACTIVE = 1;
    /*** 员工状态-离职 */
    public static final short STATUS_DIMISSION = 2;

    private Long personId; // 员工标识
    private String personName; // 员工姓名
    private long departmentId; // 部门标识
    private String departmentName; // 部门名称
    private String mobilePhone; // 手机号码
    private String shortNumber; // 短号码
    private Integer shoeSize; // 鞋码
    private String overallsSize; // 工作服尺寸
    private int officeDate; // 就职时间
    private Integer dimissionDate; // 离职时间
    private Integer birthday; // 生日
    private String education; // 学历
    private String jobType; // 工种
    private String certificate; // 证书
    private String title; // 职称
    private String birthPlace; // 籍贯
    private String housePhone; // 住宅电话
    private String idCardCode; // 身份证号码
    private String domicileLocus; // 户口所在地
    private String currentAddress; // 现住地址
    private short salaryType; // 薪资形式
    private float salary; // 薪资
    private short status; // 状态
    private long creatorId;
    private String creatorName;
    private long createdTimestamp;
    private List<Job> jobs = Lists.newArrayList();

    public Long getPersonId() {
        return personId;
    }

    public Person setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    public String getPersonName() {
        return personName;
    }

    public Person setPersonName(String personName) {
        this.personName = personName;
        return this;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public Person setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Person setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public Person setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public Person setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
        return this;
    }

    public Integer getShoeSize() {
        return shoeSize;
    }

    public Person setShoeSize(Integer shoeSize) {
        this.shoeSize = shoeSize;
        return this;
    }

    public String getOverallsSize() {
        return overallsSize;
    }

    public Person setOverallsSize(String overallsSize) {
        this.overallsSize = overallsSize;
        return this;
    }

    public int getOfficeDate() {
        return officeDate;
    }

    public Person setOfficeDate(int officeDate) {
        this.officeDate = officeDate;
        return this;
    }

    public Integer getDimissionDate() {
        return dimissionDate;
    }

    public Person setDimissionDate(Integer dimissionDate) {
        this.dimissionDate = dimissionDate;
        return this;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public Person setBirthday(Integer birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getEducation() {
        return education;
    }

    public Person setEducation(String education) {
        this.education = education;
        return this;
    }

    public String getJobType() {
        return jobType;
    }

    public Person setJobType(String jobType) {
        this.jobType = jobType;
        return this;
    }

    public String getCertificate() {
        return certificate;
    }

    public Person setCertificate(String certificate) {
        this.certificate = certificate;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Person setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Person setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public String getHousePhone() {
        return housePhone;
    }

    public Person setHousePhone(String housePhone) {
        this.housePhone = housePhone;
        return this;
    }

    public String getIdCardCode() {
        return idCardCode;
    }

    public Person setIdCardCode(String idCardCode) {
        this.idCardCode = idCardCode;
        return this;
    }

    public String getDomicileLocus() {
        return domicileLocus;
    }

    public Person setDomicileLocus(String domicileLocus) {
        this.domicileLocus = domicileLocus;
        return this;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public Person setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
        return this;
    }

    public short getSalaryType() {
        return salaryType;
    }

    public Person setSalaryType(short salaryType) {
        this.salaryType = salaryType;
        return this;
    }

    public float getSalary() {
        return salary;
    }

    public Person setSalary(float salary) {
        this.salary = salary;
        return this;
    }

    public short getStatus() {
        return status;
    }

    public Person setStatus(short status) {
        this.status = status;
        return this;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public Person setCreatorId(long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Person setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public Person setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    // ************util method*************//

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Person setOfficeDateString(String officeDate) {
        this.officeDate = DateTimeUtils.convertStringToInteger(officeDate);
        return this;
    }

    public String getOfficeDateString() {
        return DateTimeUtils.convertIntegerDayToString(officeDate);
    }

    public Person setBirthdayString(String birthday) {
        this.birthday = DateTimeUtils.convertStringToInteger(birthday);
        return this;
    }

    public String getBirthdayString() {
        return DateTimeUtils.convertIntegerDayToString(birthday);
    }

    public Person setDimissionDateString(String dimissionDate) {
        this.dimissionDate = DateTimeUtils
                .convertStringToInteger(dimissionDate);
        return this;
    }

    public String getDimissionDateString() {
        return DateTimeUtils.convertIntegerDayToString(dimissionDate);
    }

    public String getSalaryTypeString() {
        switch (salaryType) {
        case SALARY_TYPE_DAILY:
            return "日薪制";
        case SALARY_TYPE_MONTH:
            return "月薪制";
        default:
            return null;
        }
    }

    public String getStatusString() {
        switch (status) {
        case STATUS_ACTIVE:
            return "在职";
        case STATUS_DIMISSION:
            return "离职";
        default:
            return null;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((personId == null) ? 0 : personId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (personId == null) {
            if (other.personId != null)
                return false;
        } else if (!personId.equals(other.personId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
