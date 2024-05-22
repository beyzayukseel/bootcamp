package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.Receiver;
import org.com.thy.bootcamp.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/receivers")
public class ReceiverController {


    private final ReceiverService receiverService;

    @PostMapping
    public ResponseEntity<Receiver> createReceiver(@RequestBody Receiver receiver) {
        Receiver createdReceiver = receiverService.createReceiver(receiver);
        return ResponseEntity.ok(createdReceiver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receiver> getReceiver(@PathVariable Long id) {
        Receiver receiver = receiverService.getReceiver(id);
        return ResponseEntity.ok(receiver);
    }

    @GetMapping
    public ResponseEntity<List<Receiver>> getAllReceivers() {
        List<Receiver> receivers = receiverService.getAllReceivers();
        return ResponseEntity.ok(receivers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receiver> updateReceiver(@PathVariable Long id, @RequestBody Receiver receiverDetails) {
        Receiver updatedReceiver = receiverService.updateReceiver(id, receiverDetails);
        return ResponseEntity.ok(updatedReceiver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceiver(@PathVariable Long id) {
        receiverService.deleteReceiver(id);
        return ResponseEntity.noContent().build();
    }
}
