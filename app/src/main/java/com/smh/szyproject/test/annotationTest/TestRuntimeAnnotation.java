package com.smh.szyproject.test.annotationTest;

//作用于类
public class TestRuntimeAnnotation {
    @CherryAnnotation(name = "cherry-peng",age = 23,score = {99,66,77})
    public void study(int times){
        for(int i = 0; i < times; i++){
            System.out.println("Good Good Study, Day Day Up!");
        }
    }
}
