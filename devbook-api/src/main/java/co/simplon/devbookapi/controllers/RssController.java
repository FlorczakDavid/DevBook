package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.dtos.RssUrlCreate;
import co.simplon.devbookapi.entities.RssProvider;
import co.simplon.devbookapi.services.RssService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/rss")
public class RssController {
     private final RssService rssService;

     public RssController(RssService rssService) {
         this.rssService = rssService;
     }

     @PostMapping("/import")
     public ResponseEntity<RssProvider> importFeed(@Valid @RequestBody RssUrlCreate input) throws IOException {
         RssProvider provider = rssService.fetchAndStoreFrom(input);
         return ResponseEntity.ok(provider);
     }
}
