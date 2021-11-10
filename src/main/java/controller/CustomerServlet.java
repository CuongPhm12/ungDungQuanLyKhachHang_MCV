package controller;

import model.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//Tạo CustomerServlet để xử lý các request.
//        Lớp CustomerServlet có một đối tượng CustomerServiceImpl dùng để truy xuất đến dữ liệu.
@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(req,resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                showDeleteForm(req,resp);
                break;
            case "view":
                viewCustomer(req,resp);
                break;
            default:
                listCustomers(req,resp);
                break;
        }
    }

    private void viewCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if(customer==null){
            dispatcher = req.getRequestDispatcher("customer/error-404.jsp");
        }else{
            dispatcher=req.getRequestDispatcher("customer/view.jsp");
            req.setAttribute("customer",customer);
            try {
                dispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if(customer==null){
            dispatcher=req.getRequestDispatcher("error-404.jsp");
        }else{
            dispatcher=req.getRequestDispatcher("customer/delete.jsp");
            req.setAttribute("customer",customer);
        }
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Phương thức showEditForm(request, response) sẽ tìm một khách hàng theo id được truyền vào.
//    Nếu khách hàng không tồn tại thì sẽ hiển thị thông báo lỗi. Nếu khách hàng có tồn tại thì
//    hiển thị thông tin khách hàng lên một view là customer/edit.jsp:
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)  {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if(customer==null){
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        }else{
            req.setAttribute("customer",customer);
            dispatcher = req.getRequestDispatcher("customer/edit.jsp");
        }
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)  {
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    //    Phương thức listCustomers(request, response) nhận về danh sách khách hàng
//    và chuyển sang view customer/list.jsp để hiển thị.
    private void listCustomers(HttpServletRequest req, HttpServletResponse resp) {
        List<Customer> customers = this.customerService.findAll();
        req.setAttribute("customers", customers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/list.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Điều hướng việc xử lý các tính năng khác nhau của CustomerServlet thông qua tham số action
//        Tham số action được sử dụng để quy định hành động mà CustomerServlet xử lý trong từng request:
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch(action){
            case "create":
                createCustomer(req,resp);
                break;
            case "edit":
                updateCustomer(req,resp);
                break;
            case "delete":
                deleteCustomer(req,resp);
                break;
            default:
                break;
        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if(customer == null){
            dispatcher = req.getRequestDispatcher("error-404.jsp");
            try {
                dispatcher.forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.customerService.remove(id);
            try {
                resp.sendRedirect("/customers");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email =req.getParameter("email");
        String address =req.getParameter("address");
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if(customer==null){
            dispatcher=req.getRequestDispatcher("error-404.jsp");
        }else{
            customer.setName(name);
            customer.setEmail(email);
            customer.setAddress(address);
            this.customerService.update(id,customer);
            req.setAttribute("customer",customer);
            req.setAttribute("message","Customer information was updated");
            dispatcher=req.getRequestDispatcher("customer/edit.jsp");
        }
        dispatcher.forward(req,resp);
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp)  {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int id = (int)(Math.random()*10000);

    Customer customer = new Customer(id, name, email, address);
    this.customerService.save(customer);
    RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
    req.setAttribute("message","New customer was created");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
