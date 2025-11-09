package com.sqc.academy.bai2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/calculator")
    public String calculate(
            @RequestParam(required = false) String firstNumber,
            @RequestParam(required = false) String secondNumber,
            @RequestParam(required = false) String operator) {

        // Kiểm tra tham số
        if (firstNumber == null || secondNumber == null || operator == null ||
                firstNumber.trim().isEmpty() || secondNumber.trim().isEmpty() || operator.trim().isEmpty()) {
            return "Vui lòng nhập đầy đủ 3 tham số: firstNumber, secondNumber, operator.";
        }

        double num1, num2;

        // Chuyển String sang double
        try {
            num1 = Double.parseDouble(firstNumber.trim());
            num2 = Double.parseDouble(secondNumber.trim());
        } catch (NumberFormatException e) {
            return "Giá trị nhập vào không phải là số hợp lệ.";
        }

        double result;

        // Thực hiện phép tính
        switch (operator.trim()) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    return "Chia cho 0 không hợp lệ.";
                }
                result = num1 / num2;
                break;
            default:
                return "Phép toán không hợp lệ. Vui lòng sử dụng +, -, * hoặc /.";
        }

        return "Kết quả: " + result;
    }
}
