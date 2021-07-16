package app.utils;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.Topic;
import com.google.pubsub.v1.TopicName;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class PubSubUtils {

    public void publish(String projectId, String topicId, String message){

        TopicName topicName = TopicName.of(projectId, topicId);

    }

    public void createTopic(String projectId, String topicId) throws IOException {

        TopicAdminClient topicAdminClient = TopicAdminClient.create();
        TopicName topicName = TopicName.of(projectId, topicId);
        Topic topic = topicAdminClient.createTopic(topicName);
    }

    public void deleteTopic(String projectId, String topicId) throws IOException {

        TopicAdminClient topicAdminClient = TopicAdminClient.create();
        TopicName topicName = TopicName.of(projectId, topicId);
        topicAdminClient.deleteTopic(topicName);
    }


    public void publishWithErrorHandler(String projectId,
                                               String topicId,
                                               String message
    ) throws InterruptedException {

        TopicName topicName = TopicName.of(projectId, topicId);

        Publisher publisher = null;

        try {
            publisher = Publisher.newBuilder(topicName).build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(data)
                    .build();

            callApi(publisher, pubsubMessage);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }


    public void publishWithErrorHandler(String projectId,
                                               String topicId,
                                               String message,
                                               ImmutableMap<String, String> attributes
        ) throws InterruptedException {

        TopicName topicName = TopicName.of(projectId, topicId);

        Publisher publisher = null;

        try {
            publisher = Publisher.newBuilder(topicName).build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                                                        .setData(data)
                                                        .putAllAttributes(attributes)
                                                        .build();

            callApi(publisher, pubsubMessage);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           if(publisher != null) {
               publisher.shutdown();
               publisher.awaitTermination(1, TimeUnit.MINUTES);
           }
        }
    }



    private void callApi(Publisher publisher, PubsubMessage pubsubMessage){
        ApiFuture<String> future = publisher.publish(pubsubMessage);

        ApiFutures.addCallback(
                future,
                new ApiFutureCallback<String>() {
                    @Override
                    public void onFailure(Throwable t) {
                        if (t instanceof ApiException) {
                            ApiException apiException = ((ApiException) t);
                            System.out.println(apiException.getStatusCode().getCode());
                            System.out.println(apiException.isRetryable());
                        }
                        System.out.println("Error publishing message : " + pubsubMessage.getData().toString());
                    }

                    @Override
                    public void onSuccess(String result) {
                        System.out.println("Published message ID: " + result);
                    }
                },
                MoreExecutors.directExecutor()
        );
    }

    public List<String> listTopics(String projectId) throws IOException {
        TopicAdminClient topicAdminClient = TopicAdminClient.create();
        ProjectName projectName = ProjectName.of(projectId);
        List<String> topicList = new ArrayList<>();
        for (Topic topic : topicAdminClient.listTopics(projectName).iterateAll()){
                topicList.add(topic.getName().substring(topic.getName().lastIndexOf("/") +1, topic.getName().length()));
        }
        return topicList;
    }
}
