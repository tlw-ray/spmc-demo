package com.spmc.demo.task;

import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;
import java.util.concurrent.Callable;

@JSONType(seeAlso = {AddIntegerTask.class, PrintStringTask.class})
public interface Task extends Callable, Serializable {
}
