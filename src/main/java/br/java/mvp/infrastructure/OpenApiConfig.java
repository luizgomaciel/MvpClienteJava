package br.java.mvp.infrastructure;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public static final String TAG_CLIENTE = "Cliente";

    @Autowired
    private BuildProperties buildProps;

    @Bean
    public OpenAPI customOpenAPI() {
        final Info info = new Info()
                .title("Documentação de API")
                .description("Documentação de API do Cliente")
                .version(this.buildProps.getVersion());

        return new OpenAPI().components(new Components())
                .addTagsItem(createTag(TAG_CLIENTE, "Operações relacionadas ao domínio de Cliente."))
                .info(info);
    }

    public Tag createTag(String name, String description) {
        final Tag tag = new Tag();
        tag.setName(name);
        tag.setDescription(description);
        return tag;
    }

}
