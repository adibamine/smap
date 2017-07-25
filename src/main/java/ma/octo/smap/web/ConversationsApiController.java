package ma.octo.smap.web;

import facebook4j.Conversation;
import facebook4j.Message;
import facebook4j.PagableList;
import ma.octo.smap.persistance.domains.CommentByUser;
import ma.octo.smap.persistance.domains.TwitterConversationMessage;
import ma.octo.smap.services.ConversationService;
import ma.octo.smap.services.TweetService;
import ma.octo.smap.web.dto.ConversationDTO;
import ma.octo.smap.web.dto.FbMessageDTO;
import ma.octo.smap.web.dto.TwitterMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.DirectMessage;
import twitter4j.Status;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by adib on 24/04/17.
 */
@RestController
@RequestMapping(value = "/api/conversations")
public class ConversationsApiController {

    private final ConversationService conversationService;
    private final TweetService tweetService;

    @Autowired
    public ConversationsApiController(ConversationService conversationService, TweetService tweetService) {
        this.conversationService = conversationService;
        this.tweetService = tweetService;
    }

    @GetMapping(value = "/facebook/all", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<Conversation> getFacebookConversations() {
        return new ResponseEntity(conversationService.getFacebookConversations(), OK);
    }

    @PostMapping(value = "/facebook/messages", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<PagableList<Message>> getFacebookConversation(@RequestBody ConversationDTO conversationDTO) {
        return new ResponseEntity(conversationService.getFacebookConversation(conversationDTO), OK);
    }

    @PostMapping(value = "/facebook/messages/new", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<String> newFacebookMessage(@RequestBody FbMessageDTO message) {
        conversationService.sendFacebookMessage(message);
        return new ResponseEntity(conversationService.getFacebookConversation(message), OK);
    }

    @GetMapping(value = "/twitter/all", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<Map<String, List<DirectMessage>>> getTwitterConversations() {
        return new ResponseEntity(conversationService.getConversations(), OK);
    }

    @GetMapping(value = "/twitter/messages", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity getTwitterConversation(@RequestParam(value = "sender", required = true) String user1ID, @RequestParam(value = "receiver", required = true) String user2ID) {
        ///////////////////////////
        // VB Code to refactor! //
        //////////////////////////
        List<TwitterConversationMessage> result = conversationService.getConversation(user1ID,user2ID)
                .stream()
                .filter(twitterConversationMessage ->
                        !twitterConversationMessage.getReceiver_id().equals(twitterConversationMessage.getSender_id())).collect(Collectors.toList());
        return new ResponseEntity(result, OK);
    }

    @PostMapping(value = "/twitter/messages/new", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<DirectMessage> newTwitterMessage(@RequestBody TwitterMessageDTO message) {
        return new ResponseEntity(conversationService.sendTwitterMessage(message), OK);
    }


}
