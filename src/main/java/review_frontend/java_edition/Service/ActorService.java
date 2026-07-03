package review_frontend.java_edition.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import review_frontend.java_edition.DTO.ActorRequestDto;
import review_frontend.java_edition.Model.Actor;
import review_frontend.java_edition.Repository.ActorRepository;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private Cloudinary cloudinary;

    public CompletableFuture<String> saveActor(ActorRequestDto body , MultipartFile file){

        return  CompletableFuture.supplyAsync(() -> {
            try {
                Map<?,?> url = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.asMap(
                                "gravity", "face",
                                "height", 500,
                                "width", 500,
                                "crop", "thumb"
                        )
                );
                if(url.containsKey("public_id") && url.containsKey("secure_url")) {
                    Actor actor = Actor.builder()
                            .name(body.name())
                            .about(body.about())
                            .gender(body.gender())
                            .image_id((String) url.get("public_id"))
                            .image_url((String) url.get("secure_url"))
                            .modifiedBy("lakshay")
                            .build();
                    actorRepository.save(actor);
                    return "Successfully saved actor "+body.name();
                }else{
                   throw new RuntimeException("Image Upload Exception");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }
}