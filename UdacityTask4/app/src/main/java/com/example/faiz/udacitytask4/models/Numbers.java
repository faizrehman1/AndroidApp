package com.example.faiz.udacitytask4.models;

/**
 * Created by Faiz on 8/1/2016.
 */
public class Numbers {

    private String numbers;
    private String name;
    private int imageResourse;
    private int audioResource;

    public Numbers(String numbers, String name, int imageResourse, int audioResource) {
        this.numbers = numbers;
        this.name = name;
        this.imageResourse = imageResourse;
        this.audioResource = audioResource;
    }

    public int getAudioResource() {
        return audioResource;
    }

    public void setAudioResource(int audioResource) {
        this.audioResource = audioResource;
    }

    public int getImageResourse() {
        return imageResourse;
    }

    public void setImageResourse(int imageResourse) {
        this.imageResourse = imageResourse;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
