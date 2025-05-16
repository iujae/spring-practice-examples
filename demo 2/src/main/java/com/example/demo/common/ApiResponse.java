package com.example.demo.common;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    //생성자
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }

    // 정적 팩토리 메서도 (성공용)
    public static <T> ApiResponse<T> success (T data){
        return new ApiResponse<>("success","요청성공", data);
    }

    //에러용
    public static <T> ApiResponse<T> error (String message) {
        return new ApiResponse<>("error", message, null);
    }

    //getter
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
