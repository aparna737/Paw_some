package com.example.pawsome;

public class Dog {
    private String dog_name, dog_image , dog_life_span, dog_weight,dog_temperament ;

    public Dog(String dog_name, String dog_image, String dog_life_span,String dog_weight,String dog_temperament){
        this.dog_image = dog_image;
        this.dog_name = dog_name;
        this.dog_life_span = dog_life_span;
        this.dog_weight = dog_weight;
        this.dog_temperament = dog_temperament;
    }

    public String getDog_name(){
        return dog_name;
    }
    public String getDog_image(){
        return dog_image;
    }
    public String getDog_life_span(){
        return dog_life_span;
    }
    public String getDog_weight(){
        return dog_weight;
    }
    public String getDog_temperament(){
        return dog_temperament;
    }
}
