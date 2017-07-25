import {Map} from 'immutable'
import { CHART_TEST } from 'actions/actionTypes'

export default function chartReducer(state = Map(), action) {
    switch(action.type) {

        case CHART_TEST:
            return state.merge({
                chartData: action.chartData
            })

        default:
            return state
    }
}