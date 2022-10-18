package com.publiccms.entities.cms;
// Generated 2016-11-20 14:46:17 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.publiccms.common.generator.annotation.GeneratorColumn;

/**
 * CmsDictionaryDataId generated by hbm2java
 */
@Embeddable
public class CmsDictionaryDataId implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @GeneratorColumn(title = "数据字典")
    private String dictionaryId;
    @GeneratorColumn(title = "站点", condition = true)
    @JsonIgnore
    private short siteId;
    @GeneratorColumn(title = "值")
    private String value;

    public CmsDictionaryDataId() {
    }

    public CmsDictionaryDataId(String dictionaryId, short siteId, String value) {
        this.dictionaryId = dictionaryId;
        this.siteId = siteId;
        this.value = value;
    }

    @Column(name = "dictionary_id", nullable = false, length = 20)
    public String getDictionaryId() {
        return this.dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    @Column(name = "site_id", nullable = false)
    public short getSiteId() {
        return this.siteId;
    }

    public void setSiteId(short siteId) {
        this.siteId = siteId;
    }

    @Column(name = "value", nullable = false, length = 50)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CmsDictionaryDataId))
            return false;
        CmsDictionaryDataId castOther = (CmsDictionaryDataId) other;

        return ((this.getDictionaryId() == castOther.getDictionaryId()) || (this.getDictionaryId() != null
                && castOther.getDictionaryId() != null && this.getDictionaryId().equals(castOther.getDictionaryId())))
                && (this.getSiteId()==castOther.getSiteId())
                && ((this.getValue() == castOther.getValue()) || (this.getValue() != null && castOther.getValue() != null
                        && this.getValue().equals(castOther.getValue())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getDictionaryId() == null ? 0 : this.getDictionaryId().hashCode());
         result = 37 * result + this.getSiteId();
        result = 37 * result + (getValue() == null ? 0 : this.getValue().hashCode());
        return result;
    }

}
