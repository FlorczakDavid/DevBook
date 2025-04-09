package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.entities.RssProvider;
import co.simplon.devbookapi.services.RssService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/rss")
public class RssController {
     private final RssService rssService;

     public RssController(RssService rssService) {
         this.rssService = rssService;
     }

     @PostMapping("/import")
     public ResponseEntity<RssProvider> importFeed(@RequestParam String url) throws IOException {
         RssProvider provider = rssService.fetchAndStoreFrom(url);
         return ResponseEntity.ok(provider);
     }
}
