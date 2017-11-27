package com.kuyun.eam.admin.model;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by user on 2017-11-12.
 */
@Document(indexName = "result", replicas = 0, shards = 1)
public class ResultAggregator {
    private String type;

    private String id;

    private Integer createUserId;

    private String createTime;

    private Integer updateUserId;

    private String updateTime;

    private Integer companyId;

    private String tag;

    private String title;

    private String description;

    private String content;

    private String path;

    private String highlightedTitle;

    private String highlightedDescription;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHighlightedTitle() {
        return highlightedTitle;
    }

    public void setHighlightedTitle(String highlightedTitle) {
        this.highlightedTitle = highlightedTitle;
        if (StringUtils.isNotEmpty(highlightedTitle)){
            setTitle(highlightedTitle);
        }
    }

    public String getHighlightedDescription() {
        return highlightedDescription;
    }

    public void setHighlightedDescription(String highlightedDescription) {
        this.highlightedDescription = highlightedDescription;
        if (StringUtils.isNotEmpty(highlightedDescription)){
            setDescription(highlightedDescription);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultAggregator that = (ResultAggregator) o;

        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCreateUserId() != null ? !getCreateUserId().equals(that.getCreateUserId()) : that.getCreateUserId() != null)
            return false;
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null)
            return false;
        if (getUpdateUserId() != null ? !getUpdateUserId().equals(that.getUpdateUserId()) : that.getUpdateUserId() != null)
            return false;
        if (getUpdateTime() != null ? !getUpdateTime().equals(that.getUpdateTime()) : that.getUpdateTime() != null)
            return false;
        if (getCompanyId() != null ? !getCompanyId().equals(that.getCompanyId()) : that.getCompanyId() != null)
            return false;
        if (getTag() != null ? !getTag().equals(that.getTag()) : that.getTag() != null) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getContent() != null ? !getContent().equals(that.getContent()) : that.getContent() != null) return false;
        if (getPath() != null ? !getPath().equals(that.getPath()) : that.getPath() != null) return false;
        if (getHighlightedTitle() != null ? !getHighlightedTitle().equals(that.getHighlightedTitle()) : that.getHighlightedTitle() != null)
            return false;
        return getHighlightedDescription() != null ? getHighlightedDescription().equals(that.getHighlightedDescription()) : that.getHighlightedDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getCreateUserId() != null ? getCreateUserId().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getUpdateUserId() != null ? getUpdateUserId().hashCode() : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        result = 31 * result + (getCompanyId() != null ? getCompanyId().hashCode() : 0);
        result = 31 * result + (getTag() != null ? getTag().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        result = 31 * result + (getHighlightedTitle() != null ? getHighlightedTitle().hashCode() : 0);
        result = 31 * result + (getHighlightedDescription() != null ? getHighlightedDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResultAggregator{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", createUserId=" + createUserId +
                ", createTime='" + createTime + '\'' +
                ", updateUserId=" + updateUserId +
                ", updateTime='" + updateTime + '\'' +
                ", companyId=" + companyId +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", path='" + path + '\'' +
                ", highlightedTitle='" + highlightedTitle + '\'' +
                ", highlightedDescription='" + highlightedDescription + '\'' +
                '}';
    }
}
