/**
 * Created by adib on 15/04/17.
 */

import request from 'axios'

import { TWEET_SAVED, POST_SAVED, POST_LINK_SAVED , AJAX_BEGIN, AJAX_END } from 'actions/actionTypes'


export function createPostLink(postLinkToSave) {
    console.log(postLinkToSave)

    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.post('/api/insights/createPostLink', postLinkToSave)
            .then(function(response){
                dispatch({ type: POST_LINK_SAVED, saved : response.data })
                dispatch({ type: AJAX_END })
            })
            .catch(function(response){
                dispatch({ type: AJAX_END })
            })
    }
}


export function createPost(postLinkToSave) {
    console.log(postLinkToSave)

    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.post('/api/insights/createPost', postLinkToSave)
            .then(function(response){
                dispatch({ type: POST_SAVED, saved : response.data })
                dispatch({ type: AJAX_END })
            })
            .catch(function(response){
                dispatch({ type: AJAX_END })
            })
    }
}


export function createTweet(tweetToSave) {
    console.log(tweetToSave)

    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.post('/api/insights/createTweet', tweetToSave)
            .then(function(response){
                dispatch({ type: TWEET_SAVED, saved : response.data })
                dispatch({ type: AJAX_END })
            })
            .catch(function(response){
                dispatch({ type: AJAX_END })
            })
    }
}