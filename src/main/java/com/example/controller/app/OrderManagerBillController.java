package com.example.controller.app;

import com.example.model.OrderAcceptDTO;
import com.example.repository.BillRepository;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class OrderManagerBillController {
    @Autowired
    private OrderService orderService;



    @GetMapping("/cod/{customerId}")
    public ResponseEntity<List<OrderAcceptDTO>> getCodBills(@PathVariable("customerId") String customerId) {
        List<OrderAcceptDTO> order = orderService.findByCustomerIdAndStatus(customerId);
        return ResponseEntity.ok(order); // Trả về 200 OK + body
    }


    // Đơn hàng chờ xác nhận
    @GetMapping("/waiting/{customerId}")
    public ResponseEntity<List<OrderAcceptDTO>> getWaitingBills(@PathVariable String customerId) {
        List<OrderAcceptDTO> list = orderService.findOrderByAcceptCustomerIdAndStatus(customerId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/paid/{customerId}")
    public ResponseEntity<List<OrderAcceptDTO>> getPaidBills(@PathVariable String customerId) {
        List<OrderAcceptDTO> list = orderService.getAllOrderPaidByCustomerId(customerId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/success/{customerId}")
    public ResponseEntity<List<OrderAcceptDTO>> getSuccessBills(@PathVariable String customerId) {
        List<OrderAcceptDTO> list = orderService.getAllOrderSuccessByCustomerId(customerId);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/cancell/{customerId}")
    public ResponseEntity<List<OrderAcceptDTO>> getCancellBills(@PathVariable String customerId) {
        List<OrderAcceptDTO> list = orderService.getAllOrderCancelByCustomerId(customerId);
        return ResponseEntity.ok(list);
    }
}

