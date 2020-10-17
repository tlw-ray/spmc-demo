package com.spmc.demo.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author tlw
 */
public class TaskFactory {

    // 单例模式
    public static TaskFactory taskFactory = null;

    public static TaskFactory getInstance(){
        if(taskFactory == null){
            taskFactory = new TaskFactory();
        }
        return taskFactory;
    }

    /** 随机产生不同的Task **/
    Random random = new Random();

    private TaskFactory(){

    }

    public Task generateTask(){
        if(random.nextInt() % 2 == 0){
            AddIntegerTask addIntegerTask = new AddIntegerTask();
            addIntegerTask.setLeft(random.nextInt());
            addIntegerTask.setRight(random.nextInt());
            return addIntegerTask;
        }else{
            PrintStringTask printStringTask = new PrintStringTask();
            printStringTask.setContent(Integer.toString(random.nextInt()));
            return printStringTask;
        }
    }

    //TODO 支持持久化多态
    public byte[] toJsonBytes(Task task){
        return JSON.toJSONBytes(task);
    }

    public Task parseJson(byte[] bytes){
        String string = new String(bytes);
        JSONObject json = (JSONObject)JSON.parse(string);
        if(json.containsKey("content")){
            return JSON.parseObject(bytes, PrintStringTask.class);
        }else if(json.containsKey("left")){
            return JSON.parseObject(bytes, AddIntegerTask.class);
        }else{
            throw new RuntimeException("Unsupported json type, require(PrintStringTask or AddIntegerTask): " + json.toJSONString());
        }
    }
}
