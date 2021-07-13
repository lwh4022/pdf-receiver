package app.utils;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.pubsub.v1.Topic;
import com.google.pubsub.v1.TopicName;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

}
