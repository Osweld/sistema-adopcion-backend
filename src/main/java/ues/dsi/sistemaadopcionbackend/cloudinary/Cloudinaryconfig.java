package ues.dsi.sistemaadopcionbackend.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Cloudinaryconfig {
    private final String CLOUD_NAME = "dkg3altre";
    private final String API_KEY = "945625183958856";
    private final String API_SECRET = "JgRUeZ2xm6qoqYG9Ve5tx-2fwNI";

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);
        return new Cloudinary(config);
    }
}
