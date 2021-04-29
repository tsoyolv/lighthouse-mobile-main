package ru.lighthouse.mobile.main.boot.swagger;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.lighthouse.mobile.main.boot.property.DomainProperties;

@ConditionalOnExpression("${swagger.enabled}")
@Controller
@RequiredArgsConstructor
public class SwaggerRedirectView {

    private final DomainProperties domainProperties;

    @GetMapping(value = SwaggerConfig.URI)
    public RedirectView swaggerUi() {
        return new RedirectView(domainProperties.getUrl() + "/" + domainProperties.getServiceContextPath() + SwaggerConfig.UI_HTML);
    }
}
