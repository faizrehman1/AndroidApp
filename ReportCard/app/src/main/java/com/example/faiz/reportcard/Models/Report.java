package com.example.faiz.reportcard.Models;

/**
 * Created by Faiz on 8/2/2016.
 */
public class Report {
    public String name;
    public int img;
    public String grade;
    public int position;
    public String classAttend;

    public Report(String name, int img, String grade, int position, String classAttend) {
        this.name = name;
        this.img = img;
        this.grade = grade;
        this.position = position;
        this.classAttend = classAttend;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(name);
        builder.append("/n");
        builder.append("Grade: ");
        builder.append(grade);
        builder.append("/n");
        builder.append("Position: ");
        builder.append(position);
        builder.append("/n");
        builder.append("Class Attend: ");
        builder.append(classAttend);


        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String  grade) {
        this.grade = grade;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getClassAttend() {
        return classAttend;
    }

    public void setClassAttend(String classAttend) {
        this.classAttend = classAttend;
    }
}
