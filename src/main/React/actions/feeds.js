import request from 'axios'

import { AJAX_BEGIN, AJAX_END, FEEDS_BY_TERM } from 'actions/actionTypes'

export function loadFeedsByTerm(term) {
    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.get(`/api/feeds/${term}`)
            .then(function(response){
                dispatch({ type: FEEDS_BY_TERM, feeds : response.data })
                dispatch({ type: AJAX_END })
            })
            .catch(function(response){
                dispatch({ type: AJAX_END })
            })
    }
}