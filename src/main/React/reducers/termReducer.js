import {Map} from 'immutable'
import { TERMS_SAVED, TERMS_ALL, FEEDS_BY_TERM, AJAX_BEGIN, AJAX_END } from 'actions/actionTypes'

export default function termReducer(state = Map(), action) {
    switch(action.type) {

        case AJAX_BEGIN:
            console.log('ajax begin');
            return state.merge({
                ajaxBegin: true,
                ajaxEnd: false
            })

        case AJAX_END:
            console.log('ajax end');
            return state.merge({
                ajaxBegin: false,
                ajaxEnd: true
            })

        case TERMS_SAVED:
            return state.merge({
                saved: action.saved
            })

        case TERMS_ALL:
            return state.merge({
                terms: action.terms
            })

        case FEEDS_BY_TERM:
            return state.merge({
                feeds: action.feeds
            })

        default:
            return state
    }
}