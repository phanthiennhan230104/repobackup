package com.myRestaurant.manager.Service;

import com.myRestaurant.manager.Dto.AreaDto;
import com.myRestaurant.manager.Dto.TableDto;
import com.myRestaurant.manager.Entities.AreaEntities;
import com.myRestaurant.manager.Entities.TableEntities;
import com.myRestaurant.manager.Repository.AreaRepository;
import com.myRestaurant.manager.Repository.TableRepository;
import com.myRestaurant.manager.Service.Impl.TableServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService implements TableServiceImpl{

	@Autowired
    private TableRepository tableRepository;

    @Autowired
    private AreaRepository areaRepository;
    
    @Override
    public TableEntities addTable(TableDto tableDto) {
    	// Kiểm tra xem mã bàn đã tồn tại hay chưa
        if (tableRepository.existsById(tableDto.getTable_id())) {
            // Nếu đã tồn tại, ném ra một ngoại lệ hoặc trả về null để báo lỗi
            throw new IllegalArgumentException("Mã bàn đã tồn tại.");
        }

        // Chuyển DTO thành Entity
        TableEntities tableEntity = new TableEntities();
        tableEntity.setTableId(tableDto.getTable_id());
        tableEntity.setTableStatus(tableDto.isTable_status());
        tableEntity.setSeats(6);  // Mặc định số chỗ ngồi là 6

        // Lấy khu vực từ AreaRepository
        AreaEntities areaEntities = areaRepository.findById(tableDto.getAreaDto().getArea_id()).orElse(null);
        if (areaEntities != null) {
            tableEntity.setAreaEntities(areaEntities);
        }

        return tableRepository.save(tableEntity);  // Lưu bàn vào cơ sở dữ liệu
    }
    @Override
    public boolean updateTableStatus(String tableId, boolean isAvailable) {
        TableEntities table = tableRepository.findById(tableId).orElse(null);
        if (table != null) {
            table.setTableStatus(isAvailable); // Thay đổi trạng thái bàn
            tableRepository.save(table); // Lưu lại thay đổi
            return true;
        }
        return false;
    }
}
