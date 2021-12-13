package mk.ukim.finki.wp.lab.web.servlet;

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

@WebServlet(name = "BalloonOrderServlet", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonServiceImpl balloonService;
    private final OrderServiceImpl  orderService;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, BalloonServiceImpl balloonService, OrderServiceImpl orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
        this.orderService = orderService;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());


        String balloonSize = req.getParameter("size");
        req.getSession().setAttribute("balloonSize", balloonSize);


        context.setVariable("balloonType",req.getSession().getAttribute("balloonType"));
        context.setVariable("balloonSize", balloonSize);

        springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }
}
