package com.pym.msg.utils;

public interface MsgConstants {

    String MSG_MODEL_ID_TAG = "HCW-MSG-"; //消息报文ID前缀
    String MSG_QUEUE_DESTINATION_NAME = "HCW-MSG-QUEUE";//
    String MSG_TOPIC_DESTINATION_NAME = "HCW-MSG-TOPIC";

    String MSG_ID_REDIS_SET_KEY = "MSG-ID-REDIS-SET";//消息队列在redis中的id存储，处理消息重复消费问题
    String MSG_BAD_REDIS_LIST_KEY = "MSG-BAD-REDIS-LIST";//死信队列在redis中的存储的list key
}
