import {Map} from 'immutable'
import { MONITORING_TERM_SAVED, AJAX_BEGIN, AJAX_END } from 'actions/actionTypes'

export default function monitoringTermReducer(state = Map(), action) {
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

        case MONITORING_TERM_SAVED:
            return state.merge({
                monitoring_feeds: action.saved
            })



        default:
            return state
    }
}