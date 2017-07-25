import {Map} from 'immutable'
import { FEEDS_BY_TERM } from 'actions/actionTypes'

export default function feedReducer(state = Map(), action) {
    switch(action.type) {

        case FEEDS_BY_TERM:
            return state.merge({
                feeds: action.feeds
            })

        default:
            return state
    }
}