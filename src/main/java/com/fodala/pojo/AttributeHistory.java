package com.fodala.pojo;

public class AttributeHistory {
    private Integer id;
    private Integer attributeId;
    private Integer objectId;
    private String objectType;
    private String name;
    private String value;
    private String description;
    private String seq;

    public AttributeHistory(Integer id, Integer attributeId, Integer objectId, String objectType, String name, String value, String description, String seq) {
        this.id = id;
        this.attributeId = attributeId;
        this.objectId = objectId;
        this.objectType = objectType;
        this.name = name;
        this.value = value;
        this.description = description;
        this.seq = seq;
    }

    public AttributeHistory() {
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
