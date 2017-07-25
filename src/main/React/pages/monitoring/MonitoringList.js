/**
 * Created by adib on 27/03/17.
 */
import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/monitoring'

import MonitoringTable from 'components/monitoring/MonitoringTable'

class MonitoringListCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
        this.param = this.props.params.term;
    }

    componentDidMount(){
        console.log("yo")
        this.props.monitorTerm(this.props.params.term_ar,this.props.params.term_fr)
    }

    getMonitoringFeeds(){
        if(!this.props.monitoring_feeds){
            return []
        }

        return this.props.monitoring_feeds
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <h2>{this.param}</h2>
                            <MonitoringTable monitoring_feeds = {this.getMonitoringFeeds()}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        monitoring_feeds: state.monitoring.get('monitoring_feeds')
    }
}

export const MonitoringList = connect(mapStateToProps, actionCreators)(MonitoringListCmp)
