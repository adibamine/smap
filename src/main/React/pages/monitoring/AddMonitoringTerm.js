import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/monitoring'

import MonitoringForm from "components/monitoring/MonitoringForm";

class AddMonitoringTermCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    render() {
        let loading = this.props.ajaxBegin ? <div>Loading...</div> : "";
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            {loading}
                            <MonitoringForm monitorTerm = {this.props.monitorTerm} />
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        ajaxBegin: state.monitoring.get('ajaxBegin')
    }
}

export const AddMonitoringTerm = connect(mapStateToProps, actionCreators)(AddMonitoringTermCmp)