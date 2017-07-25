import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/terms'


import TermForm from 'components/terms/TermForm'

class EditProductCmp extends React.Component {
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
                            <TermForm saveTerm = {this.props.saveTerm} />
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        savedTerm: state.term.get('saved'),
        ajaxBegin: state.term.get('ajaxBegin')
    }
}

export const EditTerm = connect(mapStateToProps, actionCreators)(EditProductCmp)