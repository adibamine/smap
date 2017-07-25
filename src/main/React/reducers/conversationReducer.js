import {Map} from 'immutable'
import { ALL_CONVERSATIONS, ALL_TWITTER_CONVERSATIONS, CONVERSATION_MESSAGES } from 'actions/actionTypes'

export default function conversationReducer(state = Map(), action) {
    switch(action.type) {

        case ALL_CONVERSATIONS:
            return state.merge({
                conversations: action.conversations
            })

        case ALL_TWITTER_CONVERSATIONS:
            return state.merge({
                twitterConversations: action.twitterConversations
            })

        case CONVERSATION_MESSAGES:
            return state.merge({
                messages: action.messages.reverse()
            })

        default:
            return state
    }
}