package br.com.brainweb.interview.core.features.hero.controller;

import br.com.brainweb.interview.core.features.hero.service.HeroService;
import br.com.brainweb.interview.model.request.HeroRequest;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.response.HeroCompareResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/heroes")
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody HeroRequest request) {
        UUID uuid = heroService.create(request);
        return ResponseEntity.created(URI.create("/heroes/" + uuid)).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Hero> find(@PathVariable UUID id) {
        Hero hero = heroService.find(id);
        if(hero == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(hero);
    }

    @GetMapping
    public ResponseEntity<List<Hero>> list(@RequestParam("name") String name) {
        List<Hero> heroes = heroService.list(name);
        if(heroes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(heroes);
    }

    @PutMapping("{id}")
    public ResponseEntity<Hero> update(@Validated @RequestBody HeroRequest request, @PathVariable UUID id) {
        Hero hero = heroService.update(request, id);
        if(hero == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(hero);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Hero hero = heroService.delete(id);
        if(hero == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping("compare/{idA}/{idB}")
    public ResponseEntity<HeroCompareResponse> compare(@PathVariable("idA") UUID idA, @PathVariable("idB") UUID idB) {
        HeroCompareResponse comparation = heroService.compare(idA, idB);
        if(comparation == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(comparation);
    }
}
