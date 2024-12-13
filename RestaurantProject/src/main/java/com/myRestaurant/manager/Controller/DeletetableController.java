package com.myRestaurant.manager.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myRestaurant.manager.Dto.AreaDto;
import com.myRestaurant.manager.Dto.TableDto;
import com.myRestaurant.manager.Entities.AreaEntities;
import com.myRestaurant.manager.Entities.TableEntities;
import com.myRestaurant.manager.Payload.ResponseData;
import com.myRestaurant.manager.Repository.AreaRepository;
import com.myRestaurant.manager.Repository.TableRepository;
import com.myRestaurant.manager.Service.Impl.AreaServiceImpl;

@RestController
@RequestMapping("/delete-table")
public class DeletetableController {
	@Autowired
	AreaServiceImpl areaServiceImpl;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
    TableRepository tableRepository;
	
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
	@DeleteMapping("/delete/{tableId}")
	public ResponseEntity<String> deleteTable(@PathVariable String tableId) {
	    TableEntities table = tableRepository.findById(tableId).orElse(null);
	    if (table != null) {
	        tableRepository.delete(table);
	        return ResponseEntity.ok("Table deleted successfully.");
	    }
	    return ResponseEntity.status(404).body("Table not found.");
	}

}
