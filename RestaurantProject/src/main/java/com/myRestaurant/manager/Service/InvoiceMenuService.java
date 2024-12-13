package com.myRestaurant.manager.Service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myRestaurant.manager.Entities.InvoiceEntities;
import com.myRestaurant.manager.Entities.InvoiceMenuEntities;
import com.myRestaurant.manager.Entities.InvoiceMenuKey;
import com.myRestaurant.manager.Entities.MenuEntities;
import com.myRestaurant.manager.Repository.InvoiceMenuRepository;
import com.myRestaurant.manager.Repository.InvoiceRepository;
import com.myRestaurant.manager.Repository.MenuRepository;

@Service
public class InvoiceMenuService {
    
    @Autowired
    private InvoiceMenuRepository invoiceMenuRepository;
    
    @Autowired
    private MenuRepository menuRepository;
    
   
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    public int getLatestInvoiceId() {
        return invoiceRepository.findLatestInvoiceId()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn nào trong cơ sở dữ liệu."));
    }

    public void addDishToInvoice(int invoiceId, int dishId) {
        // Lấy thông tin món ăn từ database
        MenuEntities menuEntity = menuRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Món ăn không tồn tại"));

        // Lấy hóa đơn từ database
        InvoiceEntities invoiceEntity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));

        // Kiểm tra xem món ăn này đã có trong hóa đơn chưa
        Optional<InvoiceMenuEntities> existingInvoiceMenu = invoiceMenuRepository.findByInvoiceEntitiesAndMenuEntities(invoiceEntity, menuEntity);
        
        if (existingInvoiceMenu.isPresent()) {
            // Nếu món ăn đã có trong hóa đơn, cập nhật số lượng và tổng giá
            InvoiceMenuEntities invoiceMenuEntity = existingInvoiceMenu.get();
            invoiceMenuEntity.setQuantity(invoiceMenuEntity.getQuantity() + 1);
            invoiceMenuEntity.calculateTotalPrice();  // Cập nhật lại tổng giá
            invoiceMenuRepository.save(invoiceMenuEntity);  // Lưu lại vào database
        } else {
            // Nếu món ăn chưa có trong hóa đơn, tạo mới bản ghi
            InvoiceMenuEntities invoiceMenuEntity = new InvoiceMenuEntities();

            // Khởi tạo InvoiceMenuKey (khóa chính composite)
            InvoiceMenuKey invoiceMenuKey = new InvoiceMenuKey(invoiceId, dishId);
            invoiceMenuEntity.setId(invoiceMenuKey);  // Thiết lập khóa chính composite

            invoiceMenuEntity.setInvoiceEntities(invoiceEntity);
            invoiceMenuEntity.setMenuEntities(menuEntity);

            // Thiết lập số lượng và tổng giá
            invoiceMenuEntity.setQuantity(1);  // Giả sử số lượng mặc định là 1
            invoiceMenuEntity.setTotalPrice(menuEntity.getPrice());  // Set giá cho món ăn

            // Tính toán tổng giá
            invoiceMenuEntity.calculateTotalPrice();  // Tính toán lại tổng giá (dựa vào số lượng và đơn giá)

            // Lưu vào database
            invoiceMenuRepository.save(invoiceMenuEntity);
        }

    updateInvoiceTotal(invoiceEntity);
}

// Phương thức cập nhật tổng giá của hóa đơn
private void updateInvoiceTotal(InvoiceEntities invoiceEntity) {
    // Tính tổng giá của hóa đơn từ các món ăn trong bảng invoice_menu
    BigDecimal totalSum = invoiceMenuRepository.sumTotalPriceByInvoiceId(invoiceEntity.getInvoiceId());

    // Cập nhật lại tổng giá trong bảng invoice
    invoiceEntity.setSum(totalSum);

    // Nếu có điểm, có thể cập nhật điểm cho hóa đơn tại đây
    // invoiceEntity.setPoint(newPoint);

    // Lưu lại vào bảng invoice
    invoiceRepository.save(invoiceEntity);
    }
}




