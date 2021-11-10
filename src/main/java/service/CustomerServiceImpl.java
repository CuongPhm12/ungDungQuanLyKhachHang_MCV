package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//giả lập lớp CustomerServiceImpl làm đối tượng cung cấp dữ liệu. Danh sách khách hàng sẽ được lưu vào trong một đối tượng Map. Trong một ứng dụng thực tế,
// danh sách khách hàng sẽ được lưu vào cơ sở dữ liệu.
public class CustomerServiceImpl implements CustomerService{
    private static Map<Integer, Customer> customers;
    static{
        customers = new HashMap<>();
        customers.put(1, new Customer(1,"Tam", "tam@gmail.com","HD"));
        customers.put(2, new Customer(2,"Trang", "trang@gmail.com","HN"));
        customers.put(3, new Customer(3,"Hoangg", "hoang@gmail.com","HB"));
        customers.put(4, new Customer(4,"Cuong", "cuong@gmail.com","HD"));

    }
//   Trả về danh sách tất cả khách hàng
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
//    Lưu một khách hàng
    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(),customer);

    }
//    Tìm một khách hàng theo Id
    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }
//    Cập nhật thông tin của một khách hàng
    @Override
    public void update(int id, Customer customer) {
        customers.put(id, customer);

    }
//    Xoá một khách hàng khỏi danh sách
    @Override
    public void remove(int id) {
        customers.remove(id);

    }
}
