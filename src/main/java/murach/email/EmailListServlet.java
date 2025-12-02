package murach.email;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import murach.business.User;
import murach.data.UserDB;

public class EmailListServlet extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
        // Đã sửa: KHÔNG khai báo ném ra Exception, chỉ ném ServletException và IOException
            throws ServletException, IOException {

        String url = "/index.jsp";
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/index.jsp";
        }
        else if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            User user = new User(firstName, lastName, email);

            try {
                // Thử gọi phương thức có thể ném ra Exception
                UserDB.insert(user);

                // set User object in request object and set URL
                request.setAttribute("user", user);
                url = "/thanks.jsp";
            } catch (Exception e) {
                // Bắt mọi Exception và ném nó dưới dạng ServletException
                // Đây là cách chuẩn để xử lý checked exceptions trong phương thức Servlet
                throw new ServletException("Lỗi lưu trữ dữ liệu JPA.", e);
            }
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        // Đã sửa: KHÔNG khai báo ném ra Exception
            throws ServletException, IOException {
        doPost(request, response);
    }
}