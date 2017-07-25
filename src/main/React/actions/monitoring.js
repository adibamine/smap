import request from 'axios'

import { MONITORING_TERM_SAVED, AJAX_BEGIN, AJAX_END } from 'actions/actionTypes'

export function monitorTerm(term_ar, term_fr) {
console.log("yoo")
    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.get(`/api/monitoring?term_ar=${term_ar}&term_fr=${term_fr}`)
            .then(function(response){
                dispatch({ type: MONITORING_TERM_SAVED, saved : response.data })
                dispatch({ type: AJAX_END })
            })
            .catch(function(response){
                dispatch({ type: AJAX_END })
            })
    }
}