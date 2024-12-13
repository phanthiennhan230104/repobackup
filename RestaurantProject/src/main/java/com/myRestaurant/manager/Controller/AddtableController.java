package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Dto.TableDto;
import com.myRestaurant.manager.Entities.TableEntities;
import com.myRestaurant.manager.Payload.ResponseData;
import com.myRestaurant.manager.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homepage-cashier")
public class AddtableController {

	@Autowired
    private TableService tableService;

    @PostMapping("/add-table")
    public ResponseEntity<?> addTable(@RequestBody TableDto tableDto) {
    	ResponseData responseData = new ResponseData();
        try {
            // Kiểm tra xem mã bàn đã tồn tại hay chưa
            TableEntities savedTable = tableService.addTable(tableDto);
            responseData.setStatus(200);
            responseData.setDescription("Bàn đã được thêm thành công!");
            responseData.setData(savedTable);
            return ResponseEntity.ok(responseData);  // Trả về kết quả thành công

        } catch (IllegalArgumentException e) {
            // Nếu mã bàn đã tồn tại, trả về lỗi
            responseData.setStatus(400);
            responseData.setDescription("Mã bàn đã tồn tại.");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } catch (Exception e) {
            // Xử lý các lỗi khác
            responseData.setStatus(500);
            responseData.setDescription("Có lỗi xảy ra khi thêm bàn.");
            responseData.setData(null);  // Dữ liệu là null khi có lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
}
