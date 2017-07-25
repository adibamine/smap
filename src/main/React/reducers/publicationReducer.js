/**
 * Created by adib on 15/04/17.
 */
import {Map} from 'immutable'
import { TWEET_SAVED, POST_SAVED, POST_LINK_SAVED, AJAX_BEGIN, AJAX_END } from 'actions/actionTypes'

export default function publicationReducer(state = Map(), action) {
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

        case POST_LINK_SAVED:
            return state.merge({
                saved_post_link: action.saved
            })

        case POST_SAVED:
            return state.merge({
                saved_post: action.saved
            })

        case TWEET_SAVED:
            return state.merge({
                saved_tweet: action.saved
            })

        default:
            return state
    }
}