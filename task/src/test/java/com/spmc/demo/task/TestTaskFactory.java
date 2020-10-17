package com.spmc.demo.task;

import org.junit.Assert;
import org.junit.Test;

public class TestTaskFactory {
    @Test
    public void testSerializeAddIntegerTask(){
        AddIntegerTask addIntegerTask = new AddIntegerTask();
        addIntegerTask.setLeft(1);
        addIntegerTask.setRight(2);
        byte[] bytes = TaskFactory.getInstance().toJsonBytes(addIntegerTask);
        Task addIntegerTask1 = TaskFactory.getInstance().parseJson(bytes);
        Assert.assertEquals(addIntegerTask, addIntegerTask1);
    }

    @Test
    public void testSerializePrintStringTask(){
        PrintStringTask printStringTask = new PrintStringTask();
        printStringTask.setContent("Test");
        byte[] bytes = TaskFactory.getInstance().toJsonBytes(printStringTask);
        Task printStringTask1 = TaskFactory.getInstance().parseJson(bytes);
        Assert.assertEquals(printStringTask, printStringTask1);
    }
}
