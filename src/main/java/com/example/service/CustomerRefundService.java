package com.example.service;

import com.example.model.CustomerRefund;
import com.example.model.CustomerRefund;
import com.example.repository.CustomerRefundRepository;
import com.example.repository.ServiceBillRepository;
import com.example.repository.ServiceBookedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    CustomerRefundService {
    @Autowired
    private CustomerRefundRepository customerRefundRepository;



    public boolean addCustomerRefund(CustomerRefund customerRefund) {
        // Lưu thông tin hoàn tiền
        CustomerRefund savedRefund = customerRefundRepository.save(customerRefund);

        // Kiểm tra xem việc lưu có thành công hay không
        return savedRefund != null; // Nếu lưu thành công, trả về true; ngược lại trả về false
    }
    public List<CustomerRefund> listAllCustomerRefunds() {
        return customerRefundRepository.findAll(); // Giả sử findAll() đã được định nghĩa trong CustomerRefundRepository
    }
    public List<CustomerRefund> listCustomerRefundsByCustomerID(String customerID) {
        return customerRefundRepository.findByCustomerID(customerID);
    }

}
