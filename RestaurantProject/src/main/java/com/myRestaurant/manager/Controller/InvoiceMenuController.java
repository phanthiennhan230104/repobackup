package com.myRestaurant.manager.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myRestaurant.manager.Payload.Request.AddDishToInvoiceRequest;
import com.myRestaurant.manager.Payload.Response.AddDishToInvoiceResponse;
import com.myRestaurant.manager.Service.InvoiceMenuService;

@RestController
@RequestMapping("/api/invoice-menu")
public class InvoiceMenuController {
    
    @Autowired
    private InvoiceMenuService invoiceMenuService;

    @PostMapping("/add-to-order")
    public ResponseEntity<?> addDishToInvoice(@RequestBody Map<String, Integer> request) {
        try {
            int dishId = request.get("dishId");

            // Lấy InvoiceId mới nhất từ cơ sở dữ liệu
            int latestInvoiceId = invoiceMenuService.getLatestInvoiceId();

            // Thêm món ăn vào hóa đơn
            invoiceMenuService.addDishToInvoice(latestInvoiceId, dishId);

            return ResponseEntity.ok(Map.of("success", true, "message", "Món ăn đã được thêm vào đơn hàng!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", e.getMessage()));
        }
    }

}


