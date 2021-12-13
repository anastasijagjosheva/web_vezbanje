package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.service.impl.BalloonServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BalloonListServlet", urlPatterns = "")
public class BalloonListServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonServiceImpl balloonService;

    public BalloonListServlet(SpringTemplateEngine springTemplateEngine, BalloonServiceImpl balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloonList", balloonService.listAll());
        springTemplateEngine.process("listBalloons.html", context, resp.getWriter());

    }


}
