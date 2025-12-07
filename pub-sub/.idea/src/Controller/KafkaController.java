package Controller;

import model.Topic;
import model.TopicSubscriber;
import Subscriber.ISubscriber;
import Publisher.IPublisher;
import model.Message;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class KafkaController {
    private final Map<String, Topic> topics;
    private final Map<String, List<TopicSubscribeer>> topicSubscribers;
    private final ExecutorService subscriberExecutor;
    private final AtomicInteger topicIdCounter;

    public KafkaController() {
        topics = new ConcurrentHashMap<>();
        topicSubscribers = new ConcurrentHashMap<>();
        subscriberExecutor = Executors.newCachedThreadPool();
        topicIdCounter = new AtomicInteger(0);
    }

    public Topic createTopic(String topicName){
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic =new Topic(topicName , topicId);
        topics.put(topicId,topic);
        topicSubscribers.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println("Created topic: "+topicName+"with id"+topicId);
        return topic;
    }

    public void subscribe(ISubscriber subscriber,String topicId){
        Topic topic = topics.get(topicId);
        if(topic == null){
            System.err.println("Topic with id"+topicId+"does not exist");
            return;
        }
        TopicSubscriber ts = new TopicSubscriber(topic,subscriber);
        topicSubscribers.get(topicId).add(ts);
        subscriberExecutor.submit(new TopicSubscriberController(ts));
        System.out.println("Subscriber " + subscriber.getId() + " subscriber to topic: " + topic.getTopicName());
    }

    public void publish(IPublisher publisher, String topicId, Message message){
        Topic topic =topics.get(topicId);
        if(topic == null){
            throw new IllegalArgumentException("Topic with id "+ topicId + "does not exist");

        }
        topic.addMessage(message);
        List<TopicSubscriber> subs = topicSubscribers.get(topicId);
        for(TopicSubscriber topicSubscriber: subs){
            synchronized (topicSubscriber){
                topicSubscriber.notify();
            }
        }
        System.out.println("Message "+message.getMessage()+" published to topic "+ topic.getTopicName());

    }

    public void resetOffset(String topicId, ISubscriber subscriber, int newOffset){
        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        if(subscribers == null){
            System.err.println("Topic with id "+ topicId +"does not exist");
            return;
        }
        for(TopicSubscriber ts:subscribers) {
            if(ts.getSubscriber.getId().equals(subscriber.getId())){
                ts.getOffset().set(newOffset);
                synchronized (ts){
                    ts.notify();
                }
                System.out.println("offset for subscriber" + subscriber.getId()+" on topic"+ ts.getTopic().getTopicName() +
                        "reset to " + newOffset);
                break;
            }
        }
    }

    public void shutdown(){
        subscriberExecutor.shutdown();
        try{
            if(!subscriberExecutor.awaitTermination(5,TimeUnit.SECONDS)){
                subscriberExecutor.shutdownNow();
            }
        }
        catch(InterruptedException e){
            subscriberExecutor.shutdownNow();
        }
    }
}