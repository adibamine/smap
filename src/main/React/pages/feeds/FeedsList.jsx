/**
 * Created by adib on 27/03/17.
 */
import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/feeds'

import FeedsTable from 'components/feeds/FeedsTable'

class FeedsListCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
        this.param = this.props.params.term;
    }

    componentDidMount(){
        this.props.loadFeedsByTerm(this.props.params.term)
    }

    getFeeds(){
        if(!this.props.feeds){
            return []
        }

        return this.props.feeds
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <h2>{this.param}</h2>
                            <FeedsTable feeds = {this.getFeeds()}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        needRefresh: state.term.get('needRefresh'),
        feeds: state.term.get('feeds')
    }
}

export const FeedsList = connect(mapStateToProps, actionCreators)(FeedsListCmp)