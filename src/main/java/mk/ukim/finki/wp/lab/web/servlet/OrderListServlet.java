package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.impl.BalloonServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.OrderServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderListServlet", urlPatterns = "/order-list")
public class OrderListServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderServiceImpl orderService;

    public OrderListServlet(SpringTemplateEngine springTemplateEngine, OrderServiceImpl orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("ordersList", orderService.findAll());
        //context.setVariable("user",context.getSession().getAttribute("user"));

        springTemplateEngine.process("ordersList.html", context, resp.getWriter());

    }
}
