package com.springbootlearning.learningspringboot.backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration OpenAPI/Swagger pour la documentation de l'API.
 * 
 * <p>Cette configuration définit les métadonnées de l'API, les serveurs disponibles
 * et les informations de contact pour la documentation Swagger UI.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class OpenAPIConfig {

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Bean
    public OpenAPI cmmsOpenAPI() {
        // Configuration du serveur de développement
        Server devServer = new Server();
        devServer.setUrl("http://localhost:" + serverPort);
        devServer.setDescription("Serveur de développement local");

        // Configuration du serveur de production (exemple)
        Server prodServer = new Server();
        prodServer.setUrl("https://api.cmms-example.com");
        prodServer.setDescription("Serveur de production");

        // Informations de contact
        Contact contact = new Contact();
        contact.setName("CMMS Backend Team");
        contact.setEmail("gadyubongo@gmail.com");
        contact.setUrl("https://github.com/ubongo2000");

        // Licence
        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        // Informations générales de l'API
        Info info = new Info()
                .title("CMMS Backend API")
                .version("1.0.0")
                .description("""
                        ## API de gestion du CMMS (Computerized Maintenance Management System)
                        
                        Cette API RESTful permet de gérer les entités suivantes:
                        
                        - **Villains**: Gestion des antagonistes
                        - **Blogs**: Gestion des articles de blog
                        - **Anti-Heroes**: Gestion des anti-héros
                        
                        ### Authentification
                        
                        L'API utilise actuellement une authentification basique.
                        
                        ### Rate Limiting
                        
                        - 100 requêtes par minute pour les endpoints de lecture
                        - 20 requêtes par minute pour les endpoints d'écriture
                        
                        ### Versioning
                        
                        L'API utilise un versionnement dans l'URL: `/api/v1/`
                        """)
                .contact(contact)
                .license(license)
                .termsOfService("https://github.com/ubongo2000/cmms-backend/terms");

        // Documentation externe
        ExternalDocumentation externalDocs = new ExternalDocumentation()
                .description("Documentation complète du projet CMMS Backend");
                //.url("https://github.com/ubongo2000/cmms-backend/wiki");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer))
                .externalDocs(externalDocs);
    }
}
