package review_frontend.java_edition.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudConfig {

    @Value("${cloudinary.cloud_name}")
    private String cloud_name;

    @Value("${cloudinary.cloud_api_key}")
    private String api_key;

    @Value("${cloudinary.cloud_api_secret}")
    private String api_secret;

    @Bean
    public Cloudinary cloudInstance(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloud_name,
                "api_key",api_key,
                "api_secret",api_secret,
                "secure",true
        ));
    }

    @Bean
    public String getCloudinaryUrl(){
       return "CLOUDINARY_URL=cloudinary://"+api_key+":"+api_secret+"@"+cloud_name;
    }
}
