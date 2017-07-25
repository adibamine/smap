/**
 * Created by adib on 27/03/17.
 */
import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import InsightsDisplay from 'components/comments/InsightsDisplay'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/comments'


class InsightsCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        this.props.loadCommentsInsight()
    }

    getBySentiments(){
        if(!this.props.bySentiments){
            return []
        }
        return JSON.parse(JSON.stringify(this.props.bySentiments))
    }

    getByLanguages(){
        if(!this.props.byLanguages){
            return []
        }
        return JSON.parse(JSON.stringify(this.props.byLanguages))
    }


    render() {
        return  <div>
            <h1>Comments insight</h1>
            <InsightsDisplay byLanguages = {this.getByLanguages()} bySentiments = {this.getBySentiments()} />
        </div>
    }
};


function mapStateToProps(state) {
    console.log(state)
    return {
        bySentiments: state.comment.get('bySentiments'),
        byLanguages: state.comment.get('byLanguages')
    }
}

export const Insights = connect(mapStateToProps, actionCreators)(InsightsCmp)