import request from 'axios'

import { TOP_INFLUENCERS, USER_COMMENTS, USER_BLOCKED, AJAX_BEGIN, AJAX_END, COMMENTS_INSIGHT } from 'actions/actionTypes'


export function loadInfluencers() {

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})
        console.log("herefdcezz")
        return request.get('/api/comments/topInfluencers')
            .then(function (response) {
                dispatch({type: TOP_INFLUENCERS, influencers: response.data})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                dispatch({type: AJAX_END})
            })
    }
}

    export function loadUserComments(client_id, user_id) {

        return function(dispatch){
            dispatch({ type: AJAX_BEGIN })

            return request.get(`/api/comments/${client_id}/${user_id}`)
                .then(function(response){
                    dispatch({ type: USER_COMMENTS, comments: response.data })
                    dispatch({ type: AJAX_END })
                })
                .catch(function(error){
                    dispatch({ type: AJAX_END })
                })
        }
    }


export function loadCommentsInsight() {

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})

        return request.get(`/api/comments/insights/attijari`)
            .then(function (response) {
                console.log(response.data)
                dispatch({type: COMMENTS_INSIGHT, commentsInsightData: response.data})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                console.log(error)
                dispatch({type: AJAX_END})
            })
    }
}


export function block_user(user_id) {

    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.get(`/api/comments/blockUser/${user_id}`)
            .then(function(response){
                dispatch({ type: USER_BLOCKED })
                dispatch({ type: AJAX_END })
            })
            .catch(function(error){
                dispatch({ type: AJAX_END })
            })
    }
}