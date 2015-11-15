package co.mobiwise.fastgcm;

/**
 * Created by mertsimsek on 31/07/15.
 */
public interface IGCMManager {

    /**
     * Subsribe topic. Topics are like channel.
     * Every device which subsribe to channel
     * get notification.
     * @param topicName
     */
    void subscribeTopic(String topicName);

    /**
     * Unsubscribe from subscribed topic.
     * @param topicName
     */
    void unSubscribeTopic(String topicName);

    /**
     * Register class as a GCM Listener
     * @param gcmListener
     */
    void registerListener(GCMListener gcmListener);

    /**
     * Unregister class
     */
    void unRegisterListener();

}
