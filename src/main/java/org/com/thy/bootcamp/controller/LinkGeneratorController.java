package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.LinkGenerator;
import org.com.thy.bootcamp.service.LinkGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/links")
public class LinkGeneratorController {


    private final LinkGeneratorService linkGeneratorService;

    @PostMapping
    public ResponseEntity<LinkGenerator> createLink(@RequestBody LinkGenerator linkGenerator) {
        LinkGenerator createdLink = linkGeneratorService.createLink(linkGenerator);
        return ResponseEntity.ok(createdLink);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkGenerator> getLink(@PathVariable Long id) {
        LinkGenerator linkGenerator = linkGeneratorService.getLink(id);
        if (linkGenerator != null) {
            return ResponseEntity.ok(linkGenerator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        linkGeneratorService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}
