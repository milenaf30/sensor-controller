package com.milena.sensorcontroller.common.uuid;

import java.util.UUID;

public class UUIDFactory {

    public static String create() {
        return UUID.randomUUID().toString();
    }
}
