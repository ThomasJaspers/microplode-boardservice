package de.codecentric.microplode.messaging.api;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value="Event")
public class BoardChangedEvent {

    private EventType type;

    private List<FieldDef> fieldList = new ArrayList<FieldDef>();

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public List<FieldDef> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldDef> fieldList) {
        this.fieldList = fieldList;
    }

    public void addField(FieldDef field) {
        getFieldList().add(field);
    }
}
