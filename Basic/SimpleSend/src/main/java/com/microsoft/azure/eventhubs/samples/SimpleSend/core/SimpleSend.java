/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package com.microsoft.azure.eventhubs.samples.SimpleSend.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import com.microsoft.azure.eventhubs.samples.SimpleSend.bean.EventhubProperties;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SimpleSend {

    public static void main(String[] args)
            throws EventHubException, ExecutionException, InterruptedException, IOException, URISyntaxException {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("conf.xml");
        EventhubProperties eventhubProperties = (EventhubProperties) beanFactory.getBean("eventhubProperties");
        final ConnectionStringBuilder connStr = new ConnectionStringBuilder()
                .setEndpoint(new URI(eventhubProperties.getEndpoint())) // to target National clouds - use .setEndpoint(URI)
                .setEventHubName(eventhubProperties.getName())
                .setSasKeyName(eventhubProperties.getSasKeyName())
                .setSasKey(eventhubProperties.getSasKey());

        final Gson gson = new GsonBuilder().create();

        // The Executor handles all asynchronous tasks and this is passed to the EventHubClient instance.
        // The enables the user to segregate their thread pool based on the work load.
        // This pool can then be shared across multiple EventHubClient instances.
        // The following sample uses a single thread executor, as there is only one EventHubClient instance,
        // handling different flavors of ingestion to Event Hubs here.
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Each EventHubClient instance spins up a new TCP/SSL connection, which is expensive.
        // It is always a best practice to reuse these instances. The following sample shows this.
        final EventHubClient ehClient = EventHubClient.createSync(connStr.toString(), executorService);

        try {
            for (int i = 0; i < 10; i++) {
                String payload = "Message " + Integer.toString(i);
                //PayloadEvent payload = new PayloadEvent(i);
                byte[] payloadBytes = gson.toJson(payload).getBytes(Charset.defaultCharset());
                EventData sendEvent = EventData.create(payloadBytes);

                // Send - not tied to any partition
                // Event Hubs service will round-robin the events across all Event Hubs partitions.
                // This is the recommended & most reliable way to send to Event Hubs.
                ehClient.sendSync(sendEvent);
//                ehClient.sendSync(sendEvent, "2");
//                ehClient.sendSync(sendEvent, "3");
//                ehClient.sendSync(sendEvent, "6");
//                ehClient.sendSync(sendEvent, "0");
            }

            System.out.println(Instant.now() + ": Send Complete...");
            System.in.read();
        } finally {
            ehClient.closeSync();
            executorService.shutdown();
        }
    }
}
