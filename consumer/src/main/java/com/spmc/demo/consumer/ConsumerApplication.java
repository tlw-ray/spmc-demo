package com.spmc.demo.consumer;

import com.rabbitmq.client.*;
import com.spmc.demo.Configuration;
import com.spmc.demo.task.Task;
import com.spmc.demo.task.TaskFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerApplication {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Configuration.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //TODO 动态创建消息队列
//        channel.queueDeclare(Configuration.QUEUE_NAME, false, false, true, null);
        System.out.println("消费者: ");
        AtomicInteger cnt = new AtomicInteger();
        Consumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Task task = TaskFactory.getInstance().parseJson(body);
                cnt.getAndIncrement();
                try {
                    System.out.println("\t" + cnt.get() + "\t收到: " + task + "\t多态执行: " + task.call().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(Configuration.QUEUE_NAME, true, consumer);
    }
}
