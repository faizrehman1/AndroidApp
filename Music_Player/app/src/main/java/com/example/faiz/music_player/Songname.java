package com.example.faiz.music_player;

public class Songname {

    private String songnamee;
    private String songpath;
    private String songart;

    public String getSongart() {
        return songart;
    }

    public void setSongart(String songart) {
        this.songart = songart;
    }

    public String getSongpath() {
        return songpath;
    }

    public void setSongpath(String songpath) {
        this.songpath = songpath;
    }

    public Songname(String songnamee, String songpath) {
        this.songnamee = songnamee;
        this.songpath = songpath;
    }

    public String getSongnamee() {
        return songnamee;
    }

    public void setSongnamee(String songnamee) {
        this.songnamee = songnamee;
    }
}
