package com.microsoft.azure.eventhubs.samples.eventprocessorsample.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Zhiqiang Lin
 * @Description
 * @create 2018/6/5.
 */
@Component
public class EventhubProperties {
    private String endpoint;
    private String name;
    private String sasKeyName;
    private String sasKey;
    private String consumerGroupName;
    private String namespace;

    public String getEndpoint() {
        return endpoint;
    }

    @Value("${eventhub.endpoint}")
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    @Value("${eventhub.name}")
    public void setName(String name) {
        this.name = name;
    }

    public String getSasKeyName() {
        return sasKeyName;
    }

    @Value("${eventhub.saskeyname}")
    public void setSasKeyName(String sasKeyName) {
        this.sasKeyName = sasKeyName;
    }

    public String getSasKey() {
        return sasKey;
    }

    @Value("${eventhub.saskey}")
    public void setSasKey(String sasKey) {
        this.sasKey = sasKey;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    @Value("${eventhub.consumergroupname}")
    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public String getNamespace() {
        return namespace;
    }

    @Value("${eventhub.namespace}")
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
