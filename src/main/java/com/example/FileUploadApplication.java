package com.example;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@SpringBootApplication
public class FileUploadApplication {

    public static void main(String... args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }

    @Component
    @ApplicationPath("api")
    public static class JerseyConfig extends ResourceConfig {
        public JerseyConfig() {
            register(MultiPartFeature.class);
            register(FileUploadResource.class);
        }

    }

    @Path("upload")
    public static class FileUploadResource {
        @POST
        @Consumes(MediaType.MULTIPART_FORM_DATA)
        public String upload(@FormDataParam("file") InputStream file,
                             @FormDataParam("file") FormDataContentDisposition fdcd) throws Exception {
            file.close();
            return fdcd.getFileName();
        }
    }
}


