package com.example.dictionary;

public class YourWord {

    private String Word1;
    private String Type1;
    private String Pronounce1;
    private String Meaning1;

    public YourWord(String word1, String type1, String pronounce1, String meaning1) {
        Word1 = word1;
        Type1 = type1;
        Pronounce1 = pronounce1;
        Meaning1 = meaning1;
    }

    public String getWord1() {
        return Word1;
    }

    public void setWord1(String word1) {
        Word1 = word1;
    }

    public String getType1() {
        return Type1;
    }

    public void setType1(String type1) {
        Type1 = type1;
    }

    public String getPronounce1() {
        return Pronounce1;
    }

    public void setPronounce1(String pronounce1) {
        Pronounce1 = pronounce1;
    }

    public String getMeaning1() {
        return Meaning1;
    }

    public void setMeaning1(String meaning1) {
        Meaning1 = meaning1;
    }
}
