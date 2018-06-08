package com.microsoft.azure.eventhubs.samples.SimpleSend.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Zhiqiang Lin
 * @Description
 * @create 2018/6/5.
 */
@Component
public class BlobProperties {
    private String storageContainerName;
    private String storageConnectionString;

    public String getStorageContainerName() {
        return storageContainerName;
    }

    @Value("${blob.storagecontainername}")
    public void setStorageContainerName(String storageContainerName) {
        this.storageContainerName = storageContainerName;
    }

    public String getStorageConnectionString() {
        return storageConnectionString;
    }

    @Value("${blob.storageconnectionstring}")
    public void setStorageConnectionString(String storageConnectionString) {
        this.storageConnectionString = storageConnectionString;
    }
}
