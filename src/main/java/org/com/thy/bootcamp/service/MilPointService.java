package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.MilPoint;
import org.com.thy.bootcamp.entity.Wallet;
import org.com.thy.bootcamp.repository.MilPointRepository;
import org.com.thy.bootcamp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MilPointService {


    private final MilPointRepository milPointRepository;

    private final WalletRepository walletRepository;

    public MilPoint createMilPoint(MilPoint milPoint) {
        Wallet wallet = walletRepository.findById(milPoint.getWallet().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid wallet ID"));

        milPoint.setWallet(wallet);
        milPoint.setCreated(LocalDate.now());
        return milPointRepository.save(milPoint);
    }

    public MilPoint updateMilPoint(Long id, BigDecimal amount) {
        MilPoint milPoint = milPointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MilPoint not found with id: " + id));

        milPoint.setAmount(amount);
        return milPointRepository.save(milPoint);
    }

    public MilPoint getMilPoint(Long id) {
        return milPointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MilPoint not found with id: " + id));
    }

    public List<MilPoint> getAllMilPoints() {
        return milPointRepository.findAll();
    }

    public void deleteMilPoint(Long id) {
        if (milPointRepository.existsById(id)) {
            milPointRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("MilPoint not found with id: " + id);
        }
    }
}
