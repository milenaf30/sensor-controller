package com.milena.sensorcontroller.common.domain;


import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity<T> implements Serializable {

    @Transient
    protected abstract T getBusinessKey();

    @Override
    public int hashCode() {
        return Objects.hashCode(getBusinessKey());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        return Objects.equals(getBusinessKey(), other.getBusinessKey());
    }
}