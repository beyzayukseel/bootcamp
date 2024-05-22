package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.GroupWalletInvitation;
import org.com.thy.bootcamp.model.SendInvitationLinkRequest;
import org.com.thy.bootcamp.service.GroupWalletInvitationService;
import org.com.thy.bootcamp.util.SystemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group-wallet/invitation")
public class GroupWalletInvitationController {


    private final GroupWalletInvitationService groupWalletInvitationService;

    @PostMapping
    public ResponseEntity<SystemResponse> sendGroupWalletInvitationLink(@RequestBody SendInvitationLinkRequest request) {
       return groupWalletInvitationService.sendGroupWalletInvitationLink(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupWalletInvitation> getLink(@PathVariable Long id) {
        GroupWalletInvitation linkGenerator = groupWalletInvitationService.getLink(id);
        if (linkGenerator != null) {
            return ResponseEntity.ok(linkGenerator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        groupWalletInvitationService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}
