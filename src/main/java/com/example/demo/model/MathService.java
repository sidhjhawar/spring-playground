package com.example.demo.model;

import java.util.Map;

public class MathService {

    public int sum(int x, int y){
        return x + y;
    }

    public int subtract(int x, int y){
        return x - y;
    }

    public int multiply(int x, int y){
        return x * y;
    }

    public int divide(int x, int y){
        return x/y;
    }

    public String calculateArea(Map bodyParam, String pi) {
        String type = (String) bodyParam.get("type");
        switch (type){
            case "rectangle": {
                if(bodyParam.get("width") != null && bodyParam.get("height") != null) {
                    int width = Integer.parseInt( (String) bodyParam.get("width"));
                    int height = Integer.parseInt( (String)  bodyParam.get("height"));
                    return String.format("Area of a %d x %d rectangle is %d", width,height, width * height);
                }
                break;
            }
            case "circle": {
                if(bodyParam.get("radius") != null) {
                    int radius = Integer.parseInt( (String) bodyParam.get("radius"));
                    return String.format("Area of a circle with a radius of %d is %.4f", radius , radius * Double.parseDouble(pi) );
                }
                break;
            }
        }

        return errorMessage();
    }

    private String errorMessage(){
        return "Invalid";
    }

}
