package com.myRestaurant.manager.Controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myRestaurant.manager.Dto.AreaDto;
import com.myRestaurant.manager.Dto.TableDto;
import com.myRestaurant.manager.Entities.AreaEntities;
import com.myRestaurant.manager.Entities.InvoiceEntities;
import com.myRestaurant.manager.Entities.TableEntities;
import com.myRestaurant.manager.Entities.UserEntities;
import com.myRestaurant.manager.Payload.ResponseData;
import com.myRestaurant.manager.Repository.AreaRepository;
import com.myRestaurant.manager.Repository.InvoiceRepository;
import com.myRestaurant.manager.Repository.TableRepository;
import com.myRestaurant.manager.Repository.UserRepository;
import com.myRestaurant.manager.Service.Impl.AreaServiceImpl;

@RestController
@RequestMapping("/choose-table")
public class ChoosetableController {
	@Autowired
	AreaServiceImpl areaServiceImpl;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
    TableRepository tableRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping()
	public ResponseEntity<List<AreaDto>> getAllAreas() {
		List<AreaEntities> areaEntities = areaRepository.findAll();
        List<AreaDto> areaDtos = new ArrayList<>();
        
        for (AreaEntities area : areaEntities) {
            AreaDto areaDto = new AreaDto();
            areaDto.setArea_id(area.getAreaId());
            areaDto.setArea_name(area.getAreaName());
            
            // Lấy danh sách bàn trong mỗi khu vực
            List<TableEntities> tableEntities = tableRepository.findByAreaEntities(area);
            List<TableDto> tableDtos = new ArrayList<>();
            for (TableEntities table : tableEntities) {
                TableDto tableDto = new TableDto();
                tableDto.setTable_id(table.getTableId());
                tableDto.setTable_status(table.isTableStatus());
                tableDtos.add(tableDto);
            }
            areaDto.setTables(tableDtos);  // Đưa danh sách bàn vào DTO của khu vực
            areaDtos.add(areaDto);
        }
        return ResponseEntity.ok(areaDtos);
    }
	
	// API lấy danh sách bàn theo khu vực
	@GetMapping("/tables-data/{areaId}")
	public ResponseEntity<List<TableDto>> getTablesDataByArea(@PathVariable String areaId) {
	    AreaEntities area = areaRepository.findById(areaId).orElse(null);
	    if (area != null) {
	        List<TableEntities> tableEntities = tableRepository.findByAreaEntities(area);
	        List<TableDto> tableDtos = new ArrayList<>();
	        for (TableEntities table : tableEntities) {
	            TableDto tableDto = new TableDto();
	            tableDto.setTable_id(table.getTableId());
	            tableDto.setTable_status(table.isTableStatus());
	            tableDtos.add(tableDto);
	        }
	        return ResponseEntity.ok(tableDtos);
	    }
	    return ResponseEntity.notFound().build();
	}
	@PostMapping("/update-table-status")
	public ResponseEntity<ResponseData> updateTableStatus(@RequestBody Map<String, String> request) {
	    String tableId = request.get("tableId");
	    int userId = Integer.parseInt(request.get("userId")); // Thay đổi thành int
	    boolean isAvailable = Boolean.parseBoolean(request.get("isAvailable"));

	    // Lấy thông tin bàn từ database
	    TableEntities table = tableRepository.findById(tableId).orElse(null);

	    if (table != null) {
	        // Kiểm tra nếu bàn đã đang được sử dụng (table_status = true)
	        if (table.isTableStatus()) {
	            // Nếu bàn đã được sử dụng, không cho phép thay đổi trạng thái và trả về thông báo lỗi
	            ResponseData responseData = new ResponseData();
	            responseData.setStatus(400); // Trả về lỗi 400 (Bad Request)
	            responseData.setDescription("Table is occupied! Please choose another table!");
	            responseData.setData(null);
	            return ResponseEntity.status(400).body(responseData);
	        }

	        // Cập nhật trạng thái bàn nếu bàn chưa được sử dụng (table_status = false)
	        table.setTableStatus(isAvailable);
	        tableRepository.save(table);

	        // Tạo mới một invoice
	        UserEntities user = userRepository.findById(userId); // Tìm người dùng bằng int
	        if (user != null) {
	            InvoiceEntities invoice = new InvoiceEntities();
	            invoice.setTable(table);
	            invoice.setUser(user);
	            invoice.setCreateDate(new Timestamp(System.currentTimeMillis()));
	            invoice.setSum(BigDecimal.valueOf(0.0));
	            invoice.setPoint(0);
	            invoiceRepository.save(invoice);
	        } else {
	            // Trả về lỗi nếu user không tồn tại
	            ResponseData responseData = new ResponseData();
	            responseData.setStatus(404);
	            responseData.setDescription("User not found.");
	            responseData.setData(null);
	            return ResponseEntity.status(404).body(responseData);
	        }

	        // Chuẩn bị dữ liệu trả về sau khi cập nhật thành công
	        ResponseData responseData = new ResponseData();
	        responseData.setStatus(200);
	        responseData.setDescription("Choose table successfully and invoice created.");
	        responseData.setData(table); // Gửi lại thông tin bàn
	        return ResponseEntity.ok(responseData);
	    }

	    // Trả về lỗi nếu bàn không tồn tại
	    ResponseData responseData = new ResponseData();
	    responseData.setStatus(404);
	    responseData.setDescription("Table not found.");
	    responseData.setData(null);
	    return ResponseEntity.status(404).body(responseData);
	}
}
