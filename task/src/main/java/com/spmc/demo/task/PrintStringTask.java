package com.spmc.demo.task;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @author tlw
 */
public class PrintStringTask implements Task {

    String content;

    @Override
    public String call(){
        return content;
    }

    @Override
    public String toString() {
        return "PrintStringTask{" +
                "content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintStringTask that = (PrintStringTask) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
