import request from 'axios'

import { AJAX_BEGIN, AJAX_END, CHART_TEST } from 'actions/actionTypes'


export function loadChartTest() {

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})

        return request.get('/api/insights/page_impressions')
            .then(function (response) {
                dispatch({type: CHART_TEST, chartData: response.data.data[0].values})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                dispatch({type: AJAX_END})
            })
    }
}

export function load_page_impressions(metric, startDate, endDate) {

    return function (dispatch) {
        dispatch({type: AJAX_BEGIN})

        return request.get(`/api/insights?metric=${metric}&startDate=${startDate}&endDate=${endDate}`)
            .then(function (response) {
                dispatch({type: CHART_TEST, chartData: response.data.data[0].values})
                dispatch({type: AJAX_END})
            })
            .catch(function (error) {
                dispatch({type: AJAX_END})
            })
    }
}