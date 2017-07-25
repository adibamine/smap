import request from 'axios'

import { TERMS_SAVED, AJAX_BEGIN, AJAX_END, TERMS_ALL } from 'actions/actionTypes'

export function saveTerm(termToSave) {

    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.post('/api/terms/save', termToSave)
            .then(function(response){
                dispatch({ type: TERMS_SAVED, saved : response.data })
                dispatch({ type: AJAX_END })
            })
            .catch(function(response){
                dispatch({ type: AJAX_END })
            })
    }
}

export function loadAllTerms() {

    return function(dispatch){
        dispatch({ type: AJAX_BEGIN })

        return request.get('/api/terms')
            .then(function(response){
                dispatch({ type: TERMS_ALL, terms: response.data })
                dispatch({ type: AJAX_END })
            })
            .catch(function(error){
                dispatch({ type: AJAX_END })
            })
    }
}