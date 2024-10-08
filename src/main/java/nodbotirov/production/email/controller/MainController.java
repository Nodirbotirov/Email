package nodbotirov.production.email.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import nodbotirov.production.email.DTO.CatDTO;
import nodbotirov.production.email.entity.Cat;
import nodbotirov.production.email.repository.CatRepo;
import nodbotirov.production.email.service.MailSenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@RestController
public class MainController {

    private final CatRepo catRepo;
    private final MailSenderService mailSender;

    public MainController(CatRepo catRepo, MailSenderService mailSender) {
        this.catRepo = catRepo;
        this.mailSender = mailSender;
    }


    @Operation(
            summary = "кладет нового котика в базу",
            description = "Получает DTO кота и билдером собирает и сохраняет сущность в базу"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        System.out.println(
                "New row: " + catRepo.save(
                        Cat.builder()
                                .id(1)
                                .age(catDTO.getAge())
                                .weight(catDTO.getWeight())
                                .name(catDTO.getName())
                                .build())
        );
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }
    @GetMapping("/hello")
    public void sayHelloFromCat(@RequestParam int id) {
        var cat = catRepo.findById(id).orElseThrow();
        mailSender.send(
                "nbotirov1999@gmail.com",
                "Hello From Kitten",
                "Hello, my name is " + cat.getName() + ". Have a nice day!"
        );
    }

}