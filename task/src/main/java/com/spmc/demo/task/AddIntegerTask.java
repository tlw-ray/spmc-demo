package com.spmc.demo.task;

import java.util.Objects;

/**
 * @author tlw
 */
public class AddIntegerTask implements Task {

    private int left;
    private int right;

    @Override
    public Integer call(){
        return Integer.valueOf(left + right);
    }

    @Override
    public String toString() {
        return "AddIntegerTask{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddIntegerTask that = (AddIntegerTask) o;
        return left == that.left &&
                right == that.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
