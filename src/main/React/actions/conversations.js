import request from 'axios'

import { ALL_CONVERSATIONS, ALL_TWITTER_CONVERSATIONS, CONVERSATION_MESSAGES, AJAX_BEGIN, AJAX_END } from 'actions/actionTypes'


export function loadConversations() {

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})

        return request.get('/api/conversations/facebook/all')
            .then(function (response) {
                dispatch({type: ALL_CONVERSATIONS, conversations: response.data})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                dispatch({type: AJAX_END})
            })
    }
}

export function loadTwitterConversations() {

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})

        return request.get('/api/conversations/twitter/all')
            .then(function (response) {
                console.log(response)
                dispatch({type: ALL_TWITTER_CONVERSATIONS, twitterConversations: response.data})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                dispatch({type: AJAX_END})
            })
    }
}


export function loadConversationMessages(conversationId) {
    console.log("khraa")

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})

        return request.post('/api/conversations/facebook/messages', conversationId)
            .then(function (response) {
                dispatch({type: CONVERSATION_MESSAGES, messages: response.data})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                dispatch({type: AJAX_END})
            })
    }
}


export function sendMessage(newMessage) {
    console.log("sendMsg")

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})

        return request.post('/api/conversations/facebook/messages/new', newMessage)
            .then(function (response) {
                dispatch({type: CONVERSATION_MESSAGES, messages: response.data})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                dispatch({type: AJAX_END})
            })
    }
}