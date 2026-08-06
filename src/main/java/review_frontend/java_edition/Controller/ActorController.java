package review_frontend.java_edition.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import review_frontend.java_edition.DTO.ActorRequestDto;
import review_frontend.java_edition.Service.ActorService;

import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> createActor(@Valid @ModelAttribute ActorRequestDto actorBody,
                                                                 @RequestParam("file")MultipartFile avatar){
        return actorService.saveActor(actorBody, avatar)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex ->
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ex.getCause() != null
                                        ? ex.getCause().getMessage()
                                        : ex.getMessage())
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable int id){
        try {
            return new ResponseEntity<>("Delete Actor", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Delete Actor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
