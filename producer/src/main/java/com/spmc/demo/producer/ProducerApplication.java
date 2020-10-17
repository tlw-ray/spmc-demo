package com.spmc.demo.producer;

import com.rabbitmq.client.*;
import com.spmc.demo.Configuration;
import com.spmc.demo.task.Task;
import com.spmc.demo.task.TaskFactory;

public class ProducerApplication {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Configuration.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(Configuration.QUEUE_NAME, false, false, false, null);
        System.out.println("生产者产生消息: ");
        for(int i=0; i<Configuration.MESSAGE_COUNT; i++){
            Task task = TaskFactory.getInstance().generateTask();
            System.out.println("\t" + i + "\t生成: " + task);
            byte[] bytes = TaskFactory.getInstance().toJsonBytes(task);
            channel.basicPublish("", Configuration.QUEUE_NAME, null, bytes);
        }
        channel.close();
        connection.close();
    }
}
