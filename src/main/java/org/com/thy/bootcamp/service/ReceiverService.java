package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.Receiver;
import org.com.thy.bootcamp.repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiverService {


    private final ReceiverRepository receiverRepository;

    public Receiver createReceiver(Receiver receiver) {
        return receiverRepository.save(receiver);
    }

    public Receiver updateReceiver(Long id, Receiver receiverDetails) {
        Optional<Receiver> receiverOptional = receiverRepository.findById(id);
        if (receiverOptional.isPresent()) {
            Receiver receiver = receiverOptional.get();
            // Receiver özelliklerini güncelle
            // receiver.setName(receiverDetails.getName()); // Örneğin, ismi güncellemek için.Şu an herhangi bir attribute yok
            return receiverRepository.save(receiver);
        } else {
            throw new IllegalArgumentException("Receiver not found with id: " + id);
        }
    }

    public Receiver getReceiver(Long id) {
        return receiverRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Receiver not found with id: " + id));
    }

    public List<Receiver> getAllReceivers() {
        return receiverRepository.findAll();
    }

    public void deleteReceiver(Long id) {
        if (receiverRepository.existsById(id)) {
            receiverRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Receiver not found with id: " + id);
        }
    }
}
