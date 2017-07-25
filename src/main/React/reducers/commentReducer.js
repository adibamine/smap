import {Map} from 'immutable'
import { TOP_INFLUENCERS, USER_COMMENTS, COMMENTS_INSIGHT } from 'actions/actionTypes'

export default function commentReducer(state = Map(), action) {
    switch(action.type) {

        case TOP_INFLUENCERS:
            return state.merge({
                influencers: action.influencers
            })

        case USER_COMMENTS:
            return state.merge({
                comments: action.comments
            })

        case COMMENTS_INSIGHT:
            console.log(action.commentsInsightData)
            var bySentiments = action.commentsInsightData.slice(0, 3);
            var byLanguages = action.commentsInsightData.slice(3, 6);
            console.log(bySentiments)
            console.log(byLanguages)
            return state.merge({
                bySentiments: bySentiments,
                byLanguages: byLanguages
            })

        default:
            return state
    }
}