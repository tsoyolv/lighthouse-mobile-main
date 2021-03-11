package ru.lighthouse.mobile.main.boot.swagger;


import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@ConditionalOnExpression("${swagger.enabled}")
@Controller
public class SwaggerRedirectView {

    @GetMapping(value = SwaggerConfig.URI)
    public RedirectView swaggerUi() {
        return new RedirectView(SwaggerConfig.UI_HTML);
    }
}
