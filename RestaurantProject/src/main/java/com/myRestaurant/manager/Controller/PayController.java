package com.myRestaurant.manager.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myRestaurant.manager.Dto.InvoiceDetailDto;
import com.myRestaurant.manager.Dto.InvoiceDto;
import com.myRestaurant.manager.Dto.UserDto;
import com.myRestaurant.manager.Entities.InvoiceEntities;
import com.myRestaurant.manager.Entities.TableEntities;
import com.myRestaurant.manager.Payload.ResponseData;
import com.myRestaurant.manager.Payload.Response.ResponseDto;
import com.myRestaurant.manager.Repository.InvoiceRepository;
import com.myRestaurant.manager.Repository.TableRepository;
import com.myRestaurant.manager.Service.InvoiceService;
import com.myRestaurant.manager.Service.TableService;

@CrossOrigin("*")
@RestController
@RequestMapping("/homepage-cashier")
public class PayController {
	@Autowired
    private InvoiceService invoiceService;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	TableRepository tableRepository;

	@GetMapping("/pay/list-invoice")
    public List<InvoiceDto> getInvoices() {
		return invoiceService.getAllInvoices();
    }
	// Tìm kiếm hóa đơn theo table_id
    @GetMapping("/pay/search-invoice")
    public List<InvoiceDto> searchInvoices(@RequestParam String tableId) {
        return invoiceService.searchInvoicesByTableId(tableId);
    }
    
    @PostMapping("/pay/payment/{invoiceId}")
    public ResponseEntity<ResponseData> processPayment(@PathVariable int invoiceId) {
        // Tìm hóa đơn theo invoiceId
        InvoiceEntities invoice = invoiceRepository.findById(invoiceId).orElse(null);
        
        if (invoice != null && !invoice.isInvoiceStatus()) {  // Chỉ xử lý nếu hóa đơn chưa thanh toán
            // Đánh dấu hóa đơn đã thanh toán
            invoice.setInvoiceStatus(true);
            invoiceRepository.save(invoice);
            
            // Cập nhật trạng thái bàn
            TableEntities table = invoice.getTable();
            table.setTableStatus(false);  // Đánh dấu bàn trống
            tableRepository.save(table);

            // Tạo đối tượng responseDto
            ResponseData responseDto = new ResponseData();
            responseDto.setStatus(200);
            responseDto.setDescription("Thanh toán thành công!");
            responseDto.setData(table.getTableId());  // Trả về mã bàn
            
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            // Nếu hóa đơn không tồn tại hoặc đã thanh toán
            ResponseData responseDto = new ResponseData();
            responseDto.setStatus(400);
            responseDto.setDescription("Hóa đơn đã thanh toán hoặc không tồn tại");
            
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }
 // Cập nhật trạng thái của bàn khi thanh toán thành công
    @PostMapping("/update-table-status/{tableId}")
    public String updateTableStatus(@PathVariable("tableId") String tableId) {
        // Thực hiện logic để cập nhật trạng thái bàn
        boolean updated = tableService.updateTableStatus(tableId, true); // True = Bàn trống

        if (updated) {
            return "redirect:/homepage-cashier/pay";
        }
        return "error";
    }
    
    @GetMapping("/detail-invoice/{invoiceId}")
    @ResponseBody
    public ResponseEntity<List<InvoiceDetailDto>> getInvoiceDetails(@PathVariable int invoiceId) {
        List<InvoiceDetailDto> details = invoiceService.getInvoiceDetails(invoiceId);
        if (details != null && !details.isEmpty()) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }
}
