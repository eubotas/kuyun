package com.kuyun.fileuploader.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class FdFiles extends BaseEntity implements Serializable {
    private String uuid;

    /**
     * 上传时候的文件名
     *
     * @mbg.generated
     */
    private String fileName;

    private String mime;

    private Long size;

    /**
     * 文件系统中的文件名，防重名做随机化处理
     *
     * @mbg.generated
     */
    private String savedFileName;

    /**
     * 模块名，文件会存放在以模块名相同的子目录下
     *
     * @mbg.generated
     */
    private String moudleName;

    /**
     * oss信息
     *
     * @mbg.generated
     */
    private Integer ossId;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }

    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    public Integer getOssId() {
        return ossId;
    }

    public void setOssId(Integer ossId) {
        this.ossId = ossId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", fileName=").append(fileName);
        sb.append(", mime=").append(mime);
        sb.append(", size=").append(size);
        sb.append(", savedFileName=").append(savedFileName);
        sb.append(", moudleName=").append(moudleName);
        sb.append(", ossId=").append(ossId);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FdFiles other = (FdFiles) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getMime() == null ? other.getMime() == null : this.getMime().equals(other.getMime()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getSavedFileName() == null ? other.getSavedFileName() == null : this.getSavedFileName().equals(other.getSavedFileName()))
            && (this.getMoudleName() == null ? other.getMoudleName() == null : this.getMoudleName().equals(other.getMoudleName()))
            && (this.getOssId() == null ? other.getOssId() == null : this.getOssId().equals(other.getOssId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getMime() == null) ? 0 : getMime().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getSavedFileName() == null) ? 0 : getSavedFileName().hashCode());
        result = prime * result + ((getMoudleName() == null) ? 0 : getMoudleName().hashCode());
        result = prime * result + ((getOssId() == null) ? 0 : getOssId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }
}